package com.voting.app.service;

import com.voting.app.dao.CandidateRepository;
import com.voting.app.dao.DesignationRepository;
import com.voting.app.dao.VoteRecordRepository;
import com.voting.app.dao.VoterRepository;
import com.voting.app.dto.CandidateDto;
import com.voting.app.dto.DesignationDto;
import com.voting.app.dto.VoterDto;
import com.voting.app.entity.Candidate;
import com.voting.app.entity.Designation;
import com.voting.app.entity.VoteRecord;
import com.voting.app.entity.Voter;
import com.voting.app.exception.CandidateNotFoundException;
import com.voting.app.exception.DesignationNotFound;
import com.voting.app.exception.NotAdminException;
import com.voting.app.exception.VoterNotFoundException;
import com.voting.app.transformer.CandidateTransformer;
import com.voting.app.transformer.DesignationTransformer;
import com.voting.app.transformer.VoterTransformer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.StreamSupport;

@Service
public class VotingAppService {

    private final VoterRepository voterRepository;
    private final DesignationRepository designationRepository;
    private final CandidateRepository candidateRepository;
    private final VoteRecordRepository voteRecordRepository;
    private final VoterTransformer voterTransformer;
    private final CandidateTransformer candidateTransformer;
    private final DesignationTransformer designationTransformer;

    public VotingAppService(VoterRepository voterRepository,
                            DesignationRepository designationRepository,
                            CandidateRepository candidateRepository,
                            VoteRecordRepository voteRecordRepository,
                            VoterTransformer voterTransformer,
                            CandidateTransformer candidateTransformer,
                            DesignationTransformer designationTransformer) {
        this.voterRepository = voterRepository;
        this.designationRepository = designationRepository;
        this.candidateRepository = candidateRepository;
        this.voteRecordRepository = voteRecordRepository;
        this.voterTransformer = voterTransformer;
        this.candidateTransformer = candidateTransformer;
        this.designationTransformer = designationTransformer;
    }

    public VoterDto login(String collegeId, boolean isAdmin) {
        Optional<Voter> voter = voterRepository.findByCollegeId(collegeId);
        VoterDto voterDto = voter.map(voterTransformer::from).orElseThrow(() -> new VoterNotFoundException(collegeId));
        if (isAdmin && !voterDto.isAdmin()) {
            throw new NotAdminException(collegeId);
        }
        return voterDto;
    }

    public void addDesignations(List<String> designationsToBeAdded) {
        List<Designation> designationsToBeSaved = designationsToBeAdded
                .stream()
                .map(designation -> new Designation()
                        .setDesignationName(designation)
                        .setCreatedOn(LocalDateTime.now()))
                .toList();
        designationRepository.saveAll(designationsToBeSaved);
    }

    public CandidateDto addCandidate(String firstName, String lastName, String branch, String imgPath,
                                     String campaignQuote, String designationName, String symbol) {
        Designation designation = designationRepository.findByDesignationName(designationName)
                .orElseThrow(() -> new DesignationNotFound(designationName));
        Candidate candidate = new Candidate()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBranch(branch)
                .setCampaignQuote(campaignQuote)
                .setImgPath(imgPath)
                .setSymbol(symbol)
                .setDesignation(designation)
                .setCreatedOn(LocalDateTime.now());
        Candidate savedCandidate = candidateRepository.save(candidate);
        return candidateTransformer.from(savedCandidate);
    }

    public void castVote(String collegeId, int candidateId) {
        Voter voter = voterRepository.findByCollegeId(collegeId)
                .orElseThrow(() -> new VoterNotFoundException(collegeId));
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException(candidateId));
        VoteRecord voteRecord = new VoteRecord()
                .setVoter(voter)
                .setCandidate(candidate)
                .setCreatedOn(LocalDateTime.now());
        voteRecordRepository.save(voteRecord);
    }

    public List<DesignationDto> findAllDesignations() {
        Spliterator<Designation> allDesignations = designationRepository.findAll().spliterator();
        return StreamSupport.stream(allDesignations, false).map(designationTransformer::from).toList();
    }

    public List<CandidateDto> findAllCandidates() {
        Spliterator<Candidate> allDesignations = candidateRepository.findAll().spliterator();
        return StreamSupport.stream(allDesignations, false).map(candidateTransformer::from).toList();
    }
}

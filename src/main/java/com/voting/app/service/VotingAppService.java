package com.voting.app.service;

import com.voting.app.dao.CandidateRepository;
import com.voting.app.dao.DesignationRepository;
import com.voting.app.dao.VoteRecordRepository;
import com.voting.app.dao.VoterRepository;
import com.voting.app.dto.*;
import com.voting.app.entity.*;
import com.voting.app.exception.*;
import com.voting.app.transformer.CandidateTransformer;
import com.voting.app.transformer.DesignationTransformer;
import com.voting.app.transformer.VoterTransformer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
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
    private final PasswordEncoder passwordEncoder;

    public VotingAppService(VoterRepository voterRepository,
                            DesignationRepository designationRepository,
                            CandidateRepository candidateRepository,
                            VoteRecordRepository voteRecordRepository,
                            VoterTransformer voterTransformer,
                            CandidateTransformer candidateTransformer,
                            DesignationTransformer designationTransformer,
                            PasswordEncoder passwordEncoder) {
        this.voterRepository = voterRepository;
        this.designationRepository = designationRepository;
        this.candidateRepository = candidateRepository;
        this.voteRecordRepository = voteRecordRepository;
        this.voterTransformer = voterTransformer;
        this.candidateTransformer = candidateTransformer;
        this.designationTransformer = designationTransformer;
        this.passwordEncoder = passwordEncoder;
    }

    public VoterDto login(String collegeId, String password, boolean isAdmin) {
        VoterDto voterDto;
        if (isAdmin) {
            Optional<Voter> voter = voterRepository.findByCollegeId(collegeId);
            voterDto = voter.map(voterTransformer::from)
                    .orElseThrow(() -> new VoterNotFoundException(collegeId));
            voter.filter(v -> passwordEncoder.matches(password, v.getPassword()))
                    .orElseThrow(IncorrectUsernamePasswordException::new);
            if (!voterDto.isAdmin()) {
                throw new NotAdminException(collegeId);
            }
        } else {
            Optional<Voter> voter = voterRepository.findByCollegeId(collegeId);
            voterDto = voter.map(voterTransformer::from).orElseThrow(() -> new VoterNotFoundException(collegeId));
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

    public CandidateDto addCandidate(String firstName, String lastName, String branch,
                                     String campaignQuote, String designationName, String symbol, byte[] imgBytes) {
        Designation designation = designationRepository.findByDesignationName(designationName)
                .orElseThrow(() -> new DesignationNotFound(designationName));
        Candidate candidate = new Candidate()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBranch(branch)
                .setImgData(imgBytes)
                .setCampaignQuote(campaignQuote)
                .setSymbol(symbol)
                .setDesignation(designation)
                .setCreatedOn(LocalDateTime.now());
        Candidate savedCandidate = candidateRepository.save(candidate);
        return candidateTransformer.from(savedCandidate);
    }

    public void castVote(List<CastVoteRequestDto> request) {
        request.forEach(r -> {
            Voter voter = voterRepository.findByCollegeId(r.collegeId())
                    .orElseThrow(() -> new VoterNotFoundException(r.collegeId()));
            Candidate candidate = candidateRepository.findById(r.candidateId())
                    .orElseThrow(() -> new CandidateNotFoundException(r.candidateId()));
            VoteRecord voteRecord = new VoteRecord()
                    .setVoter(voter)
                    .setCandidate(candidate)
                    .setCreatedOn(LocalDateTime.now());
            voteRecordRepository.save(voteRecord);
        });
    }

    @Transactional
    public List<DesignationDto> findAllDesignations() {
        Spliterator<Designation> allDesignations = designationRepository.findAll().spliterator();
        return StreamSupport.stream(allDesignations, false).map(designationTransformer::from).toList();
    }

    public List<CandidateDto> findAllCandidates() {
        Spliterator<Candidate> allDesignations = candidateRepository.findAll().spliterator();
        return StreamSupport.stream(allDesignations, false).map(candidateTransformer::from).toList();
    }

    public void deleteCandidate(int candidateId) {
        candidateRepository.deleteById(candidateId);
    }

    public void deleteDesignation(int designationId) {
        designationRepository.deleteById(designationId);
    }

    public VoteResultDto fetchVoteResults() {
        List<VoteResult> voteResults = voteRecordRepository.fetchVoteResults();
        Map<String, String> candidateDesignationMap = voteResults
                .stream()
                .collect(Collectors.toMap(VoteResult::getCandidateName, VoteResult::getDesignationName));
        Map<String, Optional<VoteResult>> winnersByDesignation = voteResults
                .stream()
                .collect(Collectors.groupingBy(VoteResult::getDesignationName,
                        Collectors.maxBy(Comparator.comparingInt(VoteResult::getVoteCount))));
        Map<String, Integer> totalVotesPerCandidate = voteResults
                .stream()
                .collect(Collectors.groupingBy(VoteResult::getCandidateName,
                Collectors.summingInt(VoteResult::getVoteCount)));
        VoteResultDto voteResultDto = new VoteResultDto();
        List<VoteResultDto.WinnerData> winnerData = winnersByDesignation.entrySet()
                .stream()
                .map(entry -> new VoteResultDto.WinnerData()
                        .setDesignationName(entry.getKey())
                        .setCandidateName(entry.getValue()
                                .map(VoteResult::getCandidateName)
                                .orElse("")))
                .toList();
        List<VoteResultDto.CandidateData> candidateData = totalVotesPerCandidate.entrySet()
                .stream()
                .map(entry -> new VoteResultDto.CandidateData()
                        .setCandidateName(entry.getKey())
                        .setVoteCount(entry.getValue())
                        .setDesignationName(candidateDesignationMap.get(entry.getKey())))
                .sorted(Comparator.comparingInt(VoteResultDto.CandidateData::getVoteCount).reversed())
                .toList();
        return voteResultDto
                .setCandidateData(candidateData)
                .setWinnerData(winnerData);
    }

    public boolean voteAlready(String collegeId) {
        Optional<Voter> voter = voterRepository.findByCollegeId(collegeId);
        return voter.map(voteRecordRepository::findByVoter)
                .map(voteResults -> voteResults.size() > 0)
                .orElse(false);
    }
}

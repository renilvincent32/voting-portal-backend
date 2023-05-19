package com.voting.app.controller;

import com.voting.app.dto.*;
import com.voting.app.service.VotingAppService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VotingAppController {

    private final VotingAppService votingAppService;

    public VotingAppController(VotingAppService votingAppService) {
        this.votingAppService = votingAppService;
    }

    @PostMapping("/login")
    public VoterDto login(@RequestBody LoginRequestDto request) {
        return votingAppService.login(request.collegeId(), request.password(), request.isAdmin());
    }

    @PostMapping("/addDesignations")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void addDesignations(@RequestBody AddDesignationsRequestDto request) {
        votingAppService.addDesignations(request.designations());
    }

    @GetMapping("/getDesignations")
    @PreAuthorize("hasRole('ADMIN')")
    public List<DesignationDto> findAllDesignations() {
        return votingAppService.findAllDesignations();
    }

    @PostMapping("/addCandidate")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CandidateDto  addCandidate(@RequestBody AddCandidateRequestDto request) {
        return votingAppService.addCandidate(request.firstName(), request.lastName(), request.branch(),
                request.imgPath(), request.campaignQuote(), request.designation(), request.symbol());
    }

    @GetMapping("/getCandidates")
    public List<CandidateDto> findAllCandidates() {
        return votingAppService.findAllCandidates();
    }


    @PostMapping("/castVote")
    public void castVote(@RequestBody List<CastVoteRequestDto> request) {
        votingAppService.castVote(request);
    }

    @DeleteMapping("/deleteCandidate/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCandidate(@PathVariable("id") int candidateId) {
        votingAppService.deleteCandidate(candidateId);
    }

    @DeleteMapping("/deleteDesignation/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDesignation(@PathVariable("id") int designationId) {
        votingAppService.deleteDesignation(designationId);
    }
    @GetMapping("/fetchVoteResults")
    public VoteResultDto fetchVoteResults() {
        return votingAppService.fetchVoteResults();
    }
}

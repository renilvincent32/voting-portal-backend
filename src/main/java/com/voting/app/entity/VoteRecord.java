package com.voting.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    private LocalDateTime createdOn;

    public int getVoteId() {
        return voteId;
    }

    public VoteRecord setVoteId(int voteId) {
        this.voteId = voteId;
        return this;
    }

    public Voter getVoter() {
        return voter;
    }

    public VoteRecord setVoter(Voter voter) {
        this.voter = voter;
        return this;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public VoteRecord setCandidate(Candidate candidate) {
        this.candidate = candidate;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public VoteRecord setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}

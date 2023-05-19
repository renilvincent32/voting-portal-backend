package com.voting.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;

    private String firstName;

    private String lastName;

    private String branch;

    private String symbol;

    private String imgPath;

    private String campaignQuote;

    private LocalDateTime createdOn;

    @ManyToOne
    @JoinColumn(name = "designation_id")
    private Designation designation;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<VoteRecord> voteRecords;

    public int getCandidateId() {
        return candidateId;
    }

    public Candidate setCandidateId(int candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Candidate setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Candidate setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBranch() {
        return branch;
    }

    public Candidate setBranch(String branch) {
        this.branch = branch;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public Candidate setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Candidate setImgPath(String imgPath) {
        this.imgPath = imgPath;
        return this;
    }

    public String getCampaignQuote() {
        return campaignQuote;
    }

    public Candidate setCampaignQuote(String campaignQuote) {
        this.campaignQuote = campaignQuote;
        return this;
    }

    public Designation getDesignation() {
        return designation;
    }

    public Candidate setDesignation(Designation designation) {
        this.designation = designation;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Candidate setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public List<VoteRecord> getVoteRecords() {
        return voteRecords;
    }

    public Candidate setVoteRecords(List<VoteRecord> voteRecords) {
        this.voteRecords = voteRecords;
        return this;
    }
}

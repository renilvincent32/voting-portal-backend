package com.voting.app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int designationId;

    private String designationName;

    private LocalDateTime createdOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "designation")
    private List<Candidate> candidates;

    public int getDesignationId() {
        return designationId;
    }

    public Designation setDesignationId(int designationId) {
        this.designationId = designationId;
        return this;
    }

    public String getDesignationName() {
        return designationName;
    }

    public Designation setDesignationName(String designationName) {
        this.designationName = designationName;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Designation setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public Designation setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
        return this;
    }
}

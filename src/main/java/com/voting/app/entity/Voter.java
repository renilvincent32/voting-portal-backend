package com.voting.app.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voterId;

    private String collegeId;

    private String firstName;

    private String lastName;

    private String password;

    private String branch;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdOn;

    public int getVoterId() {
        return voterId;
    }

    public Voter setVoterId(int voterId) {
        this.voterId = voterId;
        return this;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public Voter setCollegeId(String collegeId) {
        this.collegeId = collegeId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Voter setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Voter setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Voter setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBranch() {
        return branch;
    }

    public Voter setBranch(String branch) {
        this.branch = branch;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public Voter setRole(Role role) {
        this.role = role;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public Voter setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}

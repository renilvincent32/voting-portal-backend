package com.voting.app.dao;

import com.voting.app.entity.Voter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VoterRepository extends CrudRepository<Voter, Integer> {

    Optional<Voter> findByCollegeId(String collegeId);
}

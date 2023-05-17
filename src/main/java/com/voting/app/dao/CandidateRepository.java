package com.voting.app.dao;

import com.voting.app.entity.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
}

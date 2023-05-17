package com.voting.app.dao;

import com.voting.app.entity.VoteRecord;
import org.springframework.data.repository.CrudRepository;

public interface VoteRecordRepository extends CrudRepository<VoteRecord, Integer> {
}

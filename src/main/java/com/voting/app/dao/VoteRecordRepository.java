package com.voting.app.dao;

import com.voting.app.entity.VoteRecord;
import com.voting.app.entity.VoteResult;
import com.voting.app.entity.Voter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VoteRecordRepository extends CrudRepository<VoteRecord, Integer> {

    @Query(value = """
            select d.designation_name as designationName, c.first_name || ' ' || c.last_name as candidateName, 
            count(v.*) as voteCount from vote_record v 
            right outer join candidate c on v.candidate_id = c.candidate_id
            inner join designation d on c.designation_id = d.designation_id
            group by c.candidate_id, d.designation_id
            """, nativeQuery = true)
    List<VoteResult> fetchVoteResults();

    List<VoteRecord> findByVoter(Voter voter);
}

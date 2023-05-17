package com.voting.app.transformer;

import com.voting.app.dto.VoterDto;
import com.voting.app.entity.Role;
import com.voting.app.entity.Voter;
import org.springframework.stereotype.Component;

@Component
public class VoterTransformer implements EntityTransformer<Voter, VoterDto>{

    @Override
    public VoterDto from(Voter voter) {
        return new VoterDto(voter.getCollegeId(), voter.getFirstName(),
                voter.getLastName(), voter.getBranch(), voter.getRole().equals(Role.ADMIN));
    }
}

package com.voting.app.transformer;

import com.voting.app.dto.CandidateDto;
import com.voting.app.entity.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateTransformer implements EntityTransformer<Candidate, CandidateDto>{
    @Override
    public CandidateDto from(Candidate candidate) {
        return new CandidateDto(candidate.getCandidateId(), candidate.getFirstName(), candidate.getLastName(),
                candidate.getBranch(), candidate.getImgData(), candidate.getSymbol(), candidate.getCampaignQuote(),
                candidate.getDesignation().getDesignationName());
    }
}

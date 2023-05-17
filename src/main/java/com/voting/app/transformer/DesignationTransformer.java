package com.voting.app.transformer;

import com.voting.app.dto.DesignationDto;
import com.voting.app.entity.Designation;
import org.springframework.stereotype.Component;

@Component
public class DesignationTransformer implements EntityTransformer<Designation, DesignationDto> {

    @Override
    public DesignationDto from(Designation designation) {
        int noOfCandidates = designation.getCandidates().size();
        return new DesignationDto(designation.getDesignationId(), designation.getDesignationName(), noOfCandidates);
    }
}

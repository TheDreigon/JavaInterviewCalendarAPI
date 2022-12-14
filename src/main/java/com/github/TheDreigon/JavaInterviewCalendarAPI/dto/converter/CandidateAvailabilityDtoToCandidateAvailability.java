package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailabilityDto} to {@link CandidateAvailability} type conversion
 */
@Component
public class CandidateAvailabilityDtoToCandidateAvailability implements Converter<CandidateAvailabilityDto, CandidateAvailability> {

    @Autowired
    private CandidateAvailabilityService candidateAvailabilityService;

    /**
     * Converts the candidateCandidateAvailability DTO into an candidateCandidateAvailability model object
     *
     * @param candidateCandidateAvailabilityDto the candidateCandidateAvailability DTO to convert
     * @return the candidateCandidateAvailability
     */
    @Override
    public CandidateAvailability convert(CandidateAvailabilityDto candidateCandidateAvailabilityDto) {

        CandidateAvailability candidateCandidateAvailability = (candidateCandidateAvailabilityDto.getId() != null ? candidateAvailabilityService.getCandidateAvailability(candidateCandidateAvailabilityDto.getId()) : new CandidateAvailability());

        //candidateCandidateAvailability.setName(candidateCandidateAvailabilityDto.getName());
        //candidateCandidateAvailability.setDescription(candidateCandidateAvailabilityDto.getDescription());

        return candidateCandidateAvailability;
    }
}

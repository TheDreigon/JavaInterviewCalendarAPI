package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.candidate.CandidateToCandidateDtoNoAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailability} to {@link CandidateAvailabilityDto} type conversion
 */
@Component
public class CandidateAvailabilityToCandidateAvailabilityDto implements Converter<CandidateAvailability, CandidateAvailabilityDto> {

    @Autowired
    private CandidateToCandidateDtoNoAvailability candidateToCandidateDtoNoAvailability;

    /**
     * Converts the candidateAvailability model object into a candidateAvailability DTO
     *
     * @param candidateAvailability the candidateAvailability to convert
     * @return the candidateAvailability DTO
     */
    @Override
    public CandidateAvailabilityDto convert(CandidateAvailability candidateAvailability) {

        CandidateAvailabilityDto candidateAvailabilityDto = new CandidateAvailabilityDto();

        candidateAvailabilityDto.setId(candidateAvailability.getId());
        candidateAvailabilityDto.setCandidateDtoNoAvailability(candidateToCandidateDtoNoAvailability.convert(candidateAvailability.getCandidate()));
        candidateAvailabilityDto.setDayDate(candidateAvailability.getDayDate());
        candidateAvailabilityDto.setAvailableHour(candidateAvailability.getAvailableHour());
        candidateAvailabilityDto.setDayOfWeek(candidateAvailability.getDayOfWeek());

        return candidateAvailabilityDto;
    }
}

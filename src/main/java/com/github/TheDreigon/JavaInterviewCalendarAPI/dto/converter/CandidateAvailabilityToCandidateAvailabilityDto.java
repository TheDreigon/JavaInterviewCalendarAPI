package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailability} to {@link CandidateAvailabilityDto} type conversion
 */
@Component
public class CandidateAvailabilityToCandidateAvailabilityDto implements Converter<CandidateAvailability, CandidateAvailabilityDto> {

    /**
     * Converts the candidateAvailability model object into an candidateAvailability DTO
     *
     * @param candidateAvailability the candidateAvailability to convert
     * @return the candidateAvailability DTO
     */
    @Override
    public CandidateAvailabilityDto convert(CandidateAvailability candidateAvailability) {

        CandidateAvailabilityDto candidateAvailabilityDto = new CandidateAvailabilityDto();

        candidateAvailabilityDto.setId(candidateAvailability.getId());

        candidateAvailabilityDto.setCandidate(candidateAvailability.getCandidate());

        candidateAvailabilityDto.setDayDate(candidateAvailability.getDayDate());
        candidateAvailabilityDto.setAvailableHour(candidateAvailability.getAvailableHour());
        candidateAvailabilityDto.setDayOfWeek(candidateAvailability.getDayOfWeek());

        return candidateAvailabilityDto;
    }
}

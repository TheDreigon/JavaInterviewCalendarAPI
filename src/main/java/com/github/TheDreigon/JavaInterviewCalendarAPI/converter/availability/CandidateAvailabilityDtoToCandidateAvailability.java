package com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailabilityDto} to {@link CandidateAvailability} type conversion
 */
@Component
public class CandidateAvailabilityDtoToCandidateAvailability implements Converter<CandidateAvailabilityDto, CandidateAvailability> {

    /**
     * Converts the candidateCandidateAvailability DTO into a candidateCandidateAvailability model object
     *
     * @param candidateAvailabilityDto the candidateCandidateAvailability DTO to convert
     * @return the candidateCandidateAvailability
     */
    @Override
    public CandidateAvailability convert(CandidateAvailabilityDto candidateAvailabilityDto) {

        CandidateAvailability candidateAvailability = new CandidateAvailability();

        candidateAvailability.setDayDate(candidateAvailabilityDto.getDayDate());
        candidateAvailability.setAvailableHour(candidateAvailabilityDto.getAvailableHour());
        candidateAvailability.setDayOfWeek(candidateAvailabilityDto.getDayOfWeek());

        return candidateAvailability;
    }
}

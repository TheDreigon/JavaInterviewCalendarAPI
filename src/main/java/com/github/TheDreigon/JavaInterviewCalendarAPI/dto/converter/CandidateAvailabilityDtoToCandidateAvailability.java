package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailabilityDto} to {@link CandidateAvailability} type conversion
 */
@Component
public class CandidateAvailabilityDtoToCandidateAvailability implements Converter<CandidateAvailabilityDto, CandidateAvailability> {

    /**
     * Converts the candidateCandidateAvailability DTO into an candidateCandidateAvailability model object
     *
     * @param candidateAvailabilityDto the candidateCandidateAvailability DTO to convert
     * @return the candidateCandidateAvailability
     */
    @Override
    public CandidateAvailability convert(CandidateAvailabilityDto candidateAvailabilityDto) {

        CandidateAvailability candidateAvailability = new CandidateAvailability();

        candidateAvailability.setCandidate(candidateAvailabilityDto.getCandidate());
        candidateAvailability.setDayDate(candidateAvailabilityDto.getDayDate());
        candidateAvailability.setHour(candidateAvailabilityDto.getHour());
        candidateAvailability.setDayOfWeek(candidateAvailabilityDto.getDayOfWeek());

        return candidateAvailability;
    }
}

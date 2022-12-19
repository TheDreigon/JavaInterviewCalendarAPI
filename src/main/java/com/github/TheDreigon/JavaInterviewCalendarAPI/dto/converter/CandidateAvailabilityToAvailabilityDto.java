package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateAvailability} to {@link AvailabilityDto} type conversion
 */
@Component
public class CandidateAvailabilityToAvailabilityDto implements Converter<CandidateAvailability, AvailabilityDto> {

    /**
     * Converts the candidateAvailability model object into an availability DTO
     *
     * @param candidateAvailability the candidateAvailability to convert
     * @return the availability DTO
     */
    @Override
    public AvailabilityDto convert(CandidateAvailability candidateAvailability) {

        AvailabilityDto availabilityDto = new AvailabilityDto();

        availabilityDto.setId(candidateAvailability.getId());
        availabilityDto.setDayDate(candidateAvailability.getDayDate());
        availabilityDto.setAvailableHour(candidateAvailability.getAvailableHour());
        availabilityDto.setDayOfWeek(candidateAvailability.getDayOfWeek());

        return availabilityDto;
    }
}

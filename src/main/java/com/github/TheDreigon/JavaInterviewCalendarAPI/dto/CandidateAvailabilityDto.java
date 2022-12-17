package com.github.TheDreigon.JavaInterviewCalendarAPI.dto;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@link CandidateAvailability} data transfer object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CandidateAvailabilityDto extends AbstractAvailabilityDto {

    @Null
    private Candidate candidate;
}

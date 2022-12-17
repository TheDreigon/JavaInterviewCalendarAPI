package com.github.TheDreigon.JavaInterviewCalendarAPI.dto;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@link InterviewerAvailability} data transfer object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InterviewerAvailabilityDto extends AbstractAvailabilityDto {

    @Null
    private Interviewer interviewer;
}

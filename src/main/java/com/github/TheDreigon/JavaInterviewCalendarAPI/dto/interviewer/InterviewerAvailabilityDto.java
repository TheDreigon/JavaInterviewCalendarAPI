package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The {@link InterviewerAvailability} data transfer object
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InterviewerAvailabilityDto extends AvailabilityDto {

    @Null
    private InterviewerDtoNoAvailability interviewerDtoNoAvailability;
}

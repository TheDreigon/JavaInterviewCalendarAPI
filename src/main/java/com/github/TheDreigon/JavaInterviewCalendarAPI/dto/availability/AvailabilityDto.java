package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * A more generic {@link CandidateAvailability} and {@link InterviewerAvailability} data transfer object superclass
 */
@Data
public class AvailabilityDto {

    @Null
    private Integer id;

    @NotNull(message = "Date of availability day is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dayDate;

    @NotNull(message = "Available hour is a required field")
    @Pattern(regexp = "(1[012]|[1-9])(\\\\s)?(?i)(am|pm)", message = "Please enter a regular time format (1-12am/pm) describing only the hours, no spaces. Military time (0h-23h) is not supported")
    private String availableHour;

    @NotNull(message = "Day of week is a required field")
    @Pattern(regexp = "(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday)", message = "Please enter a valid day of week")
    private String dayOfWeek;
}

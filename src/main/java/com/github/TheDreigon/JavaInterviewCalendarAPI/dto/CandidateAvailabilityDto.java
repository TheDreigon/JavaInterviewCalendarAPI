package com.github.TheDreigon.JavaInterviewCalendarAPI.dto;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.util.Date;

/**
 * The {@link CandidateAvailability} data transfer object
 */
@Data
public class CandidateAvailabilityDto {

    @Null
    private Integer id;

    @Null
    private Candidate candidate;

    @NotNull(message = "Date of availability day is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "dd/MM/yyyy")
    private Date dayDate;

    @NotNull(message = "Starting hour is a required field")
    @Pattern(regexp = "(1[012]|[1-9]):[0-5][0-9](\\\\s)?(?i)(am|pm)", message = "Please enter a regular time format describing only the hours. Military time is not supported")
    private String startHour;

    @NotNull(message = "Ending hour is a required field")
    @Pattern(regexp = "(1[012]|[1-9]):[0-5][0-9](\\\\s)?(?i)(am|pm)", message = "Please enter a regular time format describing only the hours. Military time is not supported")
    private String endHour;

    @NotNull(message = "Day of week is a required field")
    @Pattern(regexp = "(?i)(monday|tuesday|wednesday|thursday|friday|saturday|sunday)", message = "Please enter a valid day of week")
    private DayOfWeek dayOfWeek;
}

package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

/**
 * The {@link Candidate} data transfer object
 */
@Data
public class CandidateDto {

    @Null
    private Integer id;

    @NotNull(message = "Name is a required field")
    @NotBlank(message = "Name is a required field")
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[a-zA-Z]+", message = "Name should only use letters")
    private String name;

    @NotNull(message = "Description is a required field")
    @NotBlank(message = "Description is a required field")
    @Size(min = 3, max = 128)
    @Pattern(regexp = "[a-zA-Z]+", message = "Description should only use letters")
    private String description;

    @Null
    private List<AvailabilityDto> availabilityDtoList;
}

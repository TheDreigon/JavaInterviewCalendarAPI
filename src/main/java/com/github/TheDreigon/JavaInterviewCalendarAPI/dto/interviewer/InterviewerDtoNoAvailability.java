package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * The {@link Interviewer} data transfer object but with no availability list
 */
@Data
public class InterviewerDtoNoAvailability {

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
}

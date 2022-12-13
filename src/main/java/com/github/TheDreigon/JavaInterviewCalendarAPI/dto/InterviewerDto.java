package com.github.TheDreigon.JavaInterviewCalendarAPI.dto;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * The {@link Interviewer} data transfer object
 */
@Data
public class InterviewerDto {

    @Null
    private Integer id;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[a-zA-Z]+", message = "Name should only use letters")
    private String name;

    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(min = 3, max = 128)
    @Pattern(regexp = "[a-zA-Z]+", message = "Description should only use letters")
    private String description;
}

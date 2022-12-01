package com.github.TheDreigon.JavaCalendarAPI.dto;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * The {@link Calendar} data transfer object
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CalendarDto {

    @Null
    private Integer id;

    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 32)
    @Pattern(regexp = "[a-zA-Z]+", message = "Name should only use letters")
    private String name;

    @NotNull(message = "Description is mandatory")
    @NotBlank(message = "Description is mandatory")
    @Size(min = 3, max = 64)
    @Pattern(regexp = "[a-zA-Z]+", message = "Description should only use letters")
    private String description;
}

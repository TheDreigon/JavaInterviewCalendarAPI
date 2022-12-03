package com.github.TheDreigon.JavaCalendarAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaCalendarAPI.controller.RestCalendarController;
import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RestCalendarController.class)
public class CalendarControllerTest {

    @MockBean
    private CalendarService calendarService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void postShouldReturnCreatedCalendar() throws Exception {

        CalendarDto request = new CalendarDto();
        request.setName("CalendarDto");

        Calendar calendar = new Calendar();
        calendar.setName(request.getName());

        when(calendarService.createCalendar(any(Calendar.class))).thenReturn(calendar);

        mockMvc.perform(post("/api/calendar/")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }
}

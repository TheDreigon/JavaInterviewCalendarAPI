package com.github.TheDreigon.JavaCalendarAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarDtoToCalendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
/*
@SqlGroup({
        @Sql(scripts = {"classpath:database/empty/reset.sql"}, executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = {"classpath:database/init/calendar-data.sql"}, executionPhase = BEFORE_TEST_METHOD)
})
*/
public class CalendarControllerTest {

    @MockBean
    private CalendarService calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private ObjectMapper mapper;

    private final File file = new File("src/test/resources/database/init/calendar-valid.json");

    private final String PATH = "/api/calendar";

    @Mock
    private CalendarDtoToCalendar calendarDtoToCalendar;

    @Mock
    private UriComponents uriComponents;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getShouldReturnCalendarList() throws Exception {


    }

    @Test
    public void getShouldReturnCalendar() throws Exception {


    }

    @Test
    public void postShouldReturnAddedCalendarWithStatusCodeCreated() throws Exception {

        CalendarDto calendarDto = mapper.readValue(file, CalendarDto.class);

        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        when(calendarDtoToCalendar.convert(any(CalendarDto.class))).thenReturn(calendar);
        when(calendarService.createCalendar(any(Calendar.class))).thenReturn(calendar);
        calendar.setId(4);

        when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder.build()).thenReturn(uriComponents);
        when(uriComponents.toUri()).thenReturn(URI.create("/api/calendar/" + calendar.getId()));

        this.httpHeaders.setLocation(uriComponents.toUri());

        this.mockMvc.perform(post(PATH + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(calendarDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(redirectedUrlPattern("http://*/api/calendar/" + calendar.getId()));
    }

    @Test
    public void putShouldReturnEditedCalendar() throws Exception {


    }

    @Test
    public void deleteShouldReturnDeletedCalendar() throws Exception {


    }
}



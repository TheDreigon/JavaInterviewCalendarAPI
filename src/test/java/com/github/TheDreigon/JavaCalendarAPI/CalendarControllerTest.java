package com.github.TheDreigon.JavaCalendarAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarDtoToCalendar;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarToCalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
public class CalendarControllerTest {

    @MockBean
    private CalendarService calendarService;

    @MockBean
    private CalendarRepository calendarRepository;

    @Autowired
    private ObjectMapper mapper;

    private final File file = new File("src/test/resources/database/init/calendar-valid.json");

    private final String PATH = "/api/calendars";

    @Mock
    private CalendarToCalendarDto calendarToCalendarDto;

    @Mock
    private UriComponents uriComponents;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getShouldReturnCalendarListWithStatusCodeOk() throws Exception {

        CalendarDto calendarDto = mapper.readValue(file, CalendarDto.class);

        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        List<Calendar> calendarList = new ArrayList<>();
        calendarList.add(calendar);

        when(calendarService.getCalendarList()).thenReturn(calendarList);
        when(calendarToCalendarDto.convert(any(Calendar.class))).thenReturn(calendarDto);

        this.mockMvc.perform(get(PATH + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(calendarDto.getName())))
                .andExpect(jsonPath("$[0].description", is(calendarDto.getDescription())));
    }

    @Test
    public void getShouldReturnCalendarWithStatusCodeOk() throws Exception {

        CalendarDto calendarDto = mapper.readValue(file, CalendarDto.class);

        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        when(calendarService.getCalendar(any(Integer.class))).thenReturn(calendar);
        when(calendarToCalendarDto.convert(any(Calendar.class))).thenReturn(calendarDto);

        this.mockMvc.perform(get(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(calendarDto.getName())))
                .andExpect(jsonPath("$.description", is(calendarDto.getDescription())));
    }

    @Test
    public void postShouldAddCalendarWithStatusCodeCreatedRedirect() throws Exception {

        CalendarDto calendarDto = mapper.readValue(file, CalendarDto.class);

        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        when(calendarService.createCalendar(any(Calendar.class))).thenReturn(calendar);
        calendar.setId(4);
        when(calendarToCalendarDto.convert(any(Calendar.class))).thenReturn(calendarDto);

        when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder.build()).thenReturn(uriComponents);
        when(uriComponents.toUri()).thenReturn(URI.create("/api/calendars/" + calendar.getId()));

        this.httpHeaders.setLocation(uriComponents.toUri());

        this.mockMvc.perform(post(PATH + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(calendarDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(calendarDto.getName())))
                .andExpect(jsonPath("$.description", is(calendarDto.getDescription())))
                .andExpect(redirectedUrlPattern("http://*/api/calendars/" + calendar.getId()));
    }

    @Test
    public void putShouldEditCalendarWithStatusCodeOk() throws Exception {

        CalendarDto calendarDto = mapper.readValue(file, CalendarDto.class);

        Calendar calendar = new Calendar();
        calendar.setName(calendarDto.getName());
        calendar.setDescription(calendarDto.getDescription());

        when(calendarService.getCalendar(any(Integer.class))).thenReturn(Mockito.mock(Calendar.class));

        calendar.setId(4);
        when(calendarService.updateCalendar(any(Calendar.class))).thenReturn(calendar);
        when(calendarToCalendarDto.convert(any(Calendar.class))).thenReturn(calendarDto);

        this.mockMvc.perform(put(PATH + "/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(calendarDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is(calendarDto.getName())))
                .andExpect(jsonPath("$.description", is(calendarDto.getDescription())));
    }

    @Test
    public void deleteShouldDeleteCalendarWithStatusCodeOk() throws Exception {

        when(calendarService.getCalendar(any(Integer.class))).thenReturn(Mockito.mock(Calendar.class));

        this.mockMvc.perform(delete(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

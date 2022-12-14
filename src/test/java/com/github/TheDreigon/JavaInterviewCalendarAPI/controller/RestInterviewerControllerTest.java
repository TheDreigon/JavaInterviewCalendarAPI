package com.github.TheDreigon.JavaInterviewCalendarAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerToInterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
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
public class RestInterviewerControllerTest {

    @MockBean
    private InterviewerService interviewerService;

    @MockBean
    private InterviewerRepository interviewerRepository;

    @Autowired
    private ObjectMapper mapper;

    private final File file = new File("src/test/resources/database/init/interviewer-valid.json");

    private final String PATH = "/api/interviewers";

    @Mock
    private InterviewerToInterviewerDto interviewerToInterviewerDto;

    @Mock
    private UriComponents uriComponents;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getShouldReturnInterviewerListWithStatusCodeOk() throws Exception {

        InterviewerDto interviewerDto = mapper.readValue(file, InterviewerDto.class);

        Interviewer interviewer = new Interviewer();
        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());

        List<Interviewer> interviewerList = new ArrayList<>();
        interviewerList.add(interviewer);

        when(interviewerService.getInterviewerList()).thenReturn(interviewerList);
        when(interviewerToInterviewerDto.convert(any(Interviewer.class))).thenReturn(interviewerDto);

        this.mockMvc.perform(get(PATH + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(interviewerDto.getName())))
                .andExpect(jsonPath("$[0].description", is(interviewerDto.getDescription())));
    }

    @Test
    public void getShouldReturnInterviewerWithStatusCodeOk() throws Exception {

        InterviewerDto interviewerDto = mapper.readValue(file, InterviewerDto.class);

        Interviewer interviewer = new Interviewer();
        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());

        when(interviewerService.getInterviewer(any(Integer.class))).thenReturn(interviewer);
        when(interviewerToInterviewerDto.convert(any(Interviewer.class))).thenReturn(interviewerDto);

        this.mockMvc.perform(get(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(interviewerDto.getName())))
                .andExpect(jsonPath("$.description", is(interviewerDto.getDescription())));
    }

    @Test
    public void postShouldAddInterviewerWithStatusCodeCreatedRedirect() throws Exception {

        InterviewerDto interviewerDto = mapper.readValue(file, InterviewerDto.class);

        Interviewer interviewer = new Interviewer();
        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());

        when(interviewerService.createInterviewer(any(Interviewer.class))).thenReturn(interviewer);
        interviewer.setId(4);
        when(interviewerToInterviewerDto.convert(any(Interviewer.class))).thenReturn(interviewerDto);

        when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder.build()).thenReturn(uriComponents);
        when(uriComponents.toUri()).thenReturn(URI.create("/api/interviewers/" + interviewer.getId()));

        this.httpHeaders.setLocation(uriComponents.toUri());

        this.mockMvc.perform(post(PATH + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(interviewerDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(interviewerDto.getName())))
                .andExpect(jsonPath("$.description", is(interviewerDto.getDescription())))
                .andExpect(redirectedUrlPattern("http://*/api/interviewers/" + interviewer.getId()));
    }

    @Test
    public void putShouldEditInterviewerWithStatusCodeOk() throws Exception {

        InterviewerDto interviewerDto = mapper.readValue(file, InterviewerDto.class);

        Interviewer interviewer = new Interviewer();
        interviewer.setName(interviewerDto.getName());
        interviewer.setDescription(interviewerDto.getDescription());

        when(interviewerService.getInterviewer(any(Integer.class))).thenReturn(Mockito.mock(Interviewer.class));

        interviewer.setId(4);
        when(interviewerService.updateInterviewer(any(Interviewer.class))).thenReturn(interviewer);
        when(interviewerToInterviewerDto.convert(any(Interviewer.class))).thenReturn(interviewerDto);

        this.mockMvc.perform(put(PATH + "/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(interviewerDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is(interviewerDto.getName())))
                .andExpect(jsonPath("$.description", is(interviewerDto.getDescription())));
    }

    @Test
    public void deleteShouldDeleteInterviewerWithStatusCodeOk() throws Exception {

        when(interviewerService.getInterviewer(any(Integer.class))).thenReturn(Mockito.mock(Interviewer.class));

        this.mockMvc.perform(delete(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

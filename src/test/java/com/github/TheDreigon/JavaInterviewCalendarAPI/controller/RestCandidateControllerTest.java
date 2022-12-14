package com.github.TheDreigon.JavaInterviewCalendarAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateToCandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
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
public class RestCandidateControllerTest {

    @MockBean
    private CandidateService candidateService;

    @MockBean
    private CandidateRepository candidateRepository;

    @Autowired
    private ObjectMapper mapper;

    private final File file = new File("src/test/resources/database/init/candidate-valid.json");

    private final String PATH = "/api/candidates";

    @Mock
    private CandidateToCandidateDto candidateToCandidateDto;

    @Mock
    private UriComponents uriComponents;

    @Mock
    private UriComponentsBuilder uriComponentsBuilder;

    @Mock
    private HttpHeaders httpHeaders;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getShouldReturnCandidateListWithStatusCodeOk() throws Exception {

        CandidateDto candidateDto = mapper.readValue(file, CandidateDto.class);

        Candidate candidate = new Candidate();
        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate);

        when(candidateService.getCandidateList()).thenReturn(candidateList);
        when(candidateToCandidateDto.convert(any(Candidate.class))).thenReturn(candidateDto);

        this.mockMvc.perform(get(PATH + "/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(candidateDto.getName())))
                .andExpect(jsonPath("$[0].description", is(candidateDto.getDescription())));
    }

    @Test
    public void getShouldReturnCandidateWithStatusCodeOk() throws Exception {

        CandidateDto candidateDto = mapper.readValue(file, CandidateDto.class);

        Candidate candidate = new Candidate();
        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        when(candidateService.getCandidate(any(Integer.class))).thenReturn(candidate);
        when(candidateToCandidateDto.convert(any(Candidate.class))).thenReturn(candidateDto);

        this.mockMvc.perform(get(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(candidateDto.getName())))
                .andExpect(jsonPath("$.description", is(candidateDto.getDescription())));
    }

    @Test
    public void postShouldAddCandidateWithStatusCodeCreatedRedirect() throws Exception {

        CandidateDto candidateDto = mapper.readValue(file, CandidateDto.class);

        Candidate candidate = new Candidate();
        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        when(candidateService.createCandidate(any(Candidate.class))).thenReturn(candidate);
        candidate.setId(4);
        when(candidateToCandidateDto.convert(any(Candidate.class))).thenReturn(candidateDto);

        when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder.build()).thenReturn(uriComponents);
        when(uriComponents.toUri()).thenReturn(URI.create("/api/candidates/" + candidate.getId()));

        this.httpHeaders.setLocation(uriComponents.toUri());

        this.mockMvc.perform(post(PATH + "/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(candidateDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.name", is(candidateDto.getName())))
                .andExpect(jsonPath("$.description", is(candidateDto.getDescription())))
                .andExpect(redirectedUrlPattern("http://*/api/candidates/" + candidate.getId()));
    }

    @Test
    public void putShouldEditCandidateWithStatusCodeOk() throws Exception {

        CandidateDto candidateDto = mapper.readValue(file, CandidateDto.class);

        Candidate candidate = new Candidate();
        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        when(candidateService.getCandidate(any(Integer.class))).thenReturn(Mockito.mock(Candidate.class));

        candidate.setId(4);
        when(candidateService.updateCandidate(any(Candidate.class))).thenReturn(candidate);
        when(candidateToCandidateDto.convert(any(Candidate.class))).thenReturn(candidateDto);

        this.mockMvc.perform(put(PATH + "/{id}", 4)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(candidateDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is(candidateDto.getName())))
                .andExpect(jsonPath("$.description", is(candidateDto.getDescription())));
    }

    @Test
    public void deleteShouldDeleteCandidateWithStatusCodeOk() throws Exception {

        when(candidateService.getCandidate(any(Integer.class))).thenReturn(Mockito.mock(Candidate.class));

        this.mockMvc.perform(delete(PATH + "/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

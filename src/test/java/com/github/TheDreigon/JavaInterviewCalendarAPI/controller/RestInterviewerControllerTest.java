package com.github.TheDreigon.JavaInterviewCalendarAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer.InterviewerToInterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
public class RestInterviewerControllerTest {


}

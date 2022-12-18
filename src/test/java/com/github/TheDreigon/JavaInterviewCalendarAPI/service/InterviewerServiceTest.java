package com.github.TheDreigon.JavaInterviewCalendarAPI.service;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl.InterviewerServiceImpl;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
public class InterviewerServiceTest {

    @InjectMocks
    private InterviewerService interviewerService = new InterviewerServiceImpl();

    @Mock
    private InterviewerRepository interviewerRepository;

    /*
    @Test
    public void getInterviewerListShouldReturnInterviewerList() {

        Interviewer interviewer = new Interviewer();
        interviewer.setName("name");
        interviewer.setDescription("description");

        List<Interviewer> interviewerList = new ArrayList<>();
        interviewerList.add(interviewer);

        when(interviewerRepository.findAll()).thenReturn(interviewerList);

        List<Interviewer> interviewerListToAssert = interviewerService.getInterviewerList();

        Assertions.assertEquals(interviewer.getId(), interviewerListToAssert.get(0).getId());
        Assertions.assertEquals(interviewer.getName(), interviewerListToAssert.get(0).getName());
        Assertions.assertEquals(interviewer.getDescription(), interviewerListToAssert.get(0).getDescription());
    }

    @Test
    public void getInterviewerShouldReturnInterviewer() {

        Interviewer interviewer = new Interviewer();
        interviewer.setId(4);
        interviewer.setName("name");
        interviewer.setDescription("description");

        when(interviewerRepository.findById(any(Integer.class))).thenReturn(Optional.of(interviewer));

        Interviewer interviewerToAssert = interviewerService.getInterviewer(interviewer.getId());

        Assertions.assertEquals(interviewer.getId(), interviewerToAssert.getId());
        Assertions.assertEquals(interviewer.getName(), interviewerToAssert.getName());
        Assertions.assertEquals(interviewer.getDescription(), interviewerToAssert.getDescription());
    }

    @Test
    public void createInterviewerShouldReturnCreateAndInterviewer() {

        Interviewer interviewer = new Interviewer();
        interviewer.setName("name");
        interviewer.setDescription("description");

        when(interviewerRepository.save(any(Interviewer.class))).thenReturn(interviewer);

        Interviewer interviewerToAssert = interviewerService.createInterviewer(interviewer);

        Assertions.assertEquals(interviewer.getName(), interviewerToAssert.getName());
        Assertions.assertEquals(interviewer.getDescription(), interviewerToAssert.getDescription());
    }

    @Test
    public void updateInterviewerShouldUpdateAndReturnInterviewer() {

        Interviewer interviewer = new Interviewer();
        interviewer.setId(4);
        interviewer.setName("name");
        interviewer.setDescription("description");

        when(interviewerRepository.findById(any(Integer.class))).thenReturn(Optional.of(interviewer));
        when(interviewerRepository.save(any(Interviewer.class))).thenReturn(interviewer);

        Interviewer interviewerToAssert = interviewerService.updateInterviewer(interviewer);

        Assertions.assertEquals(interviewer.getId(), interviewerToAssert.getId());
        Assertions.assertEquals(interviewer.getName(), interviewerToAssert.getName());
        Assertions.assertEquals(interviewer.getDescription(), interviewerToAssert.getDescription());
    }

    @Test
    public void deleteInterviewerShouldDeleteInterviewer() {

        Interviewer interviewer = new Interviewer();
        interviewer.setId(4);
        interviewer.setName("name");
        interviewer.setDescription("description");

        interviewerService.deleteInterviewer(interviewer.getId());

        verify(interviewerRepository, times(1)).deleteById(interviewer.getId());
    }

     */
}

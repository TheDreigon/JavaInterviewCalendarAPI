package com.github.TheDreigon.JavaInterviewCalendarAPI.service;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl.CandidateServiceImpl;
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
public class CandidateServiceImplTest {

    @InjectMocks
    private CandidateService candidateService = new CandidateServiceImpl();

    @Mock
    private CandidateRepository candidateRepository;

    /*
    @Test
    public void getCandidateListShouldReturnCandidateList() {

        Candidate candidate = new Candidate();
        candidate.setName("name");
        candidate.setDescription("description");

        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(candidate);

        when(candidateRepository.findAll()).thenReturn(candidateList);

        List<Candidate> candidateListToAssert = candidateService.getCandidateList();

        Assertions.assertEquals(candidate.getId(), candidateListToAssert.get(0).getId());
        Assertions.assertEquals(candidate.getName(), candidateListToAssert.get(0).getName());
        Assertions.assertEquals(candidate.getDescription(), candidateListToAssert.get(0).getDescription());
    }

    @Test
    public void getCandidateShouldReturnCandidate() {

        Candidate candidate = new Candidate();
        candidate.setId(4);
        candidate.setName("name");
        candidate.setDescription("description");

        when(candidateRepository.findById(any(Integer.class))).thenReturn(Optional.of(candidate));

        Candidate candidateToAssert = candidateService.getCandidate(candidate.getId());

        Assertions.assertEquals(candidate.getId(), candidateToAssert.getId());
        Assertions.assertEquals(candidate.getName(), candidateToAssert.getName());
        Assertions.assertEquals(candidate.getDescription(), candidateToAssert.getDescription());
    }

    @Test
    public void createCandidateShouldReturnCreateAndCandidate() {

        Candidate candidate = new Candidate();
        candidate.setName("name");
        candidate.setDescription("description");

        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        Candidate candidateToAssert = candidateService.createCandidate(candidate);

        Assertions.assertEquals(candidate.getName(), candidateToAssert.getName());
        Assertions.assertEquals(candidate.getDescription(), candidateToAssert.getDescription());
    }

    @Test
    public void updateCandidateShouldUpdateAndReturnCandidate() {

        Candidate candidate = new Candidate();
        candidate.setId(4);
        candidate.setName("name");
        candidate.setDescription("description");

        when(candidateRepository.findById(any(Integer.class))).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        Candidate candidateToAssert = candidateService.updateCandidate(candidate);

        Assertions.assertEquals(candidate.getId(), candidateToAssert.getId());
        Assertions.assertEquals(candidate.getName(), candidateToAssert.getName());
        Assertions.assertEquals(candidate.getDescription(), candidateToAssert.getDescription());
    }

    @Test
    public void deleteCandidateShouldDeleteCandidate() {

        Candidate candidate = new Candidate();
        candidate.setId(4);
        candidate.setName("name");
        candidate.setDescription("description");

        candidateService.deleteCandidate(candidate.getId());

        verify(candidateRepository, times(1)).deleteById(candidate.getId());
    }

     */
}

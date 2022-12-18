package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Converter} implementation, responsible for {@link CandidateDto} to {@link Candidate} type conversion
 */
@Component
public class CandidateDtoToCandidate implements Converter<CandidateDto, Candidate> {

    @Autowired
    private CandidateAvailabilityDtoToCandidateAvailability candidateAvailabilityDtoToCandidateAvailability;

    /**
     * Converts the candidate DTO into a candidate model object
     *
     * @param candidateDto the candidate DTO to convert
     * @return the candidate
     */
    @Override
    public Candidate convert(CandidateDto candidateDto) {

        Candidate candidate = new Candidate();

        candidate.setName(candidateDto.getName());
        candidate.setDescription(candidateDto.getDescription());

        List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();
        candidateDto.getCandidateAvailabilityDtoList().forEach(e -> candidateAvailabilityList.add(candidateAvailabilityDtoToCandidateAvailability.convert(e)));
        candidate.setCandidateAvailabilityList(candidateAvailabilityList);

        return candidate;
    }
}

package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link Converter} implementation, responsible for {@link Candidate} to {@link CandidateDto} type conversion
 */
@Component
public class CandidateToCandidateDto implements Converter<Candidate, CandidateDto> {

    @Autowired
    private CandidateAvailabilityToAvailabilityDto candidateAvailabilityToAvailabilityDto;

    /**
     * Converts the candidate model object into a candidate DTO
     *
     * @param candidate the candidate to convert
     * @return the candidate DTO
     */
    @Override
    public CandidateDto convert(Candidate candidate) {

        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setId(candidate.getId());
        candidateDto.setName(candidate.getName());
        candidateDto.setDescription(candidate.getDescription());

        List<AvailabilityDto> availabilityDtoList = new ArrayList<>();
        candidate.getCandidateAvailabilityList().forEach(e -> availabilityDtoList.add(candidateAvailabilityToAvailabilityDto.convert(e)));
        candidateDto.setAvailabilityDtoList(availabilityDtoList);

        return candidateDto;
    }
}

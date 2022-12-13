package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Candidate} to {@link CandidateDto} type conversion
 */
@Component
public class CandidateToCandidateDto implements Converter<Candidate, CandidateDto> {

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

        return candidateDto;
    }
}

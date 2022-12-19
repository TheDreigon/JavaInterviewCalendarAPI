package com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDtoNoAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A {@link Converter} implementation, responsible for {@link Candidate} to {@link CandidateDtoNoAvailability} type conversion
 */
@Component
public class CandidateToCandidateDtoNoAvailability implements Converter<Candidate, CandidateDtoNoAvailability> {

    /**
     * Converts the candidate model object into a candidate DTO with no availability list
     *
     * @param candidate the candidate to convert
     * @return the candidate DTO with no availability list
     */
    @Override
    public CandidateDtoNoAvailability convert(Candidate candidate) {

        CandidateDtoNoAvailability candidateDtoNoAvailability = new CandidateDtoNoAvailability();

        candidateDtoNoAvailability.setId(candidate.getId());
        candidateDtoNoAvailability.setName(candidate.getName());
        candidateDtoNoAvailability.setDescription(candidate.getDescription());

        return candidateDtoNoAvailability;
    }
}

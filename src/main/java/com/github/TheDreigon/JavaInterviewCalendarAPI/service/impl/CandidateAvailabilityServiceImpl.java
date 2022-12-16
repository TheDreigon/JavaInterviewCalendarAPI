package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateAvailabilityDtoToCandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateAvailabilityToCandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * REST service responsible for {@link CandidateAvailability} related business logic operations
 */
@Service
public class CandidateAvailabilityServiceImpl implements CandidateAvailabilityService {

    @Autowired
    private CandidateAvailabilityRepository candidateAvailabilityRepository;

    @Autowired
    private CandidateAvailabilityDtoToCandidateAvailability candidateAvailabilityDtoToCandidateAvailability;

    @Autowired
    private CandidateAvailabilityToCandidateAvailabilityDto candidateAvailabilityToCandidateAvailabilityDto;

    /**
     * @see CandidateAvailabilityService#getCandidateAvailabilityList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<CandidateAvailabilityDto> getCandidateAvailabilityList() {
        return null;
    }

    /**
     * @see CandidateAvailabilityService#getCandidateAvailability(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public CandidateAvailabilityDto getCandidateAvailability(Integer id) {
        return null;
    }
}

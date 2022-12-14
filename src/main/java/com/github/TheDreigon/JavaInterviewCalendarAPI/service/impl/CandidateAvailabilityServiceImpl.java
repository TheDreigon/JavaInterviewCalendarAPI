package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * REST service responsible for {@link CandidateAvailability} related business logic operations
 */
@Service
public class CandidateAvailabilityServiceImpl implements CandidateAvailabilityService {

    @Autowired
    private CandidateAvailabilityRepository candidateAvailabilityRepository;

    /**
     * @see CandidateAvailabilityService#getCandidateAvailabilityList()
     */
    @Override
    public List<CandidateAvailability> getCandidateAvailabilityList() {

        return null;
    }

    /**
     * @see CandidateAvailabilityService#getCandidateAvailability(Integer)
     */
    @Override
    public CandidateAvailability getCandidateAvailability(Integer id) {
        return null;
    }
}

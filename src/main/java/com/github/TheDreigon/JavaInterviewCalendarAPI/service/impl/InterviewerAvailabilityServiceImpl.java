package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * REST service responsible for {@link InterviewerAvailability} related business logic operations
 */
@Service
public class InterviewerAvailabilityServiceImpl implements InterviewerAvailabilityService {

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityRepository;
    
    /**
     * @see InterviewerAvailabilityService#getInterviewerAvailabilityList()
     */
    @Override
    public List<InterviewerAvailability> getInterviewerAvailabilityList() {

        return null;
    }

    /**
     * @see InterviewerAvailabilityService#getInterviewerAvailability(Integer) 
     */
    @Override
    public InterviewerAvailability getInterviewerAvailability(Integer id) {
        return null;
    }
}

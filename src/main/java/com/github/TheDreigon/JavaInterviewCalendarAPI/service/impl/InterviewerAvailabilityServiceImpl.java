package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityDtoToInterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityToInterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * REST service responsible for {@link InterviewerAvailability} related business logic operations
 */
@Service
public class InterviewerAvailabilityServiceImpl implements InterviewerAvailabilityService {

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityRepository;

    @Autowired
    private InterviewerAvailabilityDtoToInterviewerAvailability interviewerAvailabilityDtoToInterviewerAvailability;

    @Autowired
    private InterviewerAvailabilityToInterviewerAvailabilityDto interviewerAvailabilityToInterviewerAvailabilityDto;
    
    /**
     * @see InterviewerAvailabilityService#getInterviewerAvailabilityList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<InterviewerAvailabilityDto> getInterviewerAvailabilityList() {
        return null;
    }

    /**
     * @see InterviewerAvailabilityService#getInterviewerAvailability(Integer) 
     */
    @Transactional(readOnly = true)
    @Override
    public InterviewerAvailabilityDto getInterviewerAvailability(Integer id) {
        return null;
    }
}

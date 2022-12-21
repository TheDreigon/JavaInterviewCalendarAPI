package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer.InterviewerAvailabilityDtoToInterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer.InterviewerAvailabilityToInterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * REST service responsible for {@link InterviewerAvailability} related business logic operations
 */
@Service
public class InterviewerAvailabilityServiceImpl implements InterviewerAvailabilityService {

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityDao;

    @Autowired
    private InterviewerRepository interviewerDao;

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

        List<InterviewerAvailabilityDto> interviewerAvailabilityDtoList = new ArrayList<>();

        for (InterviewerAvailability interviewerAvailability : interviewerAvailabilityDao.findAll()) {
            interviewerAvailabilityDtoList.add(interviewerAvailabilityToInterviewerAvailabilityDto.convert(interviewerAvailability));
        }

        return interviewerAvailabilityDtoList;
    }

    /**
     * @see InterviewerAvailabilityService#getInterviewerAvailability(Integer, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public InterviewerAvailabilityDto getInterviewerAvailability(Integer iId, Integer iaId) throws InterviewerNotFoundException, AvailabilityNotFoundException {

        interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);
        InterviewerAvailability retrievedInterviewerAvailability = interviewerAvailabilityDao.findById(iaId).orElseThrow(AvailabilityNotFoundException::new);

        return interviewerAvailabilityToInterviewerAvailabilityDto.convert(retrievedInterviewerAvailability);
    }
}

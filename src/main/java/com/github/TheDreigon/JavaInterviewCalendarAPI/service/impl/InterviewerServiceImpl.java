package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerDtoToInterviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerToInterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * REST service responsible for {@link Interviewer} related business logic operations
 */
@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    private InterviewerRepository interviewerDao;

    @Autowired
    private InterviewerDtoToInterviewer interviewerDtoToInterviewer;

    @Autowired
    private InterviewerToInterviewerDto interviewerToInterviewerDto;

    /**
     * @see InterviewerService#getInterviewerList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<InterviewerDto> getInterviewerList() {
        return interviewerDao.findAll();
    }

    /**
     * @see InterviewerService#getInterviewer(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public InterviewerDto getInterviewer(Integer id) {
        return interviewerDao.findById(id).orElse(null);
    }

    /**
     * @see InterviewerService#createInterviewer(Interviewer)
     */
    @Transactional
    @Override
    public InterviewerDto createInterviewer(InterviewerDto interviewerDto) {
        return interviewerDao.save(interviewerDto);
    }

    /**
     * @see InterviewerService#updateInterviewer(Interviewer)
     */
    @Transactional
    @Override
    public InterviewerDto updateInterviewer(InterviewerDto interviewerDto) {
        Interviewer interviewerFromDB = interviewerDao.findById(interviewer.getId()).orElse(null);
        if (interviewerFromDB != null) {
            interviewerFromDB.setName(interviewer.getName());
            interviewerFromDB.setDescription(interviewer.getDescription());
            return interviewerDao.save(interviewerFromDB);
        }
        return null;
    }

    /**
     * @see InterviewerService#deleteInterviewer(Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewer(Integer id) {
        interviewerDao.deleteById(id);
    }

    /**
     * @see InterviewerService#createInterviewerAvailability(InterviewerAvailabilityDto)
     */
    @Transactional
    @Override
    public InterviewerAvailabilityDto createInterviewerAvailability(InterviewerAvailabilityDto interviewerAvailabilityDto) {

        return null;
    }

    /**
     * @see InterviewerService#deleteInterviewerAvailability(Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewerAvailability(Integer id) {


    }
}

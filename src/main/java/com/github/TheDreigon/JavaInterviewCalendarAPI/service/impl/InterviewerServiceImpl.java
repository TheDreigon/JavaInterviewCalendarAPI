package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.InterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityDtoToInterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerAvailabilityToInterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerDtoToInterviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.InterviewerToInterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.InterviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * REST service responsible for {@link Interviewer} related business logic operations
 */
@Service
public class InterviewerServiceImpl implements InterviewerService {

    @Autowired
    private InterviewerRepository interviewerDao;

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityDao;

    @Autowired
    private InterviewerDtoToInterviewer interviewerDtoToInterviewer;

    @Autowired
    private InterviewerToInterviewerDto interviewerToInterviewerDto;

    @Autowired
    private InterviewerAvailabilityDtoToInterviewerAvailability interviewerAvailabilityDtoToInterviewerAvailability;

    @Autowired
    private InterviewerAvailabilityToInterviewerAvailabilityDto interviewerAvailabilityToInterviewerAvailabilityDto;

    /**
     * @see InterviewerService#getInterviewerList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<InterviewerDto> getInterviewerList() {

        List<InterviewerDto> interviewerDtoList = new ArrayList<>();

        for (Interviewer interviewer : interviewerDao.findAll()) {
            interviewerDtoList.add(interviewerToInterviewerDto.convert(interviewer));
        }

        return interviewerDtoList;
    }

    /**
     * @see InterviewerService#getInterviewer(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public InterviewerDto getInterviewer(Integer id) throws InterviewerNotFoundException {

        Interviewer retrievedInterviewer = interviewerDao.findById(id).orElseThrow(InterviewerNotFoundException::new);

        return interviewerToInterviewerDto.convert(retrievedInterviewer);
    }

    /**
     * @see InterviewerService#createInterviewer(InterviewerDto)
     */
    @Transactional
    @Override
    public InterviewerDto createInterviewer(InterviewerDto interviewerDto) {

        Interviewer createdInterviewer = interviewerDao.save(Objects.requireNonNull(interviewerDtoToInterviewer.convert(interviewerDto)));

        return interviewerToInterviewerDto.convert(createdInterviewer);
    }

    /**
     * @see InterviewerService#updateInterviewer(InterviewerDto, Integer)
     */
    @Transactional
    @Override
    public InterviewerDto updateInterviewer(InterviewerDto interviewerDto, Integer id) throws InterviewerNotFoundException {

        Interviewer retrievedInterviewer = interviewerDao.findById(id).orElseThrow(InterviewerNotFoundException::new);

        retrievedInterviewer.setName(interviewerDto.getName());
        retrievedInterviewer.setDescription(interviewerDto.getDescription());

        return interviewerToInterviewerDto.convert(interviewerDao.save((retrievedInterviewer)));
    }

    /**
     * @see InterviewerService#deleteInterviewer(Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewer(Integer id) throws InterviewerNotFoundException {

        interviewerDao.findById(id).orElseThrow(InterviewerNotFoundException::new);

        interviewerDao.deleteById(id);
    }

    /**
     * @see InterviewerService#createInterviewerAvailability(InterviewerAvailabilityDto)
     */
    @Transactional
    @Override
    public InterviewerAvailabilityDto createInterviewerAvailability(InterviewerAvailabilityDto interviewerAvailabilityDto) {

        InterviewerAvailability createdInterviewerAvailability = interviewerAvailabilityDao.save(
                Objects.requireNonNull(interviewerAvailabilityDtoToInterviewerAvailability.convert(interviewerAvailabilityDto)));

        return interviewerAvailabilityToInterviewerAvailabilityDto.convert(createdInterviewerAvailability);
    }

    /**
     * @see InterviewerService#deleteInterviewerAvailability(Integer, Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewerAvailability(Integer iId, Integer iaId) throws InterviewerNotFoundException, AvailabilityNotFoundException {

        interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);
        interviewerAvailabilityDao.findById(iaId).orElseThrow(AvailabilityNotFoundException::new);

        interviewerDao.deleteById(iaId);
    }
}

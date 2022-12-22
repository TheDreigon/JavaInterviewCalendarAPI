package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.InterviewerAvailabilityDtoToInterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.InterviewerAvailabilityToInterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer.InterviewerDtoToInterviewer;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.interviewer.InterviewerToInterviewerDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.InterviewerAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.interviewer.InterviewerDto;
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
import java.util.Iterator;
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
    public InterviewerDto getInterviewer(Integer iId) throws InterviewerNotFoundException {

        Interviewer retrievedInterviewer = interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);

        return interviewerToInterviewerDto.convert(retrievedInterviewer);
    }

    /**
     * @see InterviewerService#createInterviewer(InterviewerDto)
     */
    @Transactional
    @Override
    public InterviewerDto createInterviewer(InterviewerDto interviewerDto) {

        interviewerDto.setAvailabilityDtoList(new ArrayList<>());
        Interviewer createdInterviewer = interviewerDao.save(Objects.requireNonNull(interviewerDtoToInterviewer.convert(interviewerDto)));

        return interviewerToInterviewerDto.convert(createdInterviewer);
    }

    /**
     * @see InterviewerService#updateInterviewer(Integer, InterviewerDto)
     */
    @Transactional
    @Override
    public InterviewerDto updateInterviewer(Integer iId, InterviewerDto interviewerDto) throws InterviewerNotFoundException {

        Interviewer retrievedInterviewer = interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);

        retrievedInterviewer.setName(interviewerDto.getName());
        retrievedInterviewer.setDescription(interviewerDto.getDescription());

        return interviewerToInterviewerDto.convert(interviewerDao.save((retrievedInterviewer)));
    }

    /**
     * @see InterviewerService#deleteInterviewer(Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewer(Integer iId) throws InterviewerNotFoundException {

        interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);

        interviewerDao.deleteById(iId);
    }

    /**
     * @see InterviewerService#getInterviewerAvailabilities(Integer) 
     */
    @Transactional(readOnly = true)
    @Override
    public List<InterviewerAvailabilityDto> getInterviewerAvailabilities(Integer iId) throws InterviewerNotFoundException {

        interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);

        List<InterviewerAvailabilityDto> interviewerAvailabilityDtoList = new ArrayList<>();

        for (InterviewerAvailability interviewerAvailability : interviewerAvailabilityDao.findAll()) {
            if (Objects.equals(interviewerAvailability.getInterviewer().getId(), iId)) {
                interviewerAvailabilityDtoList.add(interviewerAvailabilityToInterviewerAvailabilityDto.convert(interviewerAvailability));
            }
        }

        return interviewerAvailabilityDtoList;
    }
    
    /**
     * @see InterviewerService#createInterviewerAvailability(Integer, InterviewerAvailabilityDto)
     */
    @Transactional
    @Override
    public InterviewerAvailabilityDto createInterviewerAvailability(Integer iId, InterviewerAvailabilityDto interviewerAvailabilityDto) throws InterviewerNotFoundException {

        InterviewerAvailability createdInterviewerAvailability = interviewerAvailabilityDao.save(
                Objects.requireNonNull(interviewerAvailabilityDtoToInterviewerAvailability.convert(interviewerAvailabilityDto)));

        (interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new)).addInterviewerAvailabilitySlot(createdInterviewerAvailability);

        return interviewerAvailabilityToInterviewerAvailabilityDto.convert(createdInterviewerAvailability);
    }

    /**
     * @see InterviewerService#deleteInterviewerAvailability(Integer, Integer)
     */
    @Transactional
    @Override
    public void deleteInterviewerAvailability(Integer iId, Integer iaId)
            throws InterviewerNotFoundException, AvailabilityNotFoundException {

        Interviewer interviewer = interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new);
        interviewerAvailabilityDao.findById(iaId).orElseThrow(AvailabilityNotFoundException::new);

        for (Iterator<InterviewerAvailability> iterator = interviewer.getInterviewerAvailabilityList().iterator(); iterator.hasNext();) {
            InterviewerAvailability interviewerAvailability= iterator.next();

            if (Objects.equals(interviewerAvailability.getId(), iaId)) {
                iterator.remove();
                interviewerAvailabilityDao.deleteById(iaId);
            }
        }
    }
}

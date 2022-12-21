package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.CandidateAvailabilityToAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.InterviewerAvailabilityToAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.AvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.InterviewerNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.InterviewerRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.AvailabilityOverlapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * REST service responsible for {@link CandidateAvailability} related business logic operations
 */
@Service
public class AvailabilityOverlapServiceImpl implements AvailabilityOverlapService {

    @Autowired
    private CandidateAvailabilityRepository candidateAvailabilityDao;

    @Autowired
    private CandidateRepository candidateDao;

    @Autowired
    private InterviewerAvailabilityRepository interviewerAvailabilityDao;

    @Autowired
    private InterviewerRepository interviewerDao;

    @Autowired
    private CandidateAvailabilityToAvailabilityDto candidateAvailabilityToAvailabilityDto;

    @Autowired
    private InterviewerAvailabilityToAvailabilityDto interviewerAvailabilityToAvailabilityDto;

    /**
     * @see AvailabilityOverlapService#getAllOverlappingAvailabilities()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AvailabilityDto> getAllOverlappingAvailabilities() {

        List<AvailabilityDto> availabilityDtoList = new ArrayList<>();

        for (CandidateAvailability candidateAvailability : candidateAvailabilityDao.findAll()) {
            for (InterviewerAvailability interviewerAvailability : interviewerAvailabilityDao.findAll()) {
                if (Objects.equals(candidateAvailabilityToAvailabilityDto.convert(candidateAvailability),
                        interviewerAvailabilityToAvailabilityDto.convert(interviewerAvailability))) {
                    availabilityDtoList.add(candidateAvailabilityToAvailabilityDto.convert(candidateAvailability));
                }
            }
        }

        return availabilityDtoList;
    }

    /**
     * @see AvailabilityOverlapService#getCandidateInterviewerOverlappingAvailabilities(Integer, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public List<AvailabilityDto> getCandidateInterviewerOverlappingAvailabilities(Integer cId, Integer iId) throws CandidateNotFoundException, InterviewerNotFoundException {

        List<AvailabilityDto> availabilityDtoList = new ArrayList<>();

        for (CandidateAvailability candidateAvailability : candidateDao.findById(cId).orElseThrow(CandidateNotFoundException::new).getCandidateAvailabilityList()) {
            for (InterviewerAvailability interviewerAvailability : interviewerDao.findById(iId).orElseThrow(InterviewerNotFoundException::new).getInterviewerAvailabilityList()) {
                if (Objects.equals(candidateAvailabilityToAvailabilityDto.convert(candidateAvailability),
                        interviewerAvailabilityToAvailabilityDto.convert(interviewerAvailability))) {
                    availabilityDtoList.add(candidateAvailabilityToAvailabilityDto.convert(candidateAvailability));
                }
            }
        }

        return availabilityDtoList;
    }
}

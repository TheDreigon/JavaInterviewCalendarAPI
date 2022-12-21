package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.CandidateAvailabilityToCandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * REST service responsible for {@link CandidateAvailability} related business logic operations
 */
@Service
public class CandidateAvailabilityServiceImpl implements CandidateAvailabilityService {

    @Autowired
    private CandidateAvailabilityRepository candidateAvailabilityDao;

    @Autowired
    private CandidateRepository candidateDao;

    @Autowired
    private CandidateAvailabilityToCandidateAvailabilityDto candidateAvailabilityToCandidateAvailabilityDto;

    /**
     * @see CandidateAvailabilityService#getCandidateAvailabilityList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<CandidateAvailabilityDto> getCandidateAvailabilityList() {

        List<CandidateAvailabilityDto> candidateAvailabilityDtoList = new ArrayList<>();

        for (CandidateAvailability candidateAvailability : candidateAvailabilityDao.findAll()) {
            candidateAvailabilityDtoList.add(candidateAvailabilityToCandidateAvailabilityDto.convert(candidateAvailability));
        }

        return candidateAvailabilityDtoList;
    }

    /**
     * @see CandidateAvailabilityService#getCandidateAvailability(Integer, Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public CandidateAvailabilityDto getCandidateAvailability(Integer cId, Integer caId) throws CandidateNotFoundException, AvailabilityNotFoundException {

        candidateDao.findById(cId).orElseThrow(CandidateNotFoundException::new);
        CandidateAvailability retrievedCandidateAvailability = candidateAvailabilityDao.findById(caId).orElseThrow(AvailabilityNotFoundException::new);

        return candidateAvailabilityToCandidateAvailabilityDto.convert(retrievedCandidateAvailability);
    }

    /**
     * @see CandidateAvailabilityService#updateCandidateAvailability(Integer, Integer, CandidateAvailabilityDto)
     */
    @Transactional
    @Override
    public CandidateAvailabilityDto updateCandidateAvailability(Integer cId, Integer caId, CandidateAvailabilityDto candidateAvailabilityDto)
            throws CandidateNotFoundException, AvailabilityNotFoundException {

        candidateDao.findById(cId).orElseThrow(CandidateNotFoundException::new);
        CandidateAvailability retrievedCandidateAvailability = candidateAvailabilityDao.findById(caId).orElseThrow(AvailabilityNotFoundException::new);

        retrievedCandidateAvailability.setDayDate(candidateAvailabilityDto.getDayDate());
        retrievedCandidateAvailability.setAvailableHour(candidateAvailabilityDto.getAvailableHour());
        retrievedCandidateAvailability.setDayOfWeek(candidateAvailabilityDto.getDayOfWeek());

        return candidateAvailabilityToCandidateAvailabilityDto.convert(candidateAvailabilityDao.save(retrievedCandidateAvailability));
    }
}

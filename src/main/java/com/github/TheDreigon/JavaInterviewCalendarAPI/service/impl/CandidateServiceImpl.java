package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.CandidateAvailabilityDtoToCandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.availability.CandidateAvailabilityToCandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.candidate.CandidateDtoToCandidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.converter.candidate.CandidateToCandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.availability.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.candidate.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.AvailabilityNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.exception.CandidateNotFoundException;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateAvailabilityRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * REST service responsible for {@link Candidate} related business logic operations
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateDao;

    @Autowired
    private CandidateAvailabilityRepository candidateAvailabilityDao;

    @Autowired
    private CandidateDtoToCandidate candidateDtoToCandidate;

    @Autowired
    private CandidateToCandidateDto candidateToCandidateDto;

    @Autowired
    private CandidateAvailabilityDtoToCandidateAvailability candidateAvailabilityDtoToCandidateAvailability;

    @Autowired
    private CandidateAvailabilityToCandidateAvailabilityDto candidateAvailabilityToCandidateAvailabilityDto;

    /**
     * @see CandidateService#getCandidateList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<CandidateDto> getCandidateList() {

        List<CandidateDto> candidateDtoList = new ArrayList<>();

        for (Candidate candidate : candidateDao.findAll()) {
            candidateDtoList.add(candidateToCandidateDto.convert(candidate));
        }

        return candidateDtoList;
    }

    /**
     * @see CandidateService#getCandidate(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public CandidateDto getCandidate(Integer id) throws CandidateNotFoundException {

        Candidate retrievedCandidate = candidateDao.findById(id).orElseThrow(CandidateNotFoundException::new);

        return candidateToCandidateDto.convert(retrievedCandidate);
    }

    /**
     * @see CandidateService#createCandidate(CandidateDto)
     */
    @Transactional
    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {

        Candidate createdCandidate = candidateDao.save(Objects.requireNonNull(candidateDtoToCandidate.convert(candidateDto)));

        return candidateToCandidateDto.convert(createdCandidate);
    }

    /**
     * @see CandidateService#updateCandidate(CandidateDto, Integer)
     */
    @Transactional
    @Override
    public CandidateDto updateCandidate(CandidateDto candidateDto, Integer id) throws CandidateNotFoundException {

        Candidate retrievedCandidate = candidateDao.findById(id).orElseThrow(CandidateNotFoundException::new);

        retrievedCandidate.setName(candidateDto.getName());
        retrievedCandidate.setDescription(candidateDto.getDescription());

        return candidateToCandidateDto.convert(candidateDao.save(retrievedCandidate));
    }

    /**
     * @see CandidateService#deleteCandidate(Integer)
     */
    @Transactional
    @Override
    public void deleteCandidate(Integer id) throws CandidateNotFoundException {

        candidateDao.findById(id).orElseThrow(CandidateNotFoundException::new);

        candidateDao.deleteById(id);
    }

    /**
     * @see CandidateService#createCandidateAvailability(Integer, CandidateAvailabilityDto)
     */
    @Transactional
    @Override
    public CandidateAvailabilityDto createCandidateAvailability(Integer cId, CandidateAvailabilityDto candidateAvailabilityDto) throws CandidateNotFoundException {

        CandidateAvailability createdCandidateAvailability = candidateAvailabilityDao.save(
                Objects.requireNonNull(candidateAvailabilityDtoToCandidateAvailability.convert(candidateAvailabilityDto)));

        (candidateDao.findById(cId).orElseThrow(CandidateNotFoundException::new)).addCandidateAvailability(createdCandidateAvailability);

        return candidateAvailabilityToCandidateAvailabilityDto.convert(createdCandidateAvailability);
    }

    /**
     * @see CandidateService#deleteCandidateAvailability(Integer, Integer)
     */
    @Transactional
    @Override
    public void deleteCandidateAvailability(Integer cId, Integer caId) throws CandidateNotFoundException, AvailabilityNotFoundException {

        candidateDao.findById(cId).orElseThrow(CandidateNotFoundException::new);
        candidateAvailabilityDao.findById(caId).orElseThrow(AvailabilityNotFoundException::new);

        candidateDao.deleteById(caId);
    }
}

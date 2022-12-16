package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateAvailabilityDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.CandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateDtoToCandidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.dto.converter.CandidateToCandidateDto;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository.CandidateRepository;
import com.github.TheDreigon.JavaInterviewCalendarAPI.service.api.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * REST service responsible for {@link Candidate} related business logic operations
 */
@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateDao;

    @Autowired
    private CandidateDtoToCandidate candidateDtoToCandidate;

    @Autowired
    private CandidateToCandidateDto candidateToCandidateDto;

    /**
     * @see CandidateService#getCandidateList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<CandidateDto> getCandidateList() {
        return candidateDao.findAll();
    }

    /**
     * @see CandidateService#getCandidate(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public CandidateDto getCandidate(Integer id) {
        return candidateDao.findById(id).orElse(null);
    }

    /**
     * @see CandidateService#createCandidate(CandidateDto)
     */
    @Transactional
    @Override
    public CandidateDto createCandidate(CandidateDto candidateDto) {
        return candidateDao.save(candidateDto);
    }

    /**
     * @see CandidateService#updateCandidate(CandidateDto)
     */
    @Transactional
    @Override
    public CandidateDto updateCandidate(CandidateDto candidateDto) {
        Candidate candidateFromDB = candidateDao.findById(candidateDto.getId()).orElse(null);
        if (candidateFromDB != null) {
            candidateFromDB.setName(candidateDto.getName());
            candidateFromDB.setDescription(candidateDto.getDescription());
            return candidateDao.save(candidateFromDB);
        }
        return null;
    }

    /**
     * @see CandidateService#deleteCandidate(Integer)
     */
    @Transactional
    @Override
    public void deleteCandidate(Integer id) {
        candidateDao.deleteById(id);
    }

    /**
     * @see CandidateService#createCandidateAvailability(CandidateAvailabilityDto)
     */
    @Transactional
    @Override
    public CandidateAvailabilityDto createCandidateAvailability(CandidateAvailabilityDto candidateAvailabilityDto) {

        return null;
    }

    /**
     * @see CandidateService#deleteCandidateAvailability(Integer)
     */
    @Transactional
    @Override
    public void deleteCandidateAvailability(Integer id) {


    }
}

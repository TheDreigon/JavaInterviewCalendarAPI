package com.github.TheDreigon.JavaInterviewCalendarAPI.service.impl;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
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

    /**
     * @see CandidateService#getCandidateList()
     */
    @Transactional(readOnly = true)
    @Override
    public List<Candidate> getCandidateList() {
        return candidateDao.findAll();
    }

    /**
     * @see CandidateService#getCandidate(Integer)
     */
    @Transactional(readOnly = true)
    @Override
    public Candidate getCandidate(Integer id) {
        return candidateDao.findById(id).orElse(null);
    }

    /**
     * @see CandidateService#createCandidate(Candidate)
     */
    @Transactional
    @Override
    public Candidate createCandidate(Candidate candidate) {
        return candidateDao.save(candidate);
    }

    /**
     * @see CandidateService#updateCandidate(Candidate)
     */
    @Transactional
    @Override
    public Candidate updateCandidate(Candidate candidate) {
        Candidate candidateFromDB = candidateDao.findById(candidate.getId()).orElse(null);
        if (candidateFromDB != null) {
            candidateFromDB.setName(candidate.getName());
            candidateFromDB.setDescription(candidate.getDescription());
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
     * @see CandidateService#createCandidateAvailability(CandidateAvailability)
     */
    @Override
    public CandidateAvailability createCandidateAvailability(CandidateAvailability candidateAvailability) {

        return null;
    }

    /**
     * @see CandidateService#deleteCandidateAvailability(Integer)
     */
    @Override
    public void deleteCandidateAvailability(Integer id) {


    }
}

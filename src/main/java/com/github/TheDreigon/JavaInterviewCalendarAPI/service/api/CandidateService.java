package com.github.TheDreigon.JavaInterviewCalendarAPI.service.api;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;

import java.util.List;

/**
 * Common interface for candidate services, provides methods to manage candidates
 */
public interface CandidateService {

    /**
     * Get all candidates
     *
     * @return all candidates
     */
    List<Candidate> getCandidateList();

    /**
     * Retrieves a candidate object for the given candidate ID
     *
     * @param id the candidate ID
     * @return the candidate object
     */
    Candidate getCandidate(Integer id);

    /**
     * Creates a candidate object
     *
     * @param candidate the candidate object
     * @return the created candidate object
     */
    Candidate createCandidate(Candidate candidate);

    /**
     * Updates a candidate object
     *
     * @param candidate the candidate object
     * @return the updated candidate object
     */
    Candidate updateCandidate(Candidate candidate);

    /**
     * Deletes a candidate object for the given candidate ID
     *
     * @param id the candidate ID
     */
    void deleteCandidate(Integer id);

    /**
     * Creates a candidateAvailability object
     *
     * @param candidateAvailability the candidateAvailability object
     * @return the created candidateAvailability object
     */
    CandidateAvailability createCandidateAvailability(CandidateAvailability candidateAvailability);

    /**
     * Deletes a candidateAvailability object for the given candidateAvailability ID
     *
     * @param id the candidateAvailability ID
     */
    void deleteCandidateAvailability(Integer id);
}

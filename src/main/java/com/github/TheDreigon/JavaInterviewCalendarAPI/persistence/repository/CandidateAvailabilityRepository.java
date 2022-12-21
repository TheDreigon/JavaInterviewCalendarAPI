package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.CandidateAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REST repository responsible for {@link CandidateAvailability} related database operations
 */
public interface CandidateAvailabilityRepository extends JpaRepository<CandidateAvailability, Integer> {
}

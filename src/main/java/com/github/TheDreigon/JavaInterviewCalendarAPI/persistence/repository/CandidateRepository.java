package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REST repository responsible for {@link Candidate} related database operations
 */
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}

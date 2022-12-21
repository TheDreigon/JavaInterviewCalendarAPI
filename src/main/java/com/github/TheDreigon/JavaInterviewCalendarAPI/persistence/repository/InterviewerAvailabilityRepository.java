package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.InterviewerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REST repository responsible for {@link InterviewerAvailability} related database operations
 */
public interface InterviewerAvailabilityRepository extends JpaRepository<InterviewerAvailability, Integer> {
}

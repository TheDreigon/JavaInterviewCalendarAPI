package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Interviewer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REST repository responsible for {@link Interviewer} related database operations
 */
public interface InterviewerRepository extends JpaRepository<Interviewer, Integer> {

}

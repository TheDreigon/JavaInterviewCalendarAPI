package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * REST repository responsible for {@link Calendar} related database operations
 */
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

}

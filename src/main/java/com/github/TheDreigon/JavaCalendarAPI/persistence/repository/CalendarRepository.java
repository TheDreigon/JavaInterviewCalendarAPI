package com.github.TheDreigon.JavaCalendarAPI.persistence.repository;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

    
}

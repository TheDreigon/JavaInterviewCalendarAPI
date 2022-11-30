package com.github.TheDreigon.JavaCalendarAPI.service;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public List<Calendar> getCalendarList() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar getCalendar(Integer id) {
        return calendarRepository.findById(id).orElse(null);
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Override
    public Calendar updateCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    @Override
    public void deleteCalendar(Integer id) {
        calendarRepository.deleteById(id);
    }
}

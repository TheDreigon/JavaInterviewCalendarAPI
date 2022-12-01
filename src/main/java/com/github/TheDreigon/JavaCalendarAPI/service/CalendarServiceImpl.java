package com.github.TheDreigon.JavaCalendarAPI.service;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarDao;

    @Override
    public List<Calendar> getCalendarList() {
        return calendarDao.findAll();
    }

    @Override
    public Calendar getCalendar(Integer id) {
        return calendarDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarDao.save(calendar);
    }

    @Transactional
    @Override
    public Calendar updateCalendar(Calendar calendar) {

        Calendar calendarFromDB = calendarDao.findById(calendar.getId()).orElse(null);
        if (calendarFromDB != null) {
            calendarFromDB.setName(calendar.getName());
            calendarFromDB.setDescription(calendar.getDescription());
            return calendarDao.save(calendarFromDB);
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteCalendar(Integer id) {
        calendarDao.deleteById(id);
    }
}

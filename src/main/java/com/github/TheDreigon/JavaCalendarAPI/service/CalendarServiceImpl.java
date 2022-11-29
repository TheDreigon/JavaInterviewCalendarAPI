package com.github.TheDreigon.JavaCalendarAPI.service;

import com.github.TheDreigon.JavaCalendarAPI.persistence.dao.CalendarDao;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Autowired
    private CalendarDao calendarDao;

    @Override
    public List<Calendar> getCalendarList() {
        return calendarDao.findAll();
    }

    @Override
    public Calendar getCalendar(Integer id) {
        return calendarDao.findById(id);
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        return calendarDao.saveOrUpdate(calendar);
    }

    @Override
    public Calendar updateCalendar(Calendar calendar) {
        return calendarDao.saveOrUpdate(calendar);
    }

    @Override
    public void deleteCalendar(Integer id) {
        calendarDao.delete(id);
    }
}

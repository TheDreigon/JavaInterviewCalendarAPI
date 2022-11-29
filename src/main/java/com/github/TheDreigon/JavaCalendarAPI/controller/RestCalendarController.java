package com.github.TheDreigon.JavaCalendarAPI.controller;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for {@link Calendar} related CRUD operations
 */
@RestController
@RequestMapping("/api/calendar")
public class RestCalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/")
    public List<Calendar> getCalendars() {
        return calendarService.getCalendarList();
    }

    @GetMapping("/{id}")
    public Calendar getCalendarById(@PathVariable("id") Integer id) {
        return calendarService.getCalendar(id);
    }

    @PostMapping("/")
    public Calendar createCalendar(@RequestBody Calendar calendar) {
        return calendarService.createCalendar(calendar);
    }

    @PutMapping("/")
    public Calendar updateCalendar(@RequestBody Calendar calendar) {
        return calendarService.updateCalendar(calendar);
    }

    @DeleteMapping("/{id}")
    public void deleteCalendar(@PathVariable("id") Integer id) {
        calendarService.deleteCalendar(id);
    }
}

package com.github.TheDreigon.JavaCalendarAPI.controller;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for {@link Calendar} related CRUD operations
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/calendars")
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
    public ResponseEntity<Calendar> createCalendar(@RequestBody Calendar calendar) {

        try {
            calendarService.createCalendar(calendar);
            return ResponseEntity.ok(calendar);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

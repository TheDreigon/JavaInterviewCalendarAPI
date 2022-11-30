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
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/calendars")
public class RestCalendarController {

    @Autowired
    private CalendarService calendarService;

    @GetMapping("/")
    public ResponseEntity<List<Calendar>> getCalendars() {

        try {
            calendarService.getCalendarList();
            return new ResponseEntity<>(calendarService.getCalendarList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendar> getCalendarById(@PathVariable("id") Integer id) {

        try {
            calendarService.getCalendar(id);
            return new ResponseEntity<>(calendarService.getCalendar(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Calendar> createCalendar(@RequestBody Calendar calendar) {

        try {
            calendarService.createCalendar(calendar);
            return new ResponseEntity<>(calendar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Calendar> updateCalendar(@PathVariable("id") Integer id, @RequestBody Calendar calendar) {

        try {
            calendarService.updateCalendar(calendar);
            return new ResponseEntity<>(calendar, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCalendar(@PathVariable("id") Integer id) {

        try {
            calendarService.deleteCalendar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

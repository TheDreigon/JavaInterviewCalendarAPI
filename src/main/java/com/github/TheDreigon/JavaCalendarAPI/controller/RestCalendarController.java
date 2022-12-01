package com.github.TheDreigon.JavaCalendarAPI.controller;

import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarDtoToCalendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST controller responsible for {@link Calendar} related CRUD operations
 */
//@CrossOrigin(origins = "http://localhost:8080")
@Slf4j
@RestController
@RequestMapping("/api/calendars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Validated
@RestControllerAdvice
public class RestCalendarController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarDtoToCalendar calendarDtoToCalendar;

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
    //TODO: maybe change to PatchMapping. In case of a PATCH request, we only send the data we want to modify.
    //TODO: in any case, PUT seems to now be working fine. move on to the next method
    public ResponseEntity<CalendarDto> updateCalendar(@PathVariable("id") Integer id, @Valid @RequestBody CalendarDto calendarDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        if (calendarService.getCalendar(id) == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        //if (calendarService.getCalendar(id) != null && !calendarDto.getId().equals(id)) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        calendarDto.setId(id);

        try {
            calendarService.updateCalendar(calendarDtoToCalendar.convert(calendarDto));
            return new ResponseEntity<>(calendarDto, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
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

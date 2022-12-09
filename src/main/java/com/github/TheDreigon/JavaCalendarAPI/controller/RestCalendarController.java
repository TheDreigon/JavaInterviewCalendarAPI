package com.github.TheDreigon.JavaCalendarAPI.controller;

import com.github.TheDreigon.JavaCalendarAPI.dto.CalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarDtoToCalendar;
import com.github.TheDreigon.JavaCalendarAPI.dto.converter.CalendarToCalendarDto;
import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller responsible for {@link Calendar} related CRUD operations
 */
@Slf4j
@RestController
@RequestMapping("/api/calendar")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestCalendarController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarDtoToCalendar calendarDtoToCalendar;

    @Autowired
    private CalendarToCalendarDto calendarToCalendarDto;

    /**
     * Retrieves a representation of the list of calendars
     *
     * @return the response entity
     */
    @GetMapping("/")
    public ResponseEntity<List<CalendarDto>> getCalendars() {

        log.info("GetAll Method called");

        List<CalendarDto> calendarDtoList = new ArrayList<>();

        try {
            for (Calendar savedCalendar : calendarService.getCalendarList()) {
                CalendarDto resultingCalendarDto = calendarToCalendarDto.convert(savedCalendar);
                calendarDtoList.add(resultingCalendarDto);
            }

            return new ResponseEntity<>(calendarDtoList, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a representation of the given calendar
     *
     * @param id the calendar id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<CalendarDto> getCalendarById(@PathVariable("id") Integer id) {

        log.info("Get Method called");

        if (calendarService.getCalendar(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Calendar savedCalendar = calendarService.getCalendar(id);
            CalendarDto resultingCalendarDto = calendarToCalendarDto.convert(savedCalendar);

            return new ResponseEntity<>(resultingCalendarDto, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a calendar
     *
     * @param calendarDto          the calendar DTO
     * @param bindingResult        the binding result object
     * @param uriComponentsBuilder the uri components builder
     * @return the response entity
     */
    @PostMapping("/")
    public ResponseEntity<CalendarDto> addCalendar(@Valid @RequestBody CalendarDto calendarDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {

        log.info("Post Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            try {
                Calendar savedCalendar = calendarService.createCalendar(calendarDtoToCalendar.convert(calendarDto));
                CalendarDto resultingCalendarDto = calendarToCalendarDto.convert(savedCalendar);

                // get help from the framework building the path for the newly created resource
                assert resultingCalendarDto != null;
                UriComponents uriComponents = uriComponentsBuilder.path("/api/calendar/" + resultingCalendarDto.getId()).build();

                // set headers with the created path
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(uriComponents.toUri());

                return new ResponseEntity<>(resultingCalendarDto, headers, HttpStatus.CREATED);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Edits a calendar
     *
     * @param calendarDto   the calendar DTO
     * @param id            the calendar id
     * @param bindingResult the binding result
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<CalendarDto> editCalendar(@Valid @RequestBody CalendarDto calendarDto, BindingResult bindingResult, @PathVariable("id") Integer id) {

        log.info("Put Method called");

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else if (calendarService.getCalendar(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {

            try {
                calendarDto.setId(id);

                Calendar savedCalendar = calendarService.updateCalendar(calendarDtoToCalendar.convert(calendarDto));
                CalendarDto resultingCalendarDto = calendarToCalendarDto.convert(savedCalendar);

                return new ResponseEntity<>(resultingCalendarDto, HttpStatus.OK);

            } catch (Exception e) {
                log.error(e.getMessage());
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * Deletes a calendar
     *
     * @param id the calendar id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCalendar(@PathVariable("id") Integer id) {

        log.info("Delete Method called");

        if (calendarService.getCalendar(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            calendarService.deleteCalendar(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

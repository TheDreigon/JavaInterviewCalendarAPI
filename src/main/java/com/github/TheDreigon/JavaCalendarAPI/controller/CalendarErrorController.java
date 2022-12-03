package com.github.TheDreigon.JavaCalendarAPI.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling error paths
 */
@RestController
public class CalendarErrorController implements ErrorController {
    private static final String PATH = "/error";

    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public String error() {
        return "No Content: please try another path";
    }
}

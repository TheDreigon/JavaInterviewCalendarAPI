package com.github.TheDreigon.JavaCalendarAPI.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for retrieving the API description
 */
@RestController
@RequestMapping({"/api", ""})
public class RestIndexController {

    /**
     * Retrieves the API name and version
     *
     * @return the response
     */
    @GetMapping(path = {"/", ""})
    @ResponseBody
    protected ApiVersion showVersion() {

        ApiVersion version = new ApiVersion();
        version.setName("JavaCalendarAPI");
        version.setVersion("v1.0");

        return version;
    }

    @Getter
    @Setter
    private static class ApiVersion {

        private String name;
        private String version;
    }
}

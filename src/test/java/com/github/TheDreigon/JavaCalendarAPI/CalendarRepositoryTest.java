package com.github.TheDreigon.JavaCalendarAPI;

import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CalendarRepositoryTest {

    @Autowired
    private CalendarRepository repository;


}
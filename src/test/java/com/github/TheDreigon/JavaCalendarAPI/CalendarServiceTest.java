package com.github.TheDreigon.JavaCalendarAPI;

import com.github.TheDreigon.JavaCalendarAPI.persistence.model.Calendar;
import com.github.TheDreigon.JavaCalendarAPI.persistence.repository.CalendarRepository;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarService;
import com.github.TheDreigon.JavaCalendarAPI.service.CalendarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CalendarServiceTest {

    @InjectMocks
    private CalendarService calendarService = new CalendarServiceImpl();

    @Mock
    private CalendarRepository calendarRepository;

    @Test
    public void getCalendarListShouldReturnCalendarList() {

        Calendar calendar = new Calendar();
        calendar.setName("name");
        calendar.setDescription("description");

        List<Calendar> calendarList = new ArrayList<>();
        calendarList.add(calendar);

        when(calendarRepository.findAll()).thenReturn(calendarList);

        List<Calendar> calendarListToAssert = calendarService.getCalendarList();

        Assertions.assertEquals(calendar.getId(), calendarListToAssert.get(0).getId());
        Assertions.assertEquals(calendar.getName(), calendarListToAssert.get(0).getName());
        Assertions.assertEquals(calendar.getDescription(), calendarListToAssert.get(0).getDescription());
    }

    @Test
    public void getCalendarShouldReturnCalendar() {

        Calendar calendar = new Calendar();
        calendar.setId(4);
        calendar.setName("name");
        calendar.setDescription("description");

        when(calendarRepository.findById(any(Integer.class))).thenReturn(Optional.of(calendar));

        Calendar calendarToAssert = calendarService.getCalendar(calendar.getId());

        Assertions.assertEquals(calendar.getId(), calendarToAssert.getId());
        Assertions.assertEquals(calendar.getName(), calendarToAssert.getName());
        Assertions.assertEquals(calendar.getDescription(), calendarToAssert.getDescription());
    }

    @Test
    public void createCalendarShouldReturnCreateAndCalendar() {

        Calendar calendar = new Calendar();
        calendar.setName("name");
        calendar.setDescription("description");

        when(calendarRepository.save(any(Calendar.class))).thenReturn(calendar);

        Calendar calendarToAssert = calendarService.createCalendar(calendar);

        Assertions.assertEquals(calendar.getName(), calendarToAssert.getName());
        Assertions.assertEquals(calendar.getDescription(), calendarToAssert.getDescription());
    }

    @Test
    public void updateCalendarShouldUpdateAndReturnCalendar() {

        Calendar calendar = new Calendar();
        calendar.setId(4);
        calendar.setName("name");
        calendar.setDescription("description");

        when(calendarRepository.findById(any(Integer.class))).thenReturn(Optional.of(calendar));
        when(calendarRepository.save(any(Calendar.class))).thenReturn(calendar);

        Calendar calendarToAssert = calendarService.updateCalendar(calendar);

        Assertions.assertEquals(calendar.getId(), calendarToAssert.getId());
        Assertions.assertEquals(calendar.getName(), calendarToAssert.getName());
        Assertions.assertEquals(calendar.getDescription(), calendarToAssert.getDescription());
    }

    @Test
    public void deleteCalendarShouldDeleteCalendar() {

        Calendar calendar = new Calendar();
        calendar.setId(4);
        calendar.setName("name");
        calendar.setDescription("description");

        calendarService.deleteCalendar(calendar.getId());

        verify(calendarRepository, times(1)).deleteById(calendar.getId());
    }
}

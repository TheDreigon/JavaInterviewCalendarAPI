package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

/**
 * The interviewer availability slot model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "INTERVIEWER_AVAILABILITY_SLOT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class InterviewerAvailabilitySlot extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "interviewer", nullable = false)
    private Interviewer interviewer;

    @Column(name = "startDay", nullable = false)
    private Date startDay;

    @Column(name = "endDay", nullable = false)
    private Date endDay;

    @Column(name = "startHour", nullable = false)
    private String startHour;

    @Column(name = "endHour", nullable = false)
    private String endHour;

    @Column(name = "dayOfWeek", nullable = false)
    private DayOfWeek dayOfWeek;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InterviewerAvailabilitySlot that = (InterviewerAvailabilitySlot) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * The interviewer availability schedule model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "INTERVIEWER_AVAILABILITY_SCHEDULE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class InterviewerAvailabilitySchedule extends AbstractModel {

    @ManyToOne
    private Interviewer interviewer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InterviewerAvailabilitySchedule that = (InterviewerAvailabilitySchedule) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

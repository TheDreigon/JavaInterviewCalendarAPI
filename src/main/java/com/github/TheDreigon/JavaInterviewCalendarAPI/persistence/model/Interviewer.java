package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The interviewer model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "INTERVIEWER")
public class Interviewer extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(
            // propagate changes on Interviewer entity to InterviewerAvailabilitySchedule entities
            cascade = {CascadeType.ALL},

            // make sure to remove InterviewerAvailabilitySchedules if unlinked from Interviewer
            orphanRemoval = true,

            // use Interviewer foreign key on InterviewerAvailabilitySchedule table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "interviewer",

            // fetch InterviewerAvailabilitySchedules from database together with Interviewer
            fetch = FetchType.EAGER
    )
    private List<InterviewerAvailabilitySchedule> interviewerAvailabilityScheduleList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Interviewer that = (Interviewer) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

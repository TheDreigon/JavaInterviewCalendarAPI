package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel.AbstractWorker;
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
public class Interviewer extends AbstractWorker {

    @OneToMany(
            // propagate changes on Interviewer entity to InterviewerAvailability entities
            cascade = {CascadeType.ALL},

            // make sure to remove Availabilities if unlinked from Interviewer
            orphanRemoval = true,

            // use Interviewer foreign key on InterviewerAvailability table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "interviewer",

            // fetch Availabilities from database together with Interviewer
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @ToString.Exclude
    private List<InterviewerAvailability> interviewerAvailabilityList = new ArrayList<>();

    public void addInterviewerAvailabilitySlot(InterviewerAvailability interviewerAvailability) {
        interviewerAvailabilityList.add(interviewerAvailability);
        interviewerAvailability.setInterviewer(this);
    }

    public void removeInterviewerAvailabilitySlot(InterviewerAvailability interviewerAvailability) {
        interviewerAvailabilityList.remove(interviewerAvailability);
        interviewerAvailability.setInterviewer(null);
    }

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

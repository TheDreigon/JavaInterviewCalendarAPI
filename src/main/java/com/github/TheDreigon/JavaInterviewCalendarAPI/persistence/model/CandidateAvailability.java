package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel.AbstractAvailability;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * The candidate availability model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CANDIDATE_AVAILABILITY")
public class CandidateAvailability extends AbstractAvailability {

    @ManyToOne
    @JsonBackReference
    //@JoinColumn(name = "candidate", nullable = false)
    @JoinColumn(name = "candidate")
    private Candidate candidate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CandidateAvailability that = (CandidateAvailability) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

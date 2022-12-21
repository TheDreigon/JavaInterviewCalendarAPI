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
 * The candidate model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CANDIDATE")
public class Candidate extends AbstractWorker {

    @OneToMany(
            // propagate changes on Candidate entity to CandidateAvailability entities
            cascade = {CascadeType.ALL},

            // make sure to remove CandidateAvailabilities if unlinked from Candidate
            orphanRemoval = true,

            // use Candidate foreign key on CandidateAvailability table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "candidate",

            // fetch CandidateAvailability from database together with Candidate
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<CandidateAvailability> candidateAvailabilityList = new ArrayList<>();

    public void addCandidateAvailability(CandidateAvailability candidateAvailability) {
        candidateAvailabilityList.add(candidateAvailability);
        candidateAvailability.setCandidate(this);
    }

    public void removeCandidateAvailability(CandidateAvailability candidateAvailability) {
        candidateAvailabilityList.remove(candidateAvailability);
        candidateAvailability.setCandidate(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Candidate candidate = (Candidate) o;
        return getId() != null && Objects.equals(getId(), candidate.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

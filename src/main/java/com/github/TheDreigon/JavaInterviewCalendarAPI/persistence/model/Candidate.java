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
 * The candidate model entity
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CANDIDATE")
public class Candidate extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(
            // propagate changes on Candidate entity to CandidateAvailabilitySlot entities
            cascade = {CascadeType.ALL},

            // make sure to remove CandidateAvailabilitySlots if unlinked from Candidate
            orphanRemoval = true,

            // use Candidate foreign key on CandidateAvailabilitySlot table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "candidate",

            // fetch CandidateAvailabilitySlots from database together with Candidate
            fetch = FetchType.EAGER
    )
    private List<CandidateAvailabilitySlot> candidateAvailabilitySlotList = new ArrayList<>();

    public void addCandidateAvailabilitySlot(CandidateAvailabilitySlot candidateAvailabilitySlot) {
        candidateAvailabilitySlotList.add(candidateAvailabilitySlot);
        candidateAvailabilitySlot.setCandidate(this);
    }

    public void removeCandidateAvailabilitySlot(CandidateAvailabilitySlot candidateAvailabilitySlot) {
        candidateAvailabilitySlotList.remove(candidateAvailabilitySlot);
        candidateAvailabilitySlot.setCandidate(null);
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

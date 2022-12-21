package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A generic availability model entity to be used as a base for concrete types of availability models
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class AbstractAvailability extends AbstractModel {

    @Column(name = "dayDate", nullable = false)
    private LocalDate dayDate;

    @Column(name = "availableHour", nullable = false)
    private String availableHour;

    @Column(name = "dayOfWeek", nullable = false)
    private String dayOfWeek;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbstractAvailability that = (AbstractAvailability) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

package com.github.TheDreigon.JavaInterviewCalendarAPI.persistence.model.abstractModel;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.Objects;

/**
 * A generic availability model entity to be used as a base for concrete types of availability models
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbstractAvailability extends AbstractModel {

    @Column(name = "dayDate", nullable = false)
    private Date dayDate;

    @Column(name = "hour", nullable = false)
    private String hour;

    @Column(name = "dayOfWeek", nullable = false)
    private DayOfWeek dayOfWeek;

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

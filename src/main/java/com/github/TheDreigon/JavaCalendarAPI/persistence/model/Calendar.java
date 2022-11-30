package com.github.TheDreigon.JavaCalendarAPI.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The calendar model entity
 */
@Getter
@Setter
@Entity
@Table(name = "calendar")
public class Calendar extends AbstractModel {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public String toString() {
        return "Calendar{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

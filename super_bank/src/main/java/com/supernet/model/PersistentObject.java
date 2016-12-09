package com.supernet.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for Model
 */
@MappedSuperclass
public abstract class PersistentObject {
    @Id
    @GeneratedValue()
    @Column(nullable = false, updatable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }

        if (Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        PersistentObject that = (PersistentObject) o;
        return new EqualsBuilder()
                .append(getId(), that.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }
}

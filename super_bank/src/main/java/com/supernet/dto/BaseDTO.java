package com.supernet.dto;


import com.supernet.exceptions.GetInstanceException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Base class for DTO
 *
 * @param <T> class
 */
public abstract class BaseDTO<T> {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    protected abstract Class<T> getEntityClass();

    /**
     * Get instance
     *
     * @return instance of T
     */
    public T instantiateEntity() {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new GetInstanceException("An error occurred during entity instantiation", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BaseDTO<?> baseDTO = (BaseDTO<?>) o;

        return new EqualsBuilder()
                .append(id, baseDTO.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}

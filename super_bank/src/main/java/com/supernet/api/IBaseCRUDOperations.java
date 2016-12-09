package com.supernet.api;

import com.supernet.exceptions.ESEntityNotFoundException;
import com.supernet.filters.SearchFilter;
import com.supernet.model.PersistentObject;
import com.supernet.services.BaseCRUDService;

import java.util.Optional;

/**
 * Interface for CRUD operations
 *
 * @param <E> entity
 * @param <F> filter
 */
@FunctionalInterface
public interface IBaseCRUDOperations<E extends PersistentObject, F extends SearchFilter> {

    /**
     * Get service for entity
     *
     * @return BaseCRUDService<E,F>, service for entity
     */
    BaseCRUDService<E, F> getService();

    /**
     * Find one entity
     *
     * @param id entity id
     * @return Entity
     */
    default E findOne(Long id) {
        return Optional.ofNullable(getService().findOne(id)).orElseThrow(ESEntityNotFoundException::new);
    }
}

package com.supernet.services;

import com.supernet.filters.SearchFilter;
import com.supernet.model.PersistentObject;
import com.supernet.reposotories.BaseRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Base class for service
 *
 * @param <E> entity
 * @param <F> search filter
 */
public interface BaseCRUDService<E extends PersistentObject, F extends SearchFilter> {

    /**
     * Get JpaRepository<E,Long>
     *
     * @return JpaRepository<E,Long>
     */
    JpaRepository<E, Long> getRepository();

    /**
     * Get BaseRepository<E,F>
     *
     * @return BaseRepository<E,F>
     */
    BaseRepositoryCustom<E, F> getRepositoryCustom();

    /**
     * Get info about one entity
     *
     * @param id entity id
     * @return entity, info about entity
     */
    default E findOne(Long id) {
        return getRepository().findOne(id);
    }

    /**
     * Save entity
     *
     * @param entity entity
     * @return entity
     */
    default E save(E entity) {
        return getRepository().save(entity);
    }

    /**
     * Save Collection with entities
     *
     * @param entities collection with entities
     * @return Collection<E>
     */
    default Collection<E> save(Collection<E> entities) {
        return getRepository().save(entities);
    }

    /**
     * Delete entity
     *
     * @param id entity id
     */
    default void delete(Long id) {
        getRepository().delete(id);
    }

    /**
     * Delete entities
     *
     * @param entities Set<E> entities
     */
    default void delete(Set<E> entities) {
        getRepository().delete(entities);
    }

    /**
     * Find all entities use search filter
     *
     * @param filter   filter
     * @param pageable pageable params
     * @return Page<E>, page with right entities
     */
    default Page<E> findAll(F filter, Pageable pageable) {
        return getRepositoryCustom().findAll(filter, pageable);
    }

    /**
     * Find all entities use search filter
     *
     * @param filter filter
     * @return List<E>, list with right entities
     */
    default List<E> findAll(F filter) {
        return getRepositoryCustom().findAll(filter);
    }
}

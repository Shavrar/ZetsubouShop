package com.supernet.reposotories;

import com.querydsl.core.BooleanBuilder;
import com.supernet.filters.SearchFilter;
import com.supernet.model.PersistentObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Base class for impl repositories
 *
 * @param <E> entity
 * @param <F> filter
 */
public abstract class BaseRepositoryImpl<E extends PersistentObject, F extends SearchFilter>
        extends QueryDslRepositorySupport
        implements BaseRepositoryCustom<E, F> {

    @PersistenceContext
    private EntityManager entityManager;

    private QueryDslJpaRepository<E, Long> jpaRepository;
    private Class<E> domainClass;

    /**
     * Create repository impl
     *
     * @param tClass entity
     */
    public BaseRepositoryImpl(Class<E> tClass) {
        super(tClass);
        domainClass = tClass;
    }

    /**
     * Init repository
     */
    @PostConstruct
    public void
    init() {
        JpaEntityInformation<E, Long> productEntityInfo = new JpaMetamodelEntityInformation<>(domainClass(), entityManager.getMetamodel());
        jpaRepository = new QueryDslJpaRepository<>(productEntityInfo, entityManager);
    }

    private Class<E>
    domainClass() {
        return domainClass;
    }

    protected abstract BooleanBuilder
    getQueryPredicate(F searchFilter);

    @Override
    public List<E>
    findAll(F searchFilter) {
        BooleanBuilder predicate = getQueryPredicate(searchFilter);
        return jpaRepository.findAll(predicate);
    }

    @Override
    public long
    count(F searchFilter) {
        BooleanBuilder predicate = getQueryPredicate(searchFilter);
        return jpaRepository.count(predicate);
    }

    @Override
    public Page<E>
    findAll(F searchFilter, Pageable pageable) {
        BooleanBuilder predicate = getQueryPredicate(searchFilter);
        return jpaRepository.findAll(predicate, pageable);
    }

    @Override
    public E
    findOne(F filter) {
        BooleanBuilder predicate = getQueryPredicate(filter);
        return jpaRepository.findOne(predicate);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

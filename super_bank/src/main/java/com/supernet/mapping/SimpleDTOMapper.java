package com.supernet.mapping;

import com.supernet.dto.BaseDTO;
import com.supernet.model.PersistentObject;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

/**
 * Simple dto mapper for entities
 *
 * @param <E> entity
 * @param <D> dto
 */
@Component
public abstract class SimpleDTOMapper<E extends PersistentObject, D extends BaseDTO<E>>
        extends BaseMapper {

    @Override
    protected PropertyMap<E, D> getEntityToDTOMapping() {

        return new PropertyMap<E, D>() {
            @Override
            protected void configure() {
                //comment for sonar
            }
        };
    }

    @Override
    protected PropertyMap<D, E> getDTOToEntityMapping() {

        return new PropertyMap<D, E>() {
            @Override
            protected void configure() {
                //comment for sonar
            }
        };
    }
}

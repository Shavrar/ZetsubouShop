package com.supernet.api;

import com.supernet.dto.BaseDTO;
import com.supernet.dto.ExceptionInfoDTO;
import com.supernet.model.PersistentObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Base class for Controllers
 */
public abstract class BaseController {

    @Autowired
    private ModelMapper modelMapper;

    protected <E extends PersistentObject, D extends BaseDTO> D convertToDto(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    protected <E extends PersistentObject, D extends BaseDTO> List<D> convertToDto(List<E> entities, Class<D> dtoClass) {
        return entities.stream().map(e -> modelMapper.map(e, dtoClass)).collect(toList());
    }

    protected <E extends PersistentObject, D extends BaseDTO> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    protected ResponseEntity<ExceptionInfoDTO> getExceptionInfo(String message, List<Object> params, HttpStatus status) {
        ExceptionInfoDTO exception = new ExceptionInfoDTO();
        exception.setMessage(message);
        exception.setParams(params);
        return new ResponseEntity<>(exception, status);
    }
}

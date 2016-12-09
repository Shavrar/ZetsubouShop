package com.supernet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.PersistenceException;

/**
 * Entity not found exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity.not.found")
public class ESEntityNotFoundException
        extends PersistenceException {
}
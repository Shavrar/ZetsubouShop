package com.supernet.api;

import com.supernet.dto.ExceptionInfoDTO;
import com.supernet.exceptions.CustomException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * Algorithm what to do if exception
     *
     * @param e exception
     * @return Response<Entity>, info about exception for client
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ExceptionInfoDTO> defaultErrorHandler(CustomException e) {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
        if (AnnotationUtils.findAnnotation
                (e.getClass(), ResponseStatus.class) != null)
            throw e;

        // Otherwise setup and send the user to a default answer.
        ExceptionInfoDTO exception = new ExceptionInfoDTO();
        exception.setMessage(e.getMessage());
        return new ResponseEntity<>(exception, e.getStatus());
    }
}

package com.supernet.dto;

import java.util.List;

/**
 * Info about exception for client
 */
public class ExceptionInfoDTO {

    private String message;

    private List<Object> params;

    public ExceptionInfoDTO() {
        //default constructor
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}

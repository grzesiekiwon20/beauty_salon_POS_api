package com.beautysalon.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;


import java.util.Map;
import java.util.Set;


@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;

    public ExceptionResponse(Integer businessErrorCode, String businessErrorDescription, String error, Set<String> validationErrors, Map<String, String> errors) {
        this.businessErrorCode = businessErrorCode;
        this.businessErrorDescription = businessErrorDescription;
        this.error = error;
        this.validationErrors = validationErrors;
        this.errors = errors;
    }

    public ExceptionResponse() {
    }

    public void setBusinessErrorCode(Integer businessErrorCode) {
        this.businessErrorCode = businessErrorCode;
    }

    public void setBusinessErrorDescription(String businessErrorDescription) {
        this.businessErrorDescription = businessErrorDescription;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setValidationErrors(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
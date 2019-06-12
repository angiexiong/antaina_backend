package com.antaina.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class ParameterCheckException extends BindException {

    public ParameterCheckException(BindingResult bindingResult) {
        super(bindingResult);
    }

}

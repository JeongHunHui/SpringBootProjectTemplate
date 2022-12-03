package com.springboot.template.global.error.exception;

import com.springboot.template.global.error.ErrorCode;

public class ExampleException extends BusinessException {
    public ExampleException() {
        super(ErrorCode.EXAMPLE_USER_ERROR);
    }
}

package com.pedroluizforlan.service.exception;

public class NotFoundException extends BusinessException {
    private static final Long serialVersionUID = 1L;

    public NotFoundException() {
        super("Resource not found");
    }
}

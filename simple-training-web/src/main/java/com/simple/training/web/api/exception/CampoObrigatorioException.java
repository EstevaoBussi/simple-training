package com.simple.training.web.api.exception;

public class CampoObrigatorioException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public CampoObrigatorioException(String campos) {
        super(campos);
    }

}

package com.pitang.mobile.infraestrutura.exception;

public class NegocioException extends RuntimeException {

    public NegocioException(Throwable cause) {
        super(cause);
    }

    public NegocioException(final String mensagem, final Throwable causa) {
        super(mensagem, causa);
    }

    public NegocioException(final String mensagem) {
        super(mensagem);
    }
}
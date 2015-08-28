package com.pitang.mobile.infraestrutura.exception;

public class InfraException extends RuntimeException{

    /**
     * Constrói uma nova InfraException.
     *
     * @param cause - exceção que provocou esta exceção(causa)
     */
    public InfraException(Throwable cause) {
        super(cause);
    }

    /**
     * Constrói uma nova InfraException.
     *
     * @param mensagem mensagem de erro.
     * @param cause - exceção que provocou esta exceção (causa)
     */
    public InfraException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

    /**
     * Constrói uma nova InfraException com a mensagem de erro.
     *
     * @param mensagem mensagem de erro.
     *
     */
    public InfraException(String mensagem) {
        super(mensagem);
    }
}

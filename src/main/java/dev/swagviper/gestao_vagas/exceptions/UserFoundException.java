package dev.swagviper.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException {

    public UserFoundException() {
        super("Este usuário já existe.");
    }

}

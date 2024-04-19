package com.algaworks.comercialapi.exception;

public class ForbiddenRequestException extends RuntimeException{

    public ForbiddenRequestException(String message){
        super(message);
    }
}

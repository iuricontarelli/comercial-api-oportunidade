package com.algaworks.comercialapi.exception;

public class UnauthorizedRequestException extends RuntimeException{

    public UnauthorizedRequestException(String message){
        super(message);
    }
}

package com.algaworks.comercialapi.exception.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.algaworks.comercialapi.exception.BadRequestException;
import com.algaworks.comercialapi.exception.ForbiddenRequestException;
import com.algaworks.comercialapi.exception.InternalServerException;
import com.algaworks.comercialapi.exception.NotFoundException;
import com.algaworks.comercialapi.exception.UnauthorizedRequestException;
import com.algaworks.comercialapi.exception.model.StandardError;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<StandardError> internalServer(InternalServerException e, HttpServletRequest request){

        String message = e.getMessage();
        String error = "Internal Server Error";
        
        if(message == null) message = e.toString();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequest(BadRequestException e, HttpServletRequest request){

        String message = e.getMessage();
        String error = "Bad Request Error";
        
        if(message == null) message = e.toString();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    
    @ExceptionHandler(ForbiddenRequestException.class)
    public ResponseEntity<StandardError> badRequest(ForbiddenRequestException e, HttpServletRequest request){

        String message = e.getMessage();
        String error = "Forbidden";
        
        if(message == null) message = e.toString();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UnauthorizedRequestException.class)
    public ResponseEntity<StandardError> badRequest(UnauthorizedRequestException e, HttpServletRequest request){

        String message = e.getMessage();
        String error = "Unauthorized";
        
        if(message == null) message = e.toString();

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){

        String message = e.getMessage();
        String error = "Not Found Error";
        
        if(message == null) message = e.toString();

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<StandardError> defaultNotFoundException(HttpServletRequest request) {
        String message = "Resource not found";
        String error = "Not Found";

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(error, status.value(), message, new Date());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
package com.productos.ProductosCrud.controller;

import com.productos.ProductosCrud.model.GenericResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<GenericResponse> handleProductNotFoundException(ChangeSetPersister.NotFoundException ex, WebRequest request) {
        GenericResponse errorDetails = new GenericResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                "El producto no fue encontrado.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<GenericResponse> handleInvalidProductException(BadRequestException ex, WebRequest request) {
        GenericResponse errorDetails = new GenericResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                "El producto es invalido.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericResponse> handleGlobalException(Exception ex, WebRequest request) {
        GenericResponse errorDetails = new GenericResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Hubo un error en el servidor.",
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

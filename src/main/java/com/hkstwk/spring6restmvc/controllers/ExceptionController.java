package com.hkstwk.spring6restmvc.controllers;

import com.hkstwk.spring6restmvc.exceptions.NotFoundException;
import com.hkstwk.spring6restmvc.model.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Beer> handleNotFoundException(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

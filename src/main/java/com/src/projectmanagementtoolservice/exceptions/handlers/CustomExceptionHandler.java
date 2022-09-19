package com.src.projectmanagementtoolservice.exceptions.handlers;

import com.src.projectmanagementtoolservice.exceptions.IdDuplicateException;
import com.src.projectmanagementtoolservice.exceptions.ProjectNotFoundException;
import com.src.projectmanagementtoolservice.exceptions.responses.ProjectIdExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IdDuplicateException.class)
    public ResponseEntity<ProjectIdExceptionResponse> handleProjectIdDuplicateException(IdDuplicateException idDuplicateException) {
        ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(idDuplicateException.getMessage());
        return new ResponseEntity<>(projectIdExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ProjectNotFoundException.class)
    public ResponseEntity<ProjectIdExceptionResponse> handleProjectNotFoundException(ProjectNotFoundException projectNotFoundException) {
        ProjectIdExceptionResponse projectIdExceptionResponse = new ProjectIdExceptionResponse(projectNotFoundException.getMessage());
        return new ResponseEntity<>(projectIdExceptionResponse, HttpStatus.NOT_FOUND);
    }
}

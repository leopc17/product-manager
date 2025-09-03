package com.br.productmanager.infraestructure.exception.handler;

import com.br.productmanager.infraestructure.exception.ProductAlreadyExistsException;
import com.br.productmanager.infraestructure.exception.ProductNotFoundException;
import com.br.productmanager.infraestructure.exception.apierror.ApiError;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        ApiError apiErrorResponse = new ApiError(INTERNAL_SERVER_ERROR, ex);
        apiErrorResponse.setDebugMessage(ex.getLocalizedMessage());
        return buildResponseEntity(apiErrorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiErrorResponse = new ApiError(BAD_REQUEST, "Malformed JSON request", ex);

        return buildResponseEntity(apiErrorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiError apiErrorResponse = new ApiError(BAD_REQUEST, "Validation error");
        apiErrorResponse.setDebugMessage(ex.getLocalizedMessage());
        apiErrorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
        apiErrorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        ApiError apiErrorResponse = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", ex);

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        ApiError apiErrorResponse = new ApiError(NOT_FOUND, ex.getLocalizedMessage());

        return buildResponseEntity(apiErrorResponse);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(ProductAlreadyExistsException ex) {
        ApiError apiErrorResponse = new ApiError(CONFLICT, ex.getLocalizedMessage());

        return buildResponseEntity(apiErrorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

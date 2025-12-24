package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomExceptionHandler {

    // Handles JWT Token specific errors (Page 17)
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse> handleJwtException(JwtException ex) {
        ApiResponse response = new ApiResponse(false, "Invalid or expired token", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handles Spring Security Authentication failures
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthException(AuthenticationException ex) {
        ApiResponse response = new ApiResponse(false, "Authentication failed", null);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Handles Role-based access denials
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleAccessDenied(AccessDeniedException ex) {
        ApiResponse response = new ApiResponse(false, "You do not have permission to access this resource", null);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    // Fallback for any other custom runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        ApiResponse response = new ApiResponse(false, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
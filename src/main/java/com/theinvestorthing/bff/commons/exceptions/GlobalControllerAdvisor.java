package com.theinvestorthing.bff.commons.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.theinvestorthing.bff.commons.response.ErrorResponse;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvisor.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundExceptionHandler(NotFoundException exception, HttpServletRequest req){
        String traceId = UUID.randomUUID().toString();
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        logger.error("404 - {}, traceId: {}", exception.getMessage(), traceId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                traceId,
                req.getRequestURI(),
                errors
        ));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestExceptionHandler(BadRequestException exception, HttpServletRequest req){
        String traceId = req.getHeader("x-trace-id");
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", exception.getMessage());
        logger.error("400 - {}, traceId: {}", exception.getMessage(), traceId);
        return ResponseEntity.badRequest().body(new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                traceId,
                req.getRequestURI(),
                errors
        ));
    }
}

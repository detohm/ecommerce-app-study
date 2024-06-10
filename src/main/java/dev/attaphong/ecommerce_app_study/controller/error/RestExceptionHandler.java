package dev.attaphong.ecommerce_app_study.controller.error;

import dev.attaphong.ecommerce_app_study.service.HeaderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<ApiError> handleEntityNotFound(Exception e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HeaderService.getInstance().getGlobalHeaders(), apiError.getHttpStatus());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ApiError> defaultHandler(Exception e){
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HeaderService.getInstance().getGlobalHeaders(), apiError.getHttpStatus());
    }
}

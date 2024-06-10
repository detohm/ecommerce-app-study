package dev.attaphong.ecommerce_app_study.controller.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
public class ApiError {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime timestamp;
    private String message;

    ApiError(){
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
    }

    ApiError(HttpStatus httpStatus){
        this();
        this.httpStatus = httpStatus;

    }

    public ApiError(HttpStatus httpStatus, String message){
        this();
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

package dev.attaphong.ecommerce_app_study.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ResponseDTO {
    private String errorCode;
    private String errorMessage;
}

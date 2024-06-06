package dev.attaphong.ecommerce_app_study.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private String productID;
    private Integer quantity;
}

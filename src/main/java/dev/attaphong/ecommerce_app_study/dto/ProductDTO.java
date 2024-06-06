package dev.attaphong.ecommerce_app_study.dto;

import dev.attaphong.ecommerce_app_study.model.Inventory;
import lombok.Data;

@Data
public class ProductDTO {
    private String id;

    private String title;
    private String desc;
    private String price;
    private Integer availableAmount;
}

package dev.attaphong.ecommerce_app_study.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    private int quantity;
}

package dev.attaphong.ecommerce_app_study.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data @EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Column(name="AVAILABLE_AMOUNT")
    private int availableAmount;
}

package dev.attaphong.ecommerce_app_study.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data @EqualsAndHashCode(callSuper = false)
@Table(name="PRODUCT")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_ID")
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="DESC")
    private String desc;

    @Column(name="PRICE")
    private BigDecimal price;

    @Column(name="IS_DELETED")
    private boolean isDeleted;

    private Inventory inventory;
}

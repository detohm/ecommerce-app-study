package dev.attaphong.ecommerce_app_study.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data @EqualsAndHashCode(callSuper = false)
@Table(name="CART")
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CART_ID")
    private long id;

    @ElementCollection
    @CollectionTable(name="CART_ITEMS", joinColumns = {@JoinColumn(name="CART_ID")})
    @AttributeOverrides({
            @AttributeOverride(name="quantity", column = @Column(name="QUANTITY"))
    })
    private List<OrderItem> items;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;
}

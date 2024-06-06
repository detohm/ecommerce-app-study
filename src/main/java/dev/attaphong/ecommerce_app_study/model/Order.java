package dev.attaphong.ecommerce_app_study.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data @EqualsAndHashCode(callSuper = false)
@Table(name="ORDER")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ORDER_ID")
    private long id;

    @OneToOne
    @JoinColumn(name="PAYMENT_ID")
    private Payment payment;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @ElementCollection
    @CollectionTable(name="ORDER_ITEMS", joinColumns = {@JoinColumn(name="ORDER_ID")})
    @AttributeOverrides({
            @AttributeOverride(name="quantity", column = @Column(name="QUANTITY"))
    })
    List<OrderItem> items;
}

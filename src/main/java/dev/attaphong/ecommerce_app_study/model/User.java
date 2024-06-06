package dev.attaphong.ecommerce_app_study.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data @EqualsAndHashCode(callSuper = false)
@Table(name="USER")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private long id;

    @Column(unique=true)
    private String username;

    @Column(name="HASHED_PASSWORD")
    private String hashedPassword;

    @Column(name="SALT")
    private String salt;

    @Column(name="FIRSTNAME")
    private String firstName;

    @Column(name="LASTNAME")
    private String lastName;
}

package dev.attaphong.ecommerce_app_study.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {
    @CreationTimestamp
    @Column(name="CREATE_TIME")
    private Date createTime;

    @UpdateTimestamp
    @Column(name="UPDATE_TIME")
    private Date updateTime;
}

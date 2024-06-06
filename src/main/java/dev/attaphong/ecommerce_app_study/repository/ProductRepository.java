package dev.attaphong.ecommerce_app_study.repository;

import dev.attaphong.ecommerce_app_study.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

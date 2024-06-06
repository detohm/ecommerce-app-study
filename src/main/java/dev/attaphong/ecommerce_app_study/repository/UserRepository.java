package dev.attaphong.ecommerce_app_study.repository;

import dev.attaphong.ecommerce_app_study.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

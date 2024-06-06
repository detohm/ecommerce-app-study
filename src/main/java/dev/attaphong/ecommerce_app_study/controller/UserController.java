package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.model.User;
import dev.attaphong.ecommerce_app_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/seed")
    // Temporal method to seed user
    void seed(){
        User user = new User();
        userRepository.save(user);
    }
}

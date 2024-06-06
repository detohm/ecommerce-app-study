package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.dto.UserDTO;
import dev.attaphong.ecommerce_app_study.model.User;
import dev.attaphong.ecommerce_app_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;
    private static HttpHeaders headers;
    SignupController(){
        headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
    }

    @PostMapping
    ResponseEntity<User> signup(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(null, headers, HttpStatus.METHOD_NOT_ALLOWED);
        // return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).headers(headers).build();
    }

    @GetMapping
    ResponseEntity<User> signup(){
        // return new ResponseEntity<>(null, headers, HttpStatus.METHOD_NOT_ALLOWED);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}

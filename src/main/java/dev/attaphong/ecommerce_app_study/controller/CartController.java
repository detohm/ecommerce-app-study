package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.dto.OrderItemDTO;
import dev.attaphong.ecommerce_app_study.dto.ResponseDTO;
import dev.attaphong.ecommerce_app_study.model.Cart;
import dev.attaphong.ecommerce_app_study.model.User;
import dev.attaphong.ecommerce_app_study.service.CartService;
import dev.attaphong.ecommerce_app_study.service.HeaderService;
import dev.attaphong.ecommerce_app_study.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity<Cart> getCart(){
        Cart cart = cartService.getCart(getCurrentUserID());
        return new ResponseEntity<>(cart, HeaderService.getInstance().getGlobalHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add-item")
    ResponseEntity<ResponseDTO> addItem(@RequestBody OrderItemDTO orderItemDTO){
        cartService.addItem(orderItemDTO, getCurrentUserID());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-item/{productID}")
    ResponseEntity<ResponseDTO> removeItem(@PathVariable("productID") Long productID){
        System.out.println(productID);
        cartService.removeItem(productID, getCurrentUserID());
        return ResponseEntity.ok().build();
    }

    private long getCurrentUserID(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            User user = userService.loadUserByUsername(username);
            return user.getId();
        } catch (UsernameNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}

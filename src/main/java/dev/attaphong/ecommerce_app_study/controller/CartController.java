package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.dto.OrderItemDTO;
import dev.attaphong.ecommerce_app_study.dto.ResponseDTO;
import dev.attaphong.ecommerce_app_study.model.Cart;
import dev.attaphong.ecommerce_app_study.service.CartService;
import dev.attaphong.ecommerce_app_study.service.HeaderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    private HttpHeaders globalHeaders;

    // TODO: change to security context
    private final long userID = 1;

    public CartController(){

    }

    @GetMapping
    ResponseEntity<Cart> getCart(){
        Cart cart = cartService.getCart(userID);
        return new ResponseEntity<>(cart, HeaderService.getInstance().getGlobalHeaders(), HttpStatus.OK);
    }

    @PostMapping("/add-item")
    ResponseEntity<ResponseDTO> addItem(@RequestBody OrderItemDTO orderItemDTO){
        cartService.addItem(orderItemDTO, userID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-item/{productID}")
    ResponseEntity<ResponseDTO> removeItem(@PathVariable("productID") Long productID){
        System.out.println(productID);
        cartService.removeItem(productID, userID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-item/{productId}")
    ResponseEntity<ResponseDTO> clearCart(@PathVariable("productId") String productID){
        System.out.println(productID);
        return ResponseEntity.ok().build();
    }
}

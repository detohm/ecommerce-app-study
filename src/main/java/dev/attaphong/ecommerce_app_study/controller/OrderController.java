package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.dto.ResponseDTO;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    public ResponseEntity<ResponseDTO> placeOrder(){
        return ResponseEntity.ok().build();
    }
}

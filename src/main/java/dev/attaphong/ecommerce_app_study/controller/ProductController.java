package dev.attaphong.ecommerce_app_study.controller;

import dev.attaphong.ecommerce_app_study.dto.ProductDTO;
import dev.attaphong.ecommerce_app_study.dto.ResponseDTO;
import dev.attaphong.ecommerce_app_study.model.Inventory;
import dev.attaphong.ecommerce_app_study.model.Product;
import dev.attaphong.ecommerce_app_study.model.Role;
import dev.attaphong.ecommerce_app_study.service.HeaderService;
import dev.attaphong.ecommerce_app_study.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    @RolesAllowed({Role.Fields.OPERATION,Role.Fields.ADMIN})
    ResponseEntity<List<ProductDTO>> getProducts(){
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(convertToDTO(products), HttpStatus.OK);
    }

    @PostMapping("/create")
    @RolesAllowed({Role.Fields.OPERATION,Role.Fields.ADMIN})
    ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
        Product product = convertFromDTO(productDTO);
        productService.create(product);
        return ResponseEntity.ok().headers(HeaderService.getInstance().getGlobalHeaders()).build();
    }

    @PutMapping("/update")
    @RolesAllowed({Role.Fields.OPERATION,Role.Fields.ADMIN})
    ResponseEntity<?> update(@RequestBody ProductDTO productDTO){
        if(productDTO.getId() == null){
            throw new RuntimeException("invalid id");
        }
        Product product = new Product();
        if(productDTO.getId() != null)
            product.setId(Long.parseLong(productDTO.getId()));
        product.setTitle(productDTO.getTitle());
        product.setDesc(productDTO.getDesc());

        productService.update(product);
        return ResponseEntity.ok().headers(HeaderService.getInstance().getGlobalHeaders()).build();
    }

    @DeleteMapping("/delete/{id}")
    @RolesAllowed({Role.Fields.OPERATION,Role.Fields.ADMIN})
    ResponseEntity<ResponseDTO> delete(@PathVariable("id") long productID){
        productService.delete(productID);
        return ResponseEntity.ok().headers(HeaderService.getInstance().getGlobalHeaders()).build();
    }

    static List<ProductDTO> convertToDTO(List<Product> products){
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product : products){
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(product, dto);
            dto.setId(Long.toString(product.getId()));

            productDTOs.add(dto);
        }
        return productDTOs;
    }

    static Product convertFromDTO(ProductDTO dto){
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);

        if(dto.getId() != null)
            product.setId(Long.parseLong(dto.getId()));

        BigDecimal price = NumberUtils.parseNumber(dto.getPrice(), BigDecimal.class);
        product.setPrice(price);
        product.setInventory(new Inventory(dto.getAvailableAmount()));

        return product;
    }
}

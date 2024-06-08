package dev.attaphong.ecommerce_app_study.service;

import dev.attaphong.ecommerce_app_study.model.Product;
import dev.attaphong.ecommerce_app_study.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public void create(Product product) {
        productRepository.save(product);
    }
    public void update(Product product){
        Optional<Product> optProduct = productRepository.findById(product.getId());
        if(optProduct.isEmpty()){
            throw new RuntimeException("invalid id");
        }
        Product savedProduct = optProduct.get();

        // only accept title and desc changes
        savedProduct.setTitle(product.getTitle());
        savedProduct.setDesc(product.getDesc());

        productRepository.save(savedProduct);
    }

    public void delete(long id){
        Optional<Product> result = productRepository.findById(id);
        if(result.isEmpty()) {
            throw new RuntimeException("invalid id");
        }
        Product savedProduct = result.get();
        savedProduct.setDeleted(true);
        productRepository.save(savedProduct);
    }
}

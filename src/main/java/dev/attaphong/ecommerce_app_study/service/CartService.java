package dev.attaphong.ecommerce_app_study.service;

import dev.attaphong.ecommerce_app_study.dto.OrderItemDTO;
import dev.attaphong.ecommerce_app_study.model.Cart;
import dev.attaphong.ecommerce_app_study.model.OrderItem;
import dev.attaphong.ecommerce_app_study.model.Product;
import dev.attaphong.ecommerce_app_study.model.User;
import dev.attaphong.ecommerce_app_study.repository.CartRepository;
import dev.attaphong.ecommerce_app_study.repository.ProductRepository;
import dev.attaphong.ecommerce_app_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCart(long userID){
        return cartRepository.findByUserId(userID);
    }

    public void addItem(OrderItemDTO orderItemDTO, long userID){
        Cart cart = cartRepository.findByUserId(userID);
        Optional<User> optUser = userRepository.findById(userID);
        if(optUser.isEmpty()){
            throw new RuntimeException("invalid user id");
        }
        long productID = Long.parseLong(orderItemDTO.getProductID());
        Optional<Product> product = productRepository.findById(productID);
        if(product.isEmpty()){
            throw new RuntimeException("invalid product");
        }
        if(cart == null){
            cart = new Cart();
            cart.setUser(optUser.get());
            cart.setItems(new ArrayList<>());
        }
        if(cart.getItems().size() >= 3){
            throw new RuntimeException("max cart size");
        }


        cart.getItems().add(new OrderItem(product.get(),orderItemDTO.getQuantity()));
        cartRepository.save(cart);
    }

    public void removeItem(long productID, long userID){

    }
}

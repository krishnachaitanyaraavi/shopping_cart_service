package com.krishna.shoppingcart.service;

import com.krishna.shoppingcart.entity.CartItem;
import com.krishna.shoppingcart.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {


    private CartItemRepository cartItemRepository;


    public CartService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }


    public CartItem addItemToCart(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> viewCartItems() {
        return cartItemRepository.findAll();
    }

    public void deleteItemFromCart(Long id) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            cartItemRepository.deleteById(id);
        } else {
            throw new RuntimeException("CartItem with id " + id + " not found");
        }
    }

    public CartItem updateItemQuantity(Long id, int quantity) {
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(id);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(quantity);
            return cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("CartItem with id " + id + " not found");
        }
    }
}

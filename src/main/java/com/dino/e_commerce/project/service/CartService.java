package com.dino.e_commerce.project.service;

import com.dino.e_commerce.project.model.CartItem;
import com.dino.e_commerce.project.model.ProductData;
import com.dino.e_commerce.project.model.Users;
import com.dino.e_commerce.project.repository.CartRepo;
import com.dino.e_commerce.project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;


    public List<CartItem> getCartItemsByUsername(String username) {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        List<CartItem> cartItems = cartRepo.findByUser(user);


        cartItems.forEach(cartItem -> cartItem.setUser(null));

        return cartItems;
    }


    public CartItem addToCart(String username, ProductData productData) {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        CartItem existingCartItem = cartRepo.findByUserAndProductId(user, productData.getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + 1);
            return cartRepo.save(existingCartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(productData);
            newCartItem.setQuantity(1);
            return cartRepo.save(newCartItem);
        }
    }


    public CartItem updateCartItemQuantity(int cartItemId, int quantity) {
        CartItem existingCartItem = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Cart item not found with ID: " + cartItemId));

        existingCartItem.setQuantity(quantity);
        return cartRepo.save(existingCartItem);
    }


    public void removeFromCart(String username, int productId) {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        CartItem cartItem = cartRepo.findByUserAndProductId(user, productId);
        if (cartItem == null) {
            throw new IllegalArgumentException("Cart item not found for product ID: " + productId);
        }

        cartRepo.delete(cartItem);
    }


    public void clearCart(String username) {
        Users user = userRepo.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        List<CartItem> cartItems = cartRepo.findByUser(user);
        cartRepo.deleteAll(cartItems);
    }
}

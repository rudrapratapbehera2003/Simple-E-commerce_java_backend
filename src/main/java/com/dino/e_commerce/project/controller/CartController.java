package com.dino.e_commerce.project.controller;

import com.dino.e_commerce.project.model.CartItem;
import com.dino.e_commerce.project.model.ProductData;
import com.dino.e_commerce.project.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/{username}")
    public ResponseEntity<?> getCartItems(@PathVariable String username) {
        try {
            List<CartItem> cartItems = cartService.getCartItemsByUsername(username);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch cart items: " + e.getMessage());
        }
    }


    @PostMapping("/add/{username}")
    public ResponseEntity<String> addToCart(@PathVariable String username, @RequestBody ProductData productData) {
        try {
            cartService.addToCart(username, productData);
            return ResponseEntity.ok("Product added to cart successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to add product to cart: " + e.getMessage());
        }
    }


    @DeleteMapping("/remove/{username}/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable String username, @PathVariable int productId) {
        try {
            cartService.removeFromCart(username, productId);
            return ResponseEntity.ok("Product removed successfully from the cart");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to remove product from cart: " + e.getMessage());
        }
    }


    @DeleteMapping("/clear/{username}")
    public ResponseEntity<String> clearCart(@PathVariable String username) {
        try {
            cartService.clearCart(username);
            return ResponseEntity.ok("Cart cleared successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to clear cart: " + e.getMessage());
        }
    }
}

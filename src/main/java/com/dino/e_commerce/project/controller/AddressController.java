package com.dino.e_commerce.project.controller;

import com.dino.e_commerce.project.model.UserAddress;
import com.dino.e_commerce.project.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService service;


    @GetMapping("/user/{username}")
    public ResponseEntity<List<UserAddress>> getUserAddress(@PathVariable String username) {
        try {

            List<UserAddress> addresses = service.getUserAddressByUsername(username);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping("/user/{username}")
    public ResponseEntity<UserAddress> addAddress(@RequestBody UserAddress userAddress, @PathVariable String username) {
        try {

            UserAddress savedAddress = service.addAddressToUser(username, userAddress);

            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    @PutMapping("/{addressId}")
    public ResponseEntity<UserAddress> updateAddress(@PathVariable int addressId, @RequestBody UserAddress updatedAddress) {
        try {

            UserAddress address = service.updateAddress(addressId, updatedAddress);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable int addressId) {
        try {

            service.deleteUserAddress(addressId);
            return ResponseEntity.ok("Address successfully deleted");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Failed to delete address");
        }
    }
}

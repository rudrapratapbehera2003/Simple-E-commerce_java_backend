package com.dino.e_commerce.project.service;

import com.dino.e_commerce.project.model.UserAddress;
import com.dino.e_commerce.project.model.Users;
import com.dino.e_commerce.project.repository.AddressRepo;
import com.dino.e_commerce.project.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepo repo;

    @Autowired
    private UserRepo userRepo;


    public List<UserAddress> getUserAddressByUsername(String username) {

        List<UserAddress> addresses = repo.findByUser_Username(username);


        addresses.forEach(address -> address.setUser(null));

        return addresses;
    }


    public UserAddress addAddressToUser(String username, UserAddress userAddress) {

        Users user = userRepo.findByUsername(username);
        userAddress.setUser(user);
        return repo.save(userAddress);
    }


    public UserAddress updateAddress(int addressId, UserAddress updatedAddress) {
        UserAddress existingAddress = repo.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address not found with ID: " + addressId));
        updatedAddress.setUser(existingAddress.getUser());
        return repo.save(updatedAddress);
    }


    public void deleteUserAddress(int addressId) {

        repo.deleteById(addressId);
    }
}

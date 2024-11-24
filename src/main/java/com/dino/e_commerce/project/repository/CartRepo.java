package com.dino.e_commerce.project.repository;

import com.dino.e_commerce.project.model.CartItem;
import com.dino.e_commerce.project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartItem,Integer> {

    List<CartItem> findByUser(Users user);

    CartItem findByUserAndProductId(Users user, int id);

}

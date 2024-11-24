package com.dino.e_commerce.project.repository;

import com.dino.e_commerce.project.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<UserAddress, Integer> {


    @Query("SELECT a FROM UserAddress a WHERE a.user.username = :username")
    List<UserAddress> findByUser_Username(@Param("username") String username);
}

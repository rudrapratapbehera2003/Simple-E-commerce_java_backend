package com.dino.e_commerce.project.model;

import jakarta.persistence.*;

@Entity
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String country;
    private String village;
    private String post;
    private String dist;
    private String block;
    private String via;
    private Long zip;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public Long getZip() {
        return zip;
    }

    public void setZip(Long zip) {
        this.zip = zip;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", village='" + village + '\'' +
                ", post='" + post + '\'' +
                ", dist='" + dist + '\'' +
                ", block='" + block + '\'' +
                ", via='" + via + '\'' +
                ", zip=" + zip +
                ", user=" + user +
                '}';
    }
}

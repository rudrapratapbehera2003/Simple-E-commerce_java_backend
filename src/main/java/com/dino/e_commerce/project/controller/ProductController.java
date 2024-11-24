package com.dino.e_commerce.project.controller;

import com.dino.e_commerce.project.model.ProductData;
import com.dino.e_commerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    
    @RequestMapping("/products")
    public List<ProductData> getProducts(){
        return service.getProducts();
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductData> getProductById(@PathVariable int id){
        ProductData product = service.getProductById(id);
        if(product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/products")
    public ResponseEntity<?> addProducts(@RequestPart ProductData product, @RequestPart MultipartFile imageFile) {

        try {
            ProductData product1 =  service.addProducts(product, imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/products/{productId}/image")
    public  ResponseEntity<byte[]> getImageBbyProductId(@PathVariable int productId){
        ProductData product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart ProductData product, @RequestPart MultipartFile imageFile){

        ProductData product1 = null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if(product1 !=null){
            return new ResponseEntity<>("updated successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        ProductData product = service.getProductById(id);
        if(product != null){
             service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }else {
                return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/products/search")
    public ResponseEntity<List<ProductData>> searchProduct(@RequestParam String keyword){
        List<ProductData> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}

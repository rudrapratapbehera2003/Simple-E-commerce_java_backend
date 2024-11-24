package com.dino.e_commerce.project.service;

import com.dino.e_commerce.project.model.ProductData;
import com.dino.e_commerce.project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo repo;

    public List<ProductData> getProducts() {
        return repo.findAll();
    }

    public ProductData addProducts(ProductData product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public ProductData getProductById(int id) {
        return repo.findById(id).orElse(new ProductData());
    }

    public ProductData updateProduct(int id, ProductData product, MultipartFile imageFile) throws IOException {
            product.setImageData(imageFile.getBytes());
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            return repo.save(product);
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

    public List<ProductData> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}

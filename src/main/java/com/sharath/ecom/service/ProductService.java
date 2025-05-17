package com.sharath.ecom.service;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sharath.ecom.model.Product;
import com.sharath.ecom.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;


    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(String id) {
        return repo.findById(String.valueOf(id)).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Product updateProduct(String id, Product product, MultipartFile imageFile) throws IOException {
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());

        return repo.save(product);
    }

    public void deleteProduct(String id) {
        repo.deleteById(String.valueOf(id));
    }

    public List<Product> searchProducts(String keyword) {
        return repo.searchByName(keyword);
    }


}

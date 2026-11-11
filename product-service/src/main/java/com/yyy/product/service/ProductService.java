package com.yyy.product.service;

import com.yyy.product.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> list(String productName,String category);
    void add(Product product);
    void update(Product product);
    void delete(Long id);
}

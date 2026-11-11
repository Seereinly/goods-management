package com.yyy.product.service.impl;

import com.yyy.product.entity.Product;
import com.yyy.product.mapper.ProductMapper;
import com.yyy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list(String productName,String category) {
        return productMapper.selectList(productName,category);
    }


    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.deleteById(id);
    }


}

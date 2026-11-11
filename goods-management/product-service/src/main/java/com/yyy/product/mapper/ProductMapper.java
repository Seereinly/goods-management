package com.yyy.product.mapper;

import com.yyy.product.entity.Product;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ProductMapper {
    List<Product> selectList(@Param("productName") String productName,@Param("category") String category);
    void insert(Product product);
    void update(Product product);
    void deleteById(@Param("id") Long id);
}

package com.yyy.product.mapper;

import com.yyy.product.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void insert(Order order);
    List<Order> selectAll();

}

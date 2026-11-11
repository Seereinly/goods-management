package com.yyy.product.service;

import lombok.Data;

@Data
public class StatisticsVO {
    private Long totalProduct;     //商品总数量
    private Integer totalStock;    //全部商品库存总和
    private Long warnStockCount;   //库存小于50的商品数量
}

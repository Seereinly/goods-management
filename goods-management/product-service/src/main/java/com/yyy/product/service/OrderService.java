package com.yyy.product.service;

import com.yyy.product.common.Result;
import com.yyy.product.entity.Order;

import java.util.List;

public interface OrderService {
    /**
     * 下单
     * @param productId 商品id
     * @param buyNum 购买数量
     * @param userId 当前登录用户id（网关透传X‑User‑Id）
     * @return
     */
    Result<Void> createOrder(Long productId, Integer buyNum, Long userId);

    /**
     * 获取统计数据：商品总数、总库存、库存预警(stock<50)数量
     */
    Result<StatisticsVO> getStatistics();

    /**
     * 查询全部订单
     */
    List<Order> selectOrderList();

}

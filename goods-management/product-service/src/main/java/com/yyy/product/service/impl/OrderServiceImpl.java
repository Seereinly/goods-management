package com.yyy.product.service.impl;

import com.yyy.product.common.Result;
import com.yyy.product.entity.Order;
import com.yyy.product.entity.Product;
import com.yyy.product.feign.UserFeignClient;
import com.yyy.product.mapper.OrderMapper;
import com.yyy.product.mapper.ProductMapper;
import com.yyy.product.service.OrderService;
import com.yyy.product.service.StatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    /**
     * 下单，加上事务：生成订单 + 扣库存要么一起成功，要么一起失败
     */
    @Override
    @Transactional
    public Result<Void> createOrder(Long productId, Integer buyNum, Long userId) {
        // ========== Feign远程调用auth‑service，校验用户是否存在【微服务间调用核心】 ==========
        Result<Boolean> userResult = userFeignClient.checkUserExist(userId);
        if(userResult.getData() == null || !userResult.getData()){
            return Result.fail("用户不存在，禁止下单");
        }

        // 购买数量合法性校验
        if(buyNum == null || buyNum <= 0){
            return Result.fail("购买数量必须大于0");
        }

        // 查询商品
        List<Product> productList = productMapper.selectList(null,null);
        Product target = null;
        for(Product p : productList){
            if(p.getId().equals(productId)){
                target = p;
                break;
            }
        }
        if(target == null){
            return Result.fail("该商品不存在");
        }

        // 判断库存
        if(target.getStock() < buyNum){
            return Result.fail("库存不足，当前库存："+target.getStock());
        }

        //扣减库存
        target.setStock(target.getStock() - buyNum);
        productMapper.update(target);

        //生成订单
        Order order = new Order();
        order.setProductId(productId);
        order.setBuyNum(buyNum);
        orderMapper.insert(order);

        return Result.success(null);
    }

    @Override
    public Result<StatisticsVO> getStatistics() {
        List<Product> list = productMapper.selectList(null,null);
        StatisticsVO vo = new StatisticsVO();
        vo.setTotalProduct((long) list.size());

        int sumStock = 0;
        long warnCount = 0;
        for(Product p : list){
            sumStock += p.getStock();
            if(p.getStock() < 50){
                warnCount++;
            }
        }
        vo.setTotalStock(sumStock);
        vo.setWarnStockCount(warnCount);
        return Result.success(vo);
    }

    @Override
    public List<Order> selectOrderList() {
        return orderMapper.selectAll();
    }

}

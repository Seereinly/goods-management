package com.yyy.product.controller;

import com.yyy.product.common.Result;
import com.yyy.product.entity.Order;
import com.yyy.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单接口
     * 从请求头 X‑User‑Id 获取网关透传的登录用户id
     */
    @PostMapping("/create")
    public Result<Void> createOrder(@RequestParam Long productId,
                                    @RequestParam Integer buyNum,
                                    HttpServletRequest request){
        String userIdStr = request.getHeader("X-User-Id");
        if(userIdStr == null){
            return Result.fail("获取登录用户信息失败，请重新登录");
        }
        Long userId = Long.parseLong(userIdStr);
        return orderService.createOrder(productId,buyNum,userId);
    }
    @GetMapping("/list")
    public Result<List<Order>> orderList(){
        List<Order> orderList = orderService.selectOrderList();
        return Result.success(orderList);
    }

}

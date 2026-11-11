package com.yyy.product.feign;

import com.yyy.product.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 远程调用 auth‑service 服务
 * name对应nacos里面服务名：auth‑service
 */
@FeignClient(name = "auth-service")
public interface UserFeignClient {

    /**
     * 远程调用auth的接口：校验用户是否存在
     * 地址：GET /auth/user/exist?userId=xxx
     */
    @GetMapping("/auth/user/exist")
    Result<Boolean> checkUserExist(@RequestParam("userId") Long userId);
}

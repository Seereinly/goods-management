# SpringCloudAlibaba 商品订单微服务系统
基于 Spring Cloud Alibaba、Gateway、Nacos、Feign、JWT 实现。

## 功能
- 用户登录、JWT 网关鉴权
- 商品增删改查、库存统计、库存预警
- 用户下单，事务保证扣库存 + 创建订单
- Nacos 服务注册发现

## 启动顺序
1. 启动 Nacos
2. auth-service
3. product-service
4. gateway

## 数据库
MySQL，三张表：sys_user、product、order

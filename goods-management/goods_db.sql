CREATE DATABASE IF NOT EXISTS goods_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE goods_db;

-- 用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` VARCHAR(50) NOT NULL COMMENT '用户名',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 商品表（移除create_user_id，新增category商品分类）
CREATE TABLE IF NOT EXISTS `product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `product_name` VARCHAR(100) NOT NULL COMMENT '商品名称',
  `price` DECIMAL(10,2) NOT NULL COMMENT '商品价格',
  `stock` INT NOT NULL DEFAULT 0 COMMENT '库存数量',
  `category` VARCHAR(50) COMMENT '商品分类',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- 订单表（下单业务用）
CREATE TABLE IF NOT EXISTS `order` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '订单id',
  `product_id` BIGINT NOT NULL COMMENT '商品id',
  `buy_num` INT NOT NULL COMMENT '购买数量',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- 测试账号
INSERT INTO `user` (`username`, `password`)
VALUES
('admin', '123456'),
('user', '123456');

-- 初始10条商品数据，带上分类
INSERT INTO `product` (`product_name`, `price`, `stock`, `category`)
VALUES
('矿泉水', 2.00, 500, '饮品'),
('抽纸', 15.90, 200, '日用品'),
('纯棉毛巾', 12.50, 150, '日用品'),
('保温杯', 69.00, 80, '日用品'),
('雨伞', 35.00, 120, '日用品'),
('牙刷', 9.90, 300, '日用品'),
('拖鞋', 25.00, 160, '日用品'),
('笔记本', 8.00, 220, '文具'),
('中性笔', 2.50, 400, '文具'),
('收纳盒', 19.90, 90, '日用品');

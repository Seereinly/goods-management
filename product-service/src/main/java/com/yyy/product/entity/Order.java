package com.yyy.product.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long productId;
    private Integer buyNum;
    private LocalDateTime createTime;
}

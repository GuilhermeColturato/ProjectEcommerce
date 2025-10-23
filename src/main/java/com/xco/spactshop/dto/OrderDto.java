package com.xco.spactshop.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class OrderDto {

    private String id;

    private String userId;

    private List<OrderItemDto> items;

    private String status;

    private Instant date;

    private BigDecimal totalValue;

    @Data
    public static class OrderItemDto {

        private String productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}

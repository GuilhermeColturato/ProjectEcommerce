package com.xco.spactshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    private String id;

    private String userId;

    private List<CartItemDto> items;

    @Data
    public static class CartItemDto {

        private String productId;
        private Integer quantity;
    }
}

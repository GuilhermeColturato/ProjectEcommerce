package com.xco.spactshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {

    private String id;

    @NotBlank
    private String name;

    private String description;

    @PositiveOrZero
    private BigDecimal price;

    @PositiveOrZero
    private Integer stock;

    private String category;

    private List<String> images;
}

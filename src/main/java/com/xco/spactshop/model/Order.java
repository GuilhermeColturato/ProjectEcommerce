package com.xco.spactshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId;

    private List<OrderItem> items;

    private String status; // "EM_PROCESSAMENTO"|"PAGO"|"ENVIADO"|"ENTREGUE"|"CANCELADO"

    private Instant date;

    private BigDecimal totalValue;

    @Data
    public static class OrderItem {

        private String productId;
        private Integer quantity;
        private BigDecimal unitPrice;
    }
}

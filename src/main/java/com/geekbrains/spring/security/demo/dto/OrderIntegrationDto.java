package com.geekbrains.spring.security.demo.dto;

import com.geekbrains.spring.security.demo.entities.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderIntegrationDto {
    private Long orderId;
    private String userEmail;
    private List<OrderDetailsDto> details;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailsDto {
        private String product;
        private BigDecimal price;
        private Double amount;
        private BigDecimal sum;

        public OrderDetailsDto(Detail details) {
            this.product = details.getProduct().getName();
            this.price = details.getPrice();
            this.amount = details.getAmount();
            this.sum = details.getPrice().multiply(BigDecimal.valueOf(details.getAmount()));
        }
    }
}

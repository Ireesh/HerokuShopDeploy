package com.geekbrains.spring.security.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDto {
    private int amountProducts;
    private BigDecimal sum;
    private List<BucketDetailDto> bucketDetails = new ArrayList<>();

    public void aggregate(){
        this.amountProducts = bucketDetails.stream().map(BucketDetailDto::getAmount).mapToInt(Integer::intValue).sum();
        this.sum = bucketDetails.stream()
                    .map(BucketDetailDto::getSum)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (sum == null) {
            sum = BigDecimal.valueOf(0);
        }
    }
}
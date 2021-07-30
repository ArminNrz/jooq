package com.jooq.repository.dto;

import lombok.Data;

@Data
public class ProductCompanyDTO {

    private int productId;
    private String productName;
    private double price;
    private String companyName;
}

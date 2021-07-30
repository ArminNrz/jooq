package com.jooq.controller.dto.product;

import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class ProductDTO {

    @NotEmpty
    String name;

    @NotNull
    int companyId;

    @NotNull
    double price;
}

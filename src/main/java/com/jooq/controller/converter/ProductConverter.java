package com.jooq.controller.converter;

import com.jooq.controller.dto.product.ProductDTO;
import com.jooq.sample.model.tables.pojos.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product convert(ProductDTO dto) {

        var product = new Product();
        product.setName(product.getName());
        product.setPrice(product.getPrice());
        product.setCompanyId(product.getCompanyId());

        return product;
    }
}

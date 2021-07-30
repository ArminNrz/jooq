package com.jooq.repository;

import com.jooq.repository.dto.ProductCompanyDTO;
import com.jooq.sample.model.Tables;
import com.jooq.sample.model.tables.pojos.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@AllArgsConstructor
public class ProductRepository {

    private final DSLContext context;

    public int insertProduct(Product product) {

        log.info("Try to save product: {}", product);

        var productId = context.insertInto(Tables.PRODUCT,
                Tables.PRODUCT.NAME, Tables.PRODUCT.COMPANY_ID)
                .values(product.getName(), product.getCompanyId())
                .execute();

        log.info("Saved product with id: {}", productId);

        return productId;
    }

    public List<ProductCompanyDTO> findByCompanyName(String companyName) {

        log.info("try to find products by companyName: {}", companyName);

        var productRecord = context.select(
                Tables.PRODUCT.ID.as("productId"),
                Tables.PRODUCT.NAME.as("productName"),
                Tables.PRODUCT.PRICE.as("price"),
                Tables.COMPANY.NAME.as("companyName")
        )
                .from(Tables.PRODUCT.join(Tables.COMPANY)
                .on(Tables.PRODUCT.COMPANY_ID.eq(Tables.COMPANY.ID)))
                .where(Tables.COMPANY.NAME.eq(companyName))
                .fetch();

        var products = productRecord.into(ProductCompanyDTO.class);

        log.info("Found products: {}", products);

        return products;
    }
}

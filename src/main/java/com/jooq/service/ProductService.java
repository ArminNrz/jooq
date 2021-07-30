package com.jooq.service;

import com.jooq.repository.CompanyRepository;
import com.jooq.repository.ProductRepository;
import com.jooq.repository.dto.ProductCompanyDTO;
import com.jooq.sample.model.tables.pojos.Product;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public int insertProduct(Product product) {

        log.debug("Try to save product: {}", product);

        var isCompanyExist = companyRepository.isExist(product.getCompanyId());

        if (!isCompanyExist) {
            throw Problem.valueOf(Status.BAD_REQUEST, "Product company is not exist");
        }

        return productRepository.insertProduct(product);
    }

    public List<ProductCompanyDTO> getByCompanyName(String companyName) {

        log.debug("Try to find product by companyName: {}", companyName);

        var products = productRepository.findByCompanyName(companyName);

        if (products.size() == 0) {
            throw Problem.valueOf(Status.NOT_FOUND, "Can not found any product with this company name");
        }

        return products;
    }
}

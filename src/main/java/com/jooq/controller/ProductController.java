package com.jooq.controller;

import com.jooq.controller.converter.ProductConverter;
import com.jooq.controller.dto.product.ProductDTO;
import com.jooq.repository.ProductRepository;
import com.jooq.repository.dto.ProductCompanyDTO;
import com.jooq.sample.model.tables.pojos.Product;
import com.jooq.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @PostMapping
    public ResponseEntity<Integer> saveProduct(@Valid @RequestBody ProductDTO dto) {

        log.info("Request to save product: {}", dto);

        var product = productConverter.convert(dto);

        var productId = productService.insertProduct(product);

        log.debug("Saved product with id: {}", productId);

        return ResponseEntity
                .created(URI.create("/products" + product.getName()))
                .body(productId);
    }

    @GetMapping("/{company-name}")
    public ResponseEntity<List<ProductCompanyDTO>> findByCompany(@PathVariable("company-name") String companyName) {

        log.info("Request to find products by companyName: {}", companyName);

        var products = productService.getByCompanyName(companyName);

        return ResponseEntity.ok(products);
    }
}

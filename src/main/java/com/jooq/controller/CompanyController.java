package com.jooq.controller;

import com.jooq.controller.converter.CompanyConverter;
import com.jooq.controller.dto.company.CompanyDTO;
import com.jooq.sample.model.tables.pojos.Company;
import com.jooq.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/v1/companys")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyConverter companyConverter;

    @PostMapping
    public ResponseEntity<Integer> createCompany(@Valid @RequestBody CompanyDTO dto) {

        log.info("Request to create company: {}", dto);

        var company = companyConverter.convert(dto);

        var id = companyService.saveCompany(company);

        log.debug("Saved company with id: {}", id);

        return ResponseEntity
                .created(URI.create("/companys" + company.getName()))
                .body(id);
    }

    @GetMapping("/{company-id}")
    public ResponseEntity<Company> getCompany(@PathVariable("company-id") int id) {

        log.info("Request to get company by id: {}", id);

        var companyRecord = companyService.getCompany(id);

        log.debug("Found company: {}", companyRecord);

        return ResponseEntity
                .ok(companyRecord);
    }
}

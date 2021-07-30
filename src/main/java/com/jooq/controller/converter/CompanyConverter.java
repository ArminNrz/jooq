package com.jooq.controller.converter;

import com.jooq.controller.dto.company.CompanyDTO;
import com.jooq.sample.model.tables.pojos.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter {

    public Company convert(CompanyDTO dto) {

        var company = new Company();
        company.setName(company.getName());

        return company;
    }
}

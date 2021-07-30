package com.jooq.service;

import com.jooq.repository.CompanyRepository;
import com.jooq.sample.model.tables.pojos.Company;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public int saveCompany(Company company) {

        log.debug("Try to save company: {}", company);

        return companyRepository.insert(company);
    }

    public Company getCompany(int id) {

        log.debug("Try to get company with id: {}", id);

        return companyRepository.find(id);
    }
}

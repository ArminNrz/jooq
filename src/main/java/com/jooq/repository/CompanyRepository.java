package com.jooq.repository;

import com.jooq.sample.model.Tables;
import com.jooq.sample.model.tables.pojos.Company;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.Objects;

@Repository
@Slf4j
@AllArgsConstructor
public class CompanyRepository {

    private final DSLContext context;

    public int insert(Company company) {

        log.info("Try to save company: {}", company);

        var companyId = context
                .insertInto(Tables.COMPANY,
                        Tables.COMPANY.NAME)
                .values(company.getName())
                .execute();

        log.info("Company with id: {} is saved", companyId);

        return companyId;
    }

    public Company find(int id) {

        log.info("Try to find company with id: {}", id);

        var companyRecord = context
                .selectFrom(Tables.COMPANY)
                .where(Tables.COMPANY.ID.eq(id))
                .fetchAny();

        if (Objects.isNull(companyRecord)) {
            log.warn("No company found");
            throw Problem.valueOf(Status.NOT_FOUND, "Can not found company");
        }

        var company = companyRecord.into(Company.class);

        log.info("Found company: {} in DB", company);

        return company;
    }

    public boolean isExist(int id) {

        log.info("Try to find company with id: {}, is exist", id);

        var isExist = context.fetchExists(
                context.selectOne()
                .from(Tables.COMPANY)
                .where(Tables.COMPANY.ID.eq(id))
        );

        log.info("company with id: {}, isExist: {}", id, isExist);

        return isExist;
    }
}

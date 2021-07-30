package com.jooq.controller.dto.company;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class CompanyDTO {
    @NotEmpty
    String name;
}

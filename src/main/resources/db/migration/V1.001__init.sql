CREATE TABLE company(
    id INT GENERATED ALWAYS AS IDENTITY,
    name varchar(255),
    PRIMARY KEY (id)
);

CREATE TABLE product(
    id INT GENERATED ALWAYS AS IDENTITY,
    company_id INT,
    name varchar(255),
    price double precision,
    PRIMARY KEY (id),
    CONSTRAINT fk_company
                    FOREIGN KEY(company_id)
                    REFERENCES company(id)
);
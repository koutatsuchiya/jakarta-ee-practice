CREATE TABLE department (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    start_date              DATE,
    name                    VARCHAR(200),
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE department_location (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    location                VARCHAR(100),
    department_id           INT,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employee (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name              VARCHAR(20),
    middle_name             VARCHAR(20),
    last_name               VARCHAR(20),
    salary                  DOUBLE PRECISION,
    gender                  VARCHAR(10),
    date_of_birth           DATE,
    department_id           INT,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE project (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    project_name            VARCHAR(100),
    area                    VARCHAR(100),
    department_id           INT,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE assignment (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number_of_hour          INTEGER,
    employee_id             INT,
    project_id              INT,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE relatives (
    id                      INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    full_name               VARCHAR(100),
    gender                  VARCHAR(10),
    phone_number            VARCHAR(15),
    relationship            VARCHAR(100),
    employee_id             INT,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

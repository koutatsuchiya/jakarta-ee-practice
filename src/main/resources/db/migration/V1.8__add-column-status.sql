ALTER TABLE department
    ADD COLUMN status VARCHAR(10) DEFAULT 'ACTIVE';

ALTER TABLE employee
    ADD COLUMN status VARCHAR(10) DEFAULT 'ACTIVE';

ALTER TABLE project
    ADD COLUMN status VARCHAR(10) DEFAULT 'ACTIVE';
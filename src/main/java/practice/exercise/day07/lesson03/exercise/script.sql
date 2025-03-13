CREATE TABLE IF NOT EXISTS employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    wages_per_hour DECIMAL(10, 2) NOT NULL,
    hours_worked DECIMAL(10, 2) NOT NULL,
    total_salary DECIMAL(12, 2) GENERATED ALWAYS AS (wages_per_hour * hours_worked) STORED
);

CREATE INDEX idx_employee_name ON employee(last_name, first_name);
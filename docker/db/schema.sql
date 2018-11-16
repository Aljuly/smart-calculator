-- DROP DATABASE IF EXISTS calculator;
-- DROP USER IF EXISTS calcuser;
CREATE USER calcuser WITH PASSWORD '1';

-- RECREATE THE DATABASE --
-- CREATE DATABASE calculator TEMPLATE template0 OWNER calcuser;
\connect calculator;
GRANT ALL PRIVILEGES ON DATABASE "calculator" TO calcuser;

DROP SCHEMA IF EXISTS calculator CASCADE;

BEGIN;

CREATE SCHEMA calculator
    AUTHORIZATION postgres;

GRANT ALL ON SCHEMA calculator TO postgres;
GRANT ALL ON SCHEMA calculator TO calcuser;
ALTER DEFAULT PRIVILEGES IN SCHEMA calculator
GRANT ALL ON TABLES TO calcuser;

CREATE SEQUENCE calculator.global_seq START 100000;
ALTER SEQUENCE calculator.global_seq
    OWNER TO postgres;
GRANT ALL ON SEQUENCE calculator.global_seq TO postgres;
GRANT ALL ON SEQUENCE calculator.global_seq TO calcuser;

CREATE TABLE calculator.users
(
  user_id       INTEGER PRIMARY KEY DEFAULT nextval('calculator.global_seq'),
  name          VARCHAR     NOT NULL,
  hash          VARCHAR     NOT NULL,
  login         VARCHAR     NOT NULL,
  email         VARCHAR     NOT NULL,
  date          TIMESTAMP   NOT NULL
);

ALTER TABLE calculator.users
    OWNER to postgres;
GRANT ALL ON TABLE calculator.users TO calcuser;
GRANT ALL ON TABLE calculator.users TO postgres;

CREATE UNIQUE INDEX users_unique_id_idx ON calculator.users (user_id);


CREATE TABLE calculator.operations
(
  operation_id  INTEGER PRIMARY KEY DEFAULT nextval('calculator.global_seq'),
  name          VARCHAR     NOT NULL,
  description   VARCHAR
);

ALTER TABLE calculator.operations
    OWNER to postgres;
GRANT ALL ON TABLE calculator.operations TO calcuser;
GRANT ALL ON TABLE calculator.operations TO postgres;

CREATE UNIQUE INDEX operation_unique_id_idx
    ON calculator.operations USING btree
    (operation_id);
    

INSERT INTO calculator.operations(name, description)
    VALUES  ('Addition', 'Two numbers, consisting of two or more digits, it is convenient to fold in a column. To do this, we write the numbers one under the other with the observance of digits. Addition occurs from the right to the left: at first units, then tens, hundreds and so on are added. The amount of each column is written below it. If the sum of the column consists of two digits, the first one is added to the next column.'),
            ('Subtraction', 'Subtraction online allows you to find the result of subtraction (difference) of two numbers, and get a completely defined subtraction process in a column. You can enter natural numbers in the online calculator. To calculate it, just enter the numbers and press the = button'),
	        ('Multiplication', 'Multiplication in an online column allows you to find the result of multiplication (product) of two numbers by a column, and get the fully-written multiplication process in columns. You can enter natural numbers in the online calculator. To calculate it, just enter the numbers and press the = button'),
            ('Division', 'Long Division online allows you to divide two numbers in a column, and get a fully-written process of dividing into a column with obtaining the integer part and the remainder.'),
	        ('Division with period', 'This online calculator will help you understand how to make a Long Division of two integers. The Long division calculator will very simply and quickly calculate the quotient and give a detailed solution to the problem');

COMMIT;
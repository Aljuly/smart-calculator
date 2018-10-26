DROP DATABASE IF EXISTS calculator;
DROP USER IF EXISTS calcuser;
CREATE USER calcuser WITH PASSWORD '1';

-- RECREATE THE DATABASE --
CREATE DATABASE calculator TEMPLATE template0 OWNER calcuser;
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
    
COMMIT;
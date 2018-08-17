REVOKE ALL PRIVILEGES ON DATABASE "calculator" from calcuser;
REASSIGN OWNED BY calcuser TO postgres;
DROP DATABASE calculator;
DROP USER calcuser;
CREATE USER calcuser WITH PASSWORD '1';

-- RECREATE THE DATABASE --
CREATE DATABASE calculator OWNER calcuser;
GRANT ALL PRIVILEGES ON DATABASE "calculator" TO calcuser;

BEGIN;
DROP SCHEMA calculator CASCADE;

CREATE SCHEMA calculator;

CREATE SEQUENCE calculator.global_seq START 100000;

CREATE TABLE calculator.users
(
  user_id       INTEGER PRIMARY KEY DEFAULT nextval('calculator.global_seq'),
  name          VARCHAR     NOT NULL,
  hash          VARCHAR     NOT NULL,
  login         VARCHAR     NOT NULL,
  email         VARCHAR     NOT NULL,
  date          TIMESTAMP   NOT NULL
);
CREATE UNIQUE INDEX users_unique_id_idx ON calculator.users (user_id);

CREATE TABLE calculator.operations
(
  operation_id  INTEGER DEFAULT nextval('calculator.global_seq'),
  name          VARCHAR     NOT NULL,
  description   VARCHAR
);
COMMIT;
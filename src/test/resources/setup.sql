CREATE SCHEMA IF NOT EXISTS MYSCHEMA;

CREATE TABLE MYSCHEMA.companies (
  id int,
  name varchar(50),
  address text,
  email varchar(50),
  phone varchar(10)
);
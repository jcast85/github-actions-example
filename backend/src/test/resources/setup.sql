CREATE TABLE companies (
  id int,
  name varchar(50),
  address text,
  email varchar(50),
  phone varchar(10)
);

CREATE TABLE id_generator (
    gen_name VARCHAR(50) PRIMARY KEY,
    gen_value BIGINT NOT NULL
);

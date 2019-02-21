DROP TABLE IF EXISTS framework;

CREATE TABLE framework (
  id UUID PRIMARY KEY,
  name varchar(50),
  language varchar(50),
  description varchar(1024),
  web boolean default false
);
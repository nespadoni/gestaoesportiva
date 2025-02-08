CREATE TABLE usuario (

    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    login varchar(100) NOT NULL UNIQUE,
    senha varchar(100) NOT NULL,
    role varchar(100) NOT NULL

);
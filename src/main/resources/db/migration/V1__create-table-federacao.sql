create table federacao(

    id BIGSERIAL primary key,
    nome varchar(100) not null,
    sigla varchar(100) not null,
    email varchar(100) not null unique,
    presidente varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(100) not null,
    ativo boolean not null

);
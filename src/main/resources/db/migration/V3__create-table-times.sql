create table times(

    id BIGSERIAL primary key,
    nome varchar(100) not null,
    tecnico varchar(100) not null,
    presidente varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(100) not null,
    ativo boolean not null

);
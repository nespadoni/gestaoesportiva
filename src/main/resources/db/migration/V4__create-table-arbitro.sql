create table arbitro(

    id BIGSERIAL primary key,
    nome varchar(100) not null,
    dupla_id int unique,
    email varchar(100) not null,
    telefone varchar(100) not null,
    cpf varchar(100) not null,
    idade int not null,
    federacao_id int unique,
    ativo boolean not null,

    constraint dupla_id foreign key (dupla_id) references dupla(id) on delete set null,
    constraint federacao_id foreign key (federacao_id) references federacao(id) on delete set null

);
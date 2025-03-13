create table campeonato(

    id BIGSERIAL primary key,
    titulo varchar(100) not null,
    descricao varchar(100) not null,
    data date not null,
    federacao_id int unique,
    endereco varchar(100) not null,
    cidade int not null,
    uf int not null,
    ativo boolean not null,

    constraint federacao_id foreign key (federacao_id) references federacao(id) on delete set null

);

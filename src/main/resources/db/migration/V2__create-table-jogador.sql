create table jogador(

    id BIGSERIAL primary key,
    nome varchar(100) not null,
    times_id INT,
    cpf varchar(100) not null unique,
    sexo varchar(100) not null,
    categoria varchar(100) not null,
    posicao varchar(100) not null,
    ativo boolean not null,

    CONSTRAINT fk_times FOREIGN KEY (times_id) REFERENCES times(id) ON DELETE SET NULL
);
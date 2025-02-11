CREATE TABLE jogo (
                      id BIGSERIAL PRIMARY KEY,
                      nome VARCHAR(255) NOT NULL,
                      data DATE NOT NULL,
                      categoria VARCHAR(50) NOT NULL,
                      campeonato_id BIGINT NOT NULL,
                      time_casa_id BIGINT NOT NULL,
                      time_visitante_id BIGINT NOT NULL,
                      local VARCHAR(255),
                      cidade VARCHAR(100),
                      uf VARCHAR(2),
                      gols_time_casa INTEGER DEFAULT 0,
                      gols_time_visitante INTEGER DEFAULT 0,
                      penaltis_time_casa INTEGER DEFAULT 0,
                      penaltis_time_visitante INTEGER DEFAULT 0,
                      finalizado BOOLEAN DEFAULT FALSE,
                      ativo BOOLEAN DEFAULT TRUE,

                      CONSTRAINT fk_jogo_campeonato FOREIGN KEY (campeonato_id) REFERENCES campeonato(id),
                      CONSTRAINT fk_jogo_time_casa FOREIGN KEY (time_casa_id) REFERENCES times(id),
                      CONSTRAINT fk_jogo_time_visitante FOREIGN KEY (time_visitante_id) REFERENCES times(id)
);

# Gestão Esportiva - Sistema de Gestão para uma Federação de Esportes

## Descrição
Gestão Esportiva é um sistema backend desenvolvido em Java com Spring Boot, projetado para atender às necessidades de uma possível Federação de Esportes. Ele oferece funcionalidades essenciais para a gestão de campeonatos, times, jogadores e árbitros, garantindo uma administração eficiente e organizada.

## 📅 Funcionalidades Principais

### ✨ Federação de Esporte (Administração)
- CRUD de campeonatos
- CRUD de times
- CRUD de regras e normas
- Gestão de calendários de jogos

### 🏀 Times
- Cadastro e gerenciamento do time
- Inscrição em campeonatos
- Adição e remoção de jogadores

### 💪 Jogadores
- Consulta do time ao qual pertence
- Acesso ao cronograma de campeonatos
- Visualização de escalações

### ⚽ Árbitros
- Acesso à lista de jogos e campeonatos em que foram escalados

## 🔧 Tecnologias Utilizadas

- **Linguagem**: Java 23
- **Framework**: Spring Boot
  - Spring Web
  - Spring Data JPA
  - Spring Security (futuro)
- **Banco de Dados**: PostgreSQL
- **Ferramentas**:
  - Lombok
  - Flyway (para migração de banco de dados)
  - OpenAPI/Swagger (para documentação da API)

## ✅ Requisitos

- Java 123+
- Maven 3.8+
- PostgreSQL instalado e configurado

## 🔧 Configuração do Projeto

1. Clone este repositório:
   ```sh
   git clone https://github.com/nespadoni/gestaoesportiva.git
   cd GestaoEsportiva
   ```
2. Configure o banco de dados PostgreSQL:
   ```sql
   CREATE DATABASE gestaoesportiva;
   ```
3. Atualize o `application.properties` com suas credenciais do PostgreSQL:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gestaoesportiva
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```
4. Execute o projeto:
   ```sh
   mvn spring-boot:run
   ```
5. Acesse a API via Swagger:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## 💪 Contribuição

Se você quiser contribuir, siga estas etapas:
1. Faça um fork do repositório
2. Crie uma branch (`feature-minha-feature`)
3. Commit suas mudanças (`git commit -m 'Adicionando nova funcionalidade'`)
4. Envie para o repositório remoto (`git push origin feature-minha-feature`)
5. Abra um Pull Request

## 🚀 Planejamento Futuro

- Implementar autenticação e autorização com Spring Security e JWT
- Criar um dashboard para a federação acompanhar estatísticas
- Desenvolver uma integração com um sistema de notificações para os usuários e times
- Desenvolver Front-End 

---

Feito com ❤️ por Neto Spadoni

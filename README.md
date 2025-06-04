# Projeto DataConnect - DAO

## Descrição

Este projeto demonstra a implementação de um Data Access Object (DAO) em Java para interagir com um banco de dados. Ele fornece uma camada de abstração para realizar operações CRUD (Create, Read, Update, Delete) na tabela `userposjava`, além de gerenciar telefones associados a usuários.

## Funcionalidades

-   **CRUD de Usuários**:
    -   Inserir novos usuários.
    -   Listar todos os usuários.
    -   Buscar um usuário por ID.
    -   Atualizar informações de um usuário.
    -   Remover um usuário.
-   **Gerenciamento de Telefones**:
    -   Adicionar telefones a usuários.
 
  ## Bibliotecas Utilizadas

- **PostgreSQL JDBC Driver (42.7.3)**
  Driver oficial para conexão da aplicação Java com o banco de dados PostgreSQL.

- **JUnit 4 (4.13.2)**
  Framework para criação e execução de testes unitários em Java.

- **Hamcrest Core (1.3)**
  Biblioteca de matchers utilizada em conjunto com o JUnit para facilitar asserções em testes.

- **Checker Qual (3.42.0)**
  Conjunto de anotações para verificação de tipos, utilizado como dependência do driver PostgreSQL.

---

## Tecnologias Utilizadas

-   Java
-   JDBC
-   Banco de Dados (PostgreSQL)

## Pré-requisitos

-   Java Development Kit (JDK) 8 ou superior
-   Banco de Dados configurado e acessível
-   Driver JDBC para o banco de dados utilizado

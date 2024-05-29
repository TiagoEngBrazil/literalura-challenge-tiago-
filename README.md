# LiterAlura-Tiago

## Descrição
O LiterAlura é um projeto Java Spring que permite buscar, cadastrar e listar livros e autores em um banco de dados local, utilizando a Gutendex API para consulta de livros.

## Objetivo
O objetivo deste projeto é demonstrar o uso do Spring Boot para criar uma aplicação que realiza consultas em uma API externa, persiste os dados em um banco de dados local e fornece uma interface para listar e consultar os dados.

## Configuração do Ambiente
Para executar o projeto, é necessário ter o Java instalado, bem como o Maven para gerenciar as dependências. Além disso, é preciso configurar um banco de dados PostgreSQL e ter uma conexão com a internet para acessar a API Gutendex.

## Consumo da API
A API Gutendex é utilizada para buscar informações sobre livros. A consulta é feita pelo título do livro e os dados são inseridos no banco de dados local.

## Java
O projeto utiliza o Spring Boot para criar uma aplicação Java. São utilizadas as seguintes dependências:
- Spring Data JPA para persistência de dados
- Driver do PostgreSQL para conexão com o banco de dados

## Banco de Dados
O projeto utiliza um banco de dados PostgreSQL para armazenar os dados dos livros e autores. É necessário configurar a conexão com o banco de dados no arquivo `application.properties`.

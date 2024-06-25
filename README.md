# Library Management System

LMS é uma aplicação Java para gerenciar uma biblioteca. Este projeto utiliza Maven para gerenciar dependências e facilitar o processo de build.

## Funcionalidades

- CRUD para o gerenciamento de livros
- CURUD para o gerenciamento de patronos
- CRUD para o gerenciamento de emprestimos

## Pré-requisitos

- [Java 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.6+](https://maven.apache.org/download.cgi)

## Como executar o projeto

1. Clone o repositório:

    ```bash
    git clone https://github.com/moreira-arthur/mangahandler
    cd mangahandler
    ```

2. Compile o projeto utilizando Maven:

    ```bash
    mvn clean package 
    ```

3. Execute a aplicação:

    ```bash
    java -jar target/mangahandler-1.0-SNAPSHOT.jar 
    ```
4. Você também pode execeutar clicando no .jar que se encontra em 
   ```bash
    cd mangahandler/target 
    ```


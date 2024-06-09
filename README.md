# Projeto de CRUD de Veículos

Este projeto implementa um CRUD (Create, Read, Update, Delete) simples de veículos utilizando Spring Boot, JPA,
PostgreSQL, Flyway para migrações de banco de dados e Swagger para documentação da API.

## Tecnologias Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

- **Java 17**: Linguagem de programação.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Spring Data JPA**: Abstração de persistência de dados.
- **PostgreSQL**: Banco de dados relacional.
- **Flyway**: Ferramenta para migrações de banco de dados.
- **Swagger**: Ferramenta para documentação e teste de APIs.

## Endpoints

### Veículo Controller

   ```http
   GET /api/v1/veiculos/{id} - Descrição: Retorna um veículo pelo seu ID.
   ```

   ```http
   PUT /api/v1/veiculos/{id} - Descrição: Atualiza um veículo pelo seu ID.
   ```

   ```http
   DELETE /api/v1/veiculos/{id} - Descrição: Deleta um veículo pelo seu ID.
   ```

   ```http
   GET /api/v1/veiculos - Descrição: Retorna todos os veículos.
   ```

   ```http
   POST /api/v1/veiculos - Descrição: Cria um novo veículo.
   ```

   ```http
   GET /api/v1/veiculos/criteria - Descrição: Realiza uma busca avançada de veículos por chassi, marca ou modelo.
   ```

### Modelo Controller

  ```http
  GET /api/v1/modelos/{id} - Descrição: Retorna um modelo pelo seu ID.
  ``` 

  ```http
  PUT /api/v1/modelos/{id} - Descrição: Atualiza um modelo pelo seu ID.
  ```

  ```http
  DELETE /api/v1/modelos/{id} - Descrição: Deleta um modelo pelo seu ID.
  ```

  ```http
  GET /api/v1/modelos - Descrição: Retorna todos os modelos.
  ```

  ```http
  POST /api/v1/modelos - Descrição: Cria um novo modelo.
  ```

### Marca Controller

   ```http
   GET /api/v1/marcas/{id} - Descrição: Retorna uma marca pelo seu ID.
   ```

   ```http
   PUT /api/v1/marcas/{id} - Descrição: Atualiza uma marca pelo seu ID.
   ```

   ```http
   DELETE /api/v1/marcas/{id} - Descrição: Deleta uma marca pelo seu ID.
   ```

   ```http
   GET /api/v1/marcas  - Descrição: Retorna todas as marcas.
   ```

   ```http
   POST /api/v1/marcas - Descrição: Cria uma nova marca.
   ```

## Como Executar o Projeto

1. **Clone o repositório**:
   ```sh
   git clone https://github.com/Andersonfreitas21/locar
   cd locar
   ```

2. **Configure o Banco de Dados**:

- Crie um banco de dados PostgreSQL.
- Atualize as configurações de conexão no arquivo application.properties.

3. **Execute as Migrações Flyway**:

- As migrações Flyway serão executadas automaticamente quando você iniciar a aplicação.

4. **Inicie a aplicação**:

   ```sh
    ./mvmw spring-boot:run
   ```

5**Acesse a Documentação Swagger**:

- Abra o navegador e vá para http://localhost:8080/swagger-ui.html para visualizar e testar os endpoints.

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para obter mais detalhes.
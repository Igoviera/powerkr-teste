# Teste Técnico / Powerkr

## Instalação
1 Clone o repositório:
```bash
  git clone https://github.com/Igoviera/powerkr-teste.git
```
2 Instalar dependências com Maven

## Uso
A API estará acessível em http://localhost:8080

Faça login no app para poder realizar as operações CRUD:

```json
{
  "email": "admin@gmail.com",
  "password": "123456"
}
```

## A API fornece os seguintes endpoints:
```bash
  GET /api/powerkrTeste/login - Login no App
  GET /api/powerkrTeste/users/id - Buscar usuário por ID
  POT /api/powerkrTeste/users - Cadastrar usuário
  PUT /api/powerkrTeste/users - atualizar usuário 
  DELETE /api/powerkrTeste/users - deletar usuário 
```

## ✔️ Técnicas e tecnologias utilizadas

- ``Java 17``
- ``InteliJ IDEA``
- ``Paradigma de orientação a objetos``
- ``Spring Boot``
- ``Spring Data JPA``
- ``Hibernate``
- ``RESTful APIs``
- ``H2 Database``
- ``Maven``
- ``DTO (Data Transfer Objects)``
- ``Swagger``
- ``Insomnia``

## ✔️ Funcionalidades Principais

- ``Autenticação e autorização com JWT``
- ``CRUD de entidade``
- ``Tratamento de exceções``
- ``Validação de dados``
- ``Testes unitários``

## ✔️ Acesso através do swagger-ui
- ``http://localhost:8080/swagger-ui/index.html#/``
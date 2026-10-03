# Cadastro de Clientes

API REST para cadastro de **clientes** e **categorias**, desenvolvida com Spring Boot.
Cada cliente pode pertencer a uma categoria (relacionamento N:1).

## Tecnologias

- Java 21
- Spring Boot 3.5 (Web, Data JPA, Validation)
- PostgreSQL
- Lombok
- Springdoc OpenAPI (Swagger)
- JUnit 5, Mockito e H2 (testes)
- Docker Compose

## Estrutura

```
src/main/java/com/gestao/CadastroDeCliente
├── cliente/      # Controller, Service, Repository, DTO, Mapper e Model de Cliente
├── categoria/    # Controller, Service, Repository, DTO, Mapper e Model de Categoria
└── exception/    # Exceções da aplicação e tratamento global de erros
```

## Como rodar

**Pré-requisitos:** Java 21+ e Docker.

1. Suba o banco PostgreSQL:
   ```bash
   docker compose up -d
   ```
2. Rode a aplicação:
   ```bash
   cd CadastroDeCliente
   ./mvnw spring-boot:run
   ```
3. Acesse a documentação interativa: http://localhost:8080/swagger-ui.html

Para usar outro banco, defina as variáveis de ambiente `DATABASE_URL`, `DATABASE_USERNAME` e `DATABASE_PASSWORD`.

## Endpoints

### Clientes

| Método | Rota             | Descrição                  | Sucesso |
|--------|------------------|----------------------------|---------|
| GET    | `/clientes`      | Lista todos os clientes    | 200     |
| GET    | `/clientes/{id}` | Busca um cliente pelo ID   | 200     |
| POST   | `/clientes`      | Cadastra um cliente        | 201     |
| PUT    | `/clientes/{id}` | Atualiza um cliente        | 200     |
| DELETE | `/clientes/{id}` | Remove um cliente          | 204     |

Exemplo de corpo:

```json
{
  "nome": "Ana Souza",
  "email": "ana@email.com",
  "idade": 25,
  "categoriaId": 1
}
```

### Categorias

| Método | Rota               | Descrição                    | Sucesso |
|--------|--------------------|------------------------------|---------|
| GET    | `/categorias`      | Lista todas as categorias    | 200     |
| GET    | `/categorias/{id}` | Busca uma categoria pelo ID  | 200     |
| POST   | `/categorias`      | Cadastra uma categoria       | 201     |
| PUT    | `/categorias/{id}` | Atualiza uma categoria       | 200     |
| DELETE | `/categorias/{id}` | Remove uma categoria         | 204     |

Exemplo de corpo:

```json
{
  "tipo": "VIP"
}
```

## Regras e tratamento de erros

- Campos obrigatórios e formato de e-mail são validados (**400 Bad Request**).
- Buscar, atualizar ou remover um registro inexistente retorna **404 Not Found**.
- Não é permitido cadastrar dois clientes com o mesmo e-mail (**409 Conflict**).
- Uma categoria com clientes vinculados não pode ser removida (**409 Conflict**).

Exemplo de resposta de erro:

```json
{
  "timestamp": "2026-10-03T10:30:00",
  "status": 400,
  "mensagem": "Dados inválidos.",
  "campos": {
    "email": "E-mail inválido."
  }
}
```

## Testes

```bash
cd CadastroDeCliente
./mvnw test
```

Os testes usam banco H2 em memória, então não é preciso ter o PostgreSQL rodando.

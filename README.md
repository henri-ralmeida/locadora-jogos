# Locadora de Jogos API

**Catálogo de jogos com cadastro de usuários e autenticação JWT.**

Java 21 · Spring Boot 3.5.6 · Spring Security · MySQL · Flyway · OpenAPI

API para cadastrar, consultar, editar e excluir jogos de uma locadora. O catálogo inclui preço, gênero, mídia, classificação e disponibilidade.

> O escopo atual é o gerenciamento do catálogo. Não há fluxo implementado de retirada, devolução ou cobrança de aluguéis.

## Comece com Docker

Requisitos: Git, Docker e Docker Compose; portas 8080 e 3306 disponíveis.

```bash
git clone https://github.com/henri-ralmeida/locadora-jogos.git
cd locadora-jogos
docker compose up --build -d
docker compose logs -f app
```

O Compose inicia a API e o MySQL 8, com banco `locadora_db`. O Flyway aplica as migrations versionadas.

- **Swagger:** http://localhost:8080/swagger-ui.html
- **OpenAPI:** http://localhost:8080/v3/api-docs
- **API:** http://localhost:8080

Para encerrar preservando o volume do banco:

```bash
docker compose down
```

As credenciais do Compose são exemplos para desenvolvimento local.

## Executar com Java

Requisitos: **JDK 21** e MySQL disponível. O projeto inclui Maven Wrapper.

Configure as variáveis abaixo no seu terminal conforme o seu banco:

| Variável | Valor a fornecer |
| --- | --- |
| `SPRING_DATASOURCE_URL` | URL JDBC, por exemplo `jdbc:mysql://localhost:3306/locadorajogos?createDatabaseIfNotExist=true` |
| `SPRING_DATASOURCE_USERNAME` | Seu usuário MySQL |
| `SPRING_DATASOURCE_PASSWORD` | Sua senha MySQL |

Execute na raiz:

```bash
./mvnw spring-boot:run
```

No PowerShell: `.\mvnw.cmd spring-boot:run`.

A configuração padrão está em [application.yml](src/main/resources/application.yml). Use variáveis de ambiente para suas credenciais.

## Primeiro uso

Os comandos abaixo usam Bash e cURL. No Windows, use Git Bash ou o Swagger.

### 1. Cadastrar usuário

```bash
curl -i -X POST http://localhost:8080/login/usuarios \
  -H 'Content-Type: application/json' \
  -d '{"username":"gamer123","password":"SenhaDeExemplo"}'
```

### 2. Fazer login

```bash
curl -X POST http://localhost:8080/login \
  -H 'Content-Type: application/json' \
  -d '{"username":"gamer123","password":"SenhaDeExemplo"}'
```

A resposta contém o **token como texto**, não um objeto `{"token":"..."}`. Copie o valor para o campo **Authorize** do Swagger ou use `Authorization: Bearer [REDACTED:Authorization header] token]`.

### 3. Cadastrar jogo

```bash
curl -i -X POST http://localhost:8080/jogos \
  -H 'Content-Type: application/json' \
  -H 'Authorization: Bearer [REDACTED:Authorization header] token]' \
  -d '{"nomeJogo":"Super Mario 64","preco":150.0,"generoJogo":"ADVENTURE","tipoMidia":"FITA","categoria":"EVERYONE","disponivel":true}'
```

### 4. Consultar catálogo

```bash
curl 'http://localhost:8080/jogos?page=1' \
  -H 'Authorization: Bearer [REDACTED:Authorization header] token]'
```

A paginação começa em **1**, com **10 jogos por página**. O controller não oferece parâmetro para alterar esse tamanho.

## Rotas

| Método | Caminho | Acesso | Operação |
| --- | --- | --- | --- |
| POST | `/login/usuarios` | Público | Cadastrar usuário |
| POST | `/login` | Público | Obter token |
| POST | `/jogos` | Autenticado | Cadastrar jogo |
| GET | `/jogos?page=1` | Autenticado | Listar jogos |
| GET | `/jogos/{id}` | Autenticado | Consultar jogo |
| PUT | `/jogos/{id}` | Autenticado | Atualizar jogo |
| DELETE | `/jogos/{id}` | Autenticado | Excluir jogo |

Os caminhos não usam prefixo `/api/v1`. A exclusão bem-sucedida retorna HTTP 204.

### Valores do catálogo

| Campo | Valores |
| --- | --- |
| `generoJogo` | `ADVENTURE`, `RPG`, `FIGHTING`, `RACING` |
| `tipoMidia` | `FITA`, `CD_ROM`, `DVD_ROM` |
| `categoria` | `EVERYONE`, `EVERYONE_10_PLUS`, `TEEN`, `MATURE`, `ADULTS_ONLY`, `RATING_PENDING` |

Consulte o [DTO de jogo](src/main/java/br/com/retro/locadorajogos/dto/JogoDTO.java) e o Swagger para o contrato dos dados.

## Validação e testes

```bash
./mvnw test
```

No PowerShell: `.\mvnw.cmd test`. O teste de contexto Spring existente precisa de um banco acessível e das credenciais configuradas. O repositório não fornece relatório que sustente percentuais de cobertura por camada.

Para conferir o fluxo manualmente: cadastre um usuário, faça login, cadastre um jogo e teste consulta, atualização e exclusão.

## Organização

- [Controllers](src/main/java/br/com/retro/locadorajogos/controller): rotas de autenticação e catálogo.
- [Serviços](src/main/java/br/com/retro/locadorajogos/service): operações da aplicação.
- [Segurança](src/main/java/br/com/retro/locadorajogos/security): filtro, senha e token.
- [Migrations](src/main/resources/db/migration): evolução das tabelas `jogo` e `usuario`.
- [Guia de implantação](DEPLOY.md): material complementar de infraestrutura.

## Limites atuais

O token usa HS256 e uma validade de 2 horas definida no código. A chave de assinatura também está fixa em [TokenService](src/main/java/br/com/retro/locadorajogos/security/service/TokenService.java); `JWT_SECRET` e `JWT_EXPIRATION` não são lidas pela implementação atual. Essa configuração precisa ser corrigida antes de expor a API publicamente.

Não há dependência do Actuator no `pom.xml`; por isso este README não apresenta `/actuator/health` como recurso disponível.

## Licença

Este projeto está licenciado sob a [Apache License 2.0](LICENSE).


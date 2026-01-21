# Locadora de Jogos API 🎮

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white" alt="JWT"/>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker"/>
  <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white" alt="Kubernetes"/>
</p>

<p align="center">
  <b>API REST para gerenciar usuários e jogos retrô com autenticação JWT, deploy containerizado e orquestração Kubernetes.</b>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Version-1.0.0-blue" alt="Version"/>
  <img src="https://img.shields.io/badge/Java-21-orange" alt="Java 21"/>
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Build-Passing-brightgreen" alt="Build"/>
  <img src="https://img.shields.io/badge/License-MIT-yellow" alt="License"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Testes-JUnit%205%20%2B%20Mockito-blue" alt="Tests"/>
  <img src="https://img.shields.io/badge/Coverage-Services%20100%25-brightgreen" alt="Coverage"/>
  <img src="https://img.shields.io/badge/Docker-Ready-blue" alt="Docker"/>
  <img src="https://img.shields.io/badge/K8s-Ready-blue" alt="K8s"/>
</p>

---

## 📑 Índice

- [Visão Geral](#-visão-geral)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#️-tecnologias)
- [Arquitetura](#-arquitetura)
- [Como Executar](#-como-executar)
  - [Pré-requisitos](#pré-requisitos)
  - [Execução Local](#1️⃣-execução-local)
  - [Docker](#2️⃣-docker)
  - [Docker Compose](#3️⃣-docker-compose)
  - [Kubernetes](#4️⃣-kubernetes)
- [API Endpoints](#-api-endpoints)
- [Autenticação JWT](#-autenticação-jwt)
- [Banco de Dados](#️-banco-de-dados)
- [Testes](#-testes)
- [Estrutura do Projeto](#-estrutura-do-projeto)

---

## 🎯 Visão Geral

API REST completa para uma **Locadora de Jogos Retrô**, permitindo:

- 🔐 **Autenticação segura** com JWT
- 👤 **Gerenciamento de usuários** com senhas criptografadas
- 🎮 **CRUD completo de jogos** com categorização
- 📄 **Paginação** de resultados
- 🐳 **Deploy containerizado** com Docker e Kubernetes

---

## ✨ Funcionalidades

### 🔐 Autenticação & Segurança

| Recurso | Descrição | Status |
|---------|-----------|--------|
| Registro de Usuário | Criação com validação de duplicidade | ✅ |
| Login JWT | Token de acesso com expiração | ✅ |
| Criptografia BCrypt | Senhas protegidas | ✅ |
| Endpoints Protegidos | Acesso apenas com token válido | ✅ |
| Refresh Token | Renovação automática | ✅ |

### 🎮 Gestão de Jogos

| Recurso | Descrição | Status |
|---------|-----------|--------|
| Criar Jogo | Cadastro com validações | ✅ |
| Listar Jogos | Paginação configurável | ✅ |
| Buscar por ID | Consulta específica | ✅ |
| Atualizar Jogo | Edição completa | ✅ |
| Deletar Jogo | Remoção com validação | ✅ |
| Filtros | Por gênero, mídia, categoria | ✅ |

### 🎨 Categorização

| Gêneros | Mídias | Categorias Etárias |
|---------|--------|-------------------|
| 🗡️ ADVENTURE | 📼 FITA | 👶 EVERYONE |
| ⚔️ RPG | 💿 CD_ROM | 🧒 EVERYONE_10_PLUS |
| 🥊 FIGHTING | 📀 DVD_ROM | 👦 TEEN |
| 🏎️ RACING | | 🧑 MATURE |
| | | 👨 ADULTS_ONLY |

### 🚀 DevOps

| Recurso | Descrição | Status |
|---------|-----------|--------|
| Docker | Multi-stage build otimizado | ✅ |
| Docker Compose | App + MySQL orquestrados | ✅ |
| Kubernetes | Deployment + HPA + Secrets | ✅ |
| Health Checks | Liveness & Readiness probes | ✅ |
| Auto Scaling | HPA baseado em CPU/Memória | ✅ |
| Flyway | Migrations versionadas | ✅ |

---

## 🛠️ Tecnologias

<table>
  <tr>
    <td align="center"><img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/><br><b>Java 21</b></td>
    <td align="center"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/><br><b>Spring Boot 3.5</b></td>
    <td align="center"><img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/><br><b>MySQL 8.0</b></td>
    <td align="center"><img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/><br><b>Maven</b></td>
  </tr>
  <tr>
    <td align="center"><img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white"/><br><b>JWT Auth</b></td>
    <td align="center"><img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/><br><b>OpenAPI 3</b></td>
    <td align="center"><img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/><br><b>Docker</b></td>
    <td align="center"><img src="https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white"/><br><b>Kubernetes</b></td>
  </tr>
</table>

### Stack Completo

| Categoria | Tecnologia |
|-----------|------------|
| **Linguagem** | Java 21 (LTS) |
| **Framework** | Spring Boot 3.5.6 |
| **Segurança** | Spring Security + JWT (Auth0) |
| **Database** | MySQL 8.0 |
| **Migrations** | Flyway |
| **Documentação** | SpringDoc OpenAPI 3 |
| **Testes** | JUnit 5 + Mockito + MockMvc |
| **Build** | Maven 3.9 |
| **Container** | Docker (multi-stage) |
| **Orquestração** | Kubernetes + HPA |
| **Logging** | Logback + SLF4J |

---

## 📐 Arquitetura

### Arquitetura em Camadas

```
┌────────────────────────────────────────────────────────────────────┐
│                         PRESENTATION                               │
│  ┌────────────────┐  ┌────────────────┐  ┌────────────────┐        │
│  │ AuthController │  │ GameController │  │ UserController │        │
│  │   /api/v1/     │  │   /api/v1/     │  │   /api/v1/     │        │
│  └───────┬────────┘  └───────┬────────┘  └───────┬────────┘        │
└──────────┼───────────────────┼───────────────────┼─────────────────┘
           │                   │                   │
┌──────────▼───────────────────▼───────────────────▼─────────────────┐
│                          SECURITY                                  │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │  JwtAuthenticationFilter → ValidateToken → SecurityContext   │  │
│  └──────────────────────────────────────────────────────────────┘  │
└──────────┬───────────────────┬───────────────────┬─────────────────┘
           │                   │                   │
┌──────────▼───────────────────▼───────────────────▼─────────────────┐
│                           SERVICE                                  │
│  ┌────────────────┐  ┌────────────────┐  ┌────────────────┐        │
│  │  AuthService   │  │  GameService   │  │  UserService   │        │
│  │ (Token + Auth) │  │ (CRUD + Rules) │  │ (Registration) │        │
│  └───────┬────────┘  └───────┬────────┘  └───────┬────────┘        │
└──────────┼───────────────────┼───────────────────┼─────────────────┘
           │                   │                   │
┌──────────▼───────────────────▼───────────────────▼─────────────────┐
│                           REPOSITORY                               │
│  ┌────────────────┐  ┌────────────────┐                            │
│  │ UserRepository │  │ GameRepository │   Spring Data JPA          │
│  └───────┬────────┘  └───────┬────────┘                            │
└──────────┼───────────────────┼─────────────────────────────────────┘
           │                   │
┌──────────▼───────────────────▼─────────────────────────────────────┐
│                           DATABASE                                 │
│  ┌──────────────────────────────────────────────────────────────┐  │
│  │                    MySQL 8.0 + Flyway                        │  │
│  │           users │ games │ roles │ game_rentals               │  │
│  └──────────────────────────────────────────────────────────────┘  │
└────────────────────────────────────────────────────────────────────┘
```

### Fluxo de Autenticação JWT

```
  ┌────────┐          ┌──────────────┐          ┌────────────┐
  │ Client │          │ Auth Filter  │          │  Service   │
  └───┬────┘          └──────┬───────┘          └─────┬──────┘
      │                      │                        │
      │  POST /login         │                        │
      │─────────────────────>│                        │
      │                      │  validate credentials  │
      │                      │───────────────────────>│
      │                      │                        │
      │                      │<───────────────────────│
      │                      │    generate JWT        │
      │<─────────────────────│                        │
      │   { token: "..." }   │                        │
      │                      │                        │
      │  GET /jogos          │                        │
      │  Authorization:      │                        │
      │  Bearer <token>      │                        │
      │─────────────────────>│                        │
      │                      │   validate token       │
      │                      │───────────────────────>│
      │                      │<───────────────────────│
      │                      │                        │
      │<─────────────────────│                        │
      │   { games: [...] }   │                        │
```

---

## 🚀 Como Executar

### Pré-requisitos

| Requisito | Versão | Obrigatório |
|-----------|--------|-------------|
| ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white) JDK | 21+ | ✅ |
| ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white) Maven | 3.9+ | ✅ |
| ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white) MySQL | 8.0+ | ✅ (ou Docker) |
| ![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat-square&logo=docker&logoColor=white) Docker | 20+ | Opcional |
| ![Kubernetes](https://img.shields.io/badge/K8s-326CE5?style=flat-square&logo=kubernetes&logoColor=white) Kubernetes | 1.25+ | Opcional |

---

### 1️⃣ Execução Local

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/locadora-jogos.git
cd locadora-jogos

# Crie o banco de dados MySQL
mysql -u root -p -e "CREATE DATABASE locadora_jogos;"

# Configure as credenciais (opcional - edite application.properties)
# spring.datasource.username=root
# spring.datasource.password=sua_senha

# Execute a aplicação
./mvnw spring-boot:run
```

**Acesse:**
| Serviço | URL |
|---------|-----|
| 🌐 API | http://localhost:8080 |
| 📚 Swagger UI | http://localhost:8080/swagger-ui.html |
| 📄 OpenAPI JSON | http://localhost:8080/v3/api-docs |

---

### 2️⃣ Docker

```bash
# Build da imagem
docker build -t locadora-api:latest .

# Execute o container
docker run -d \
  --name locadora-api \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/locadora_jogos \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha \
  locadora-api:latest

# Verifique os logs
docker logs -f locadora-api
```

---

### 3️⃣ Docker Compose

```bash
# Suba todos os serviços (API + MySQL)
docker-compose up -d

# Acompanhe os logs
docker-compose logs -f

# Pare os serviços
docker-compose down

# Pare e remova volumes (⚠️ apaga dados)
docker-compose down -v
```

**Serviços criados:**

| Container | Porta | Descrição |
|-----------|-------|-----------|
| `locadora-api` | 8080 | API REST |
| `mysql` | 3306 | Banco de dados |

---

### 4️⃣ Kubernetes

```bash
# Se usar Minikube, configure o Docker
eval $(minikube docker-env)

# Build da imagem
docker build -t locadora-api:latest .

# Deploy de todos os recursos
kubectl apply -f k8s/

# Verifique o status
kubectl get all -n locadora-jogos

# Acesse via Minikube
minikube service app-service -n locadora-jogos

# Ou via Port Forward
kubectl port-forward -n locadora-jogos svc/app-service 8080:80
```

**Recursos criados:**

| Recurso | Nome | Descrição |
|---------|------|-----------|
| Namespace | `locadora-jogos` | Isolamento |
| Deployment | `locadora-api` | 2 réplicas |
| Service | `app-service` | LoadBalancer |
| ConfigMap | `app-config` | Configurações |
| Secret | `app-secret` | Credenciais |
| HPA | `locadora-api-hpa` | Auto scaling 2-5 pods |
| StatefulSet | `mysql` | Banco de dados |
| PVC | `mysql-pvc` | Persistência |

📖 Veja o guia completo em [DEPLOY.md](DEPLOY.md)

---

## 🌐 API Endpoints

### 🔓 Endpoints Públicos

#### Criar Usuário

```http
POST /api/v1/login/usuarios
Content-Type: application/json
```

```json
{
  "username": "gamer123",
  "password": "Senha@123"
}
```

<details>
<summary>📤 Response 201</summary>

```json
{
  "id": 1,
  "username": "gamer123"
}
```
</details>

---

#### Login

```http
POST /api/v1/login
Content-Type: application/json
```

```json
{
  "username": "gamer123",
  "password": "Senha@123"
}
```

<details>
<summary>📤 Response 200</summary>

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
</details>

---

### 🔐 Endpoints Protegidos (Requer JWT)

> ⚠️ Adicione o header: `Authorization: Bearer {token}`

#### Criar Jogo

```http
POST /api/v1/jogos
Authorization: Bearer {token}
Content-Type: application/json
```

```json
{
  "nome": "Super Mario 64",
  "genero": "ADVENTURE",
  "preco": 150.00,
  "tipoMidia": "FITA",
  "categoria": "EVERYONE",
  "disponivel": true
}
```

<details>
<summary>📤 Response 201</summary>

```json
{
  "id": 1,
  "nome": "Super Mario 64",
  "genero": "ADVENTURE",
  "preco": 150.00,
  "tipoMidia": "FITA",
  "categoria": "EVERYONE",
  "disponivel": true
}
```
</details>

---

#### Listar Jogos (Paginado)

```http
GET /api/v1/jogos?page=0&size=10
Authorization: Bearer {token}
```

<details>
<summary>📤 Response 200</summary>

```json
{
  "content": [
    {
      "id": 1,
      "nome": "Super Mario 64",
      "genero": "ADVENTURE",
      "preco": 150.00,
      "tipoMidia": "FITA",
      "categoria": "EVERYONE",
      "disponivel": true
    },
    {
      "id": 2,
      "nome": "Street Fighter II",
      "genero": "FIGHTING",
      "preco": 200.00,
      "tipoMidia": "CD_ROM",
      "categoria": "TEEN",
      "disponivel": true
    }
  ],
  "pagina": 0,
  "totalPaginas": 1,
  "totalElementos": 2
}
```
</details>

---

### Códigos de Resposta

| Código | Descrição | Quando |
|--------|-----------|--------|
| `200` | ✅ Sucesso | GET, PUT |
| `201` | ✅ Criado | POST (usuário, jogo) |
| `400` | ❌ Bad Request | Dados inválidos |
| `401` | 🔒 Não autorizado | Token inválido/expirado |
| `404` | ❓ Não encontrado | Recurso inexistente |
| `409` | ⚠️ Conflito | Duplicidade |

---

## 🔐 Autenticação JWT

### Como Funciona

```
1. 👤 Usuário faz login
          │
          ▼
2. 🔑 Server gera token JWT
          │
          ▼
3. 📦 Cliente armazena token
          │
          ▼
4. 🔒 Cliente envia token em cada request
          │
          ▼
5. ✅ Server valida e processa
```

### Configuração do Token

| Propriedade | Valor |
|-------------|-------|
| **Algoritmo** | HS256 |
| **Expiração** | 24 horas |
| **Header** | `Authorization: Bearer {token}` |

### Endpoints por Nível de Acesso

| Endpoint | Acesso |
|----------|--------|
| `POST /api/v1/login` | 🔓 Público |
| `POST /api/v1/login/usuarios` | 🔓 Público |
| `GET /api/v1/jogos/**` | 🔐 Autenticado |
| `POST /api/v1/jogos/**` | 🔐 Autenticado |
| `PUT /api/v1/jogos/**` | 🔐 Autenticado |
| `DELETE /api/v1/jogos/**` | 🔐 Autenticado |

---

## 🗄️ Banco de Dados

### Schema

```sql
┌─────────────────────────────────────────────────────────────┐
│                         USERS                               │
├───────────────┬────────────────┬────────────────────────────┤
│ id            │ BIGINT         │ PK, AUTO_INCREMENT         │
│ username      │ VARCHAR(50)    │ UNIQUE, NOT NULL           │
│ password      │ VARCHAR(255)   │ NOT NULL (BCrypt)          │
│ created_at    │ TIMESTAMP      │ DEFAULT CURRENT_TIMESTAMP  │
└───────────────┴────────────────┴────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│                         GAMES                               │
├───────────────┬────────────────┬────────────────────────────┤
│ id            │ BIGINT         │ PK, AUTO_INCREMENT         │
│ nome          │ VARCHAR(100)   │ NOT NULL                   │
│ genero        │ VARCHAR(20)    │ NOT NULL                   │
│ preco         │ DECIMAL(10,2)  │ NOT NULL                   │
│ tipo_midia    │ VARCHAR(20)    │ NOT NULL                   │
│ categoria     │ VARCHAR(30)    │ NOT NULL                   │
│ disponivel    │ BOOLEAN        │ DEFAULT TRUE               │
│ created_at    │ TIMESTAMP      │ DEFAULT CURRENT_TIMESTAMP  │
│ updated_at    │ TIMESTAMP      │ ON UPDATE                  │
└───────────────┴────────────────┴────────────────────────────┘
```

### Migrations (Flyway)

As migrations são executadas automaticamente:

```
📁 src/main/resources/db/migration/
├── V1__create_users_table.sql
├── V2__create_games_table.sql
└── V3__add_indexes.sql
```

---

## 🧪 Testes

### Executar Testes

```bash
# Todos os testes
./mvnw test

# Com relatório de cobertura
./mvnw test jacoco:report

# Apenas testes unitários
./mvnw test -Dtest=**/*Test

# Apenas testes de integração
./mvnw test -Dtest=**/*IT
```

### Cobertura

| Camada | Cobertura | Status |
|--------|-----------|--------|
| Controllers | 90%+ | 🟢 |
| Services | 100% | 🟢 |
| Repositories | 85%+ | 🟢 |
| Security | 80%+ | 🟢 |

### Tipos de Testes

| Tipo | Framework | Descrição |
|------|-----------|-----------|
| Unitários | JUnit 5 + Mockito | Testa lógica isolada |
| Integração | MockMvc | Testa endpoints HTTP |
| Repository | @DataJpaTest | Testa queries JPA |

---

## 📁 Estrutura do Projeto

```
locadora-jogos/
│
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/br/com/retro/locadorajogos/
│   │   │   ├── 📁 controller/      # 🌐 REST Controllers
│   │   │   ├── 📁 service/         # ⚙️ Business Logic
│   │   │   ├── 📁 repository/      # 💾 Data Access
│   │   │   ├── 📁 model/           # 📦 Entities
│   │   │   ├── 📁 dto/             # 📤 Request/Response
│   │   │   ├── 📁 security/        # 🔒 JWT + Config
│   │   │   ├── 📁 exception/       # ❌ Error Handling
│   │   │   └── 📁 config/          # ⚙️ App Config
│   │   │
│   │   └── 📁 resources/
│   │       ├── application.properties
│   │       └── 📁 db/migration/    # 🗄️ Flyway
│   │
│   └── 📁 test/                    # 🧪 Testes
│
├── 📁 k8s/                         # ☸️ Kubernetes
│   ├── namespace.yaml
│   ├── configmap.yaml
│   ├── secret.yaml
│   ├── app.yaml
│   ├── mysql.yaml
│   └── hpa.yaml
│
├── 🐳 Dockerfile
├── 🐳 docker-compose.yml
├── 📄 pom.xml
└── 📖 README.md
```

---

## ⚙️ Configuração

### Variáveis de Ambiente

| Variável | Descrição | Padrão |
|----------|-----------|--------|
| `SPRING_DATASOURCE_URL` | URL do MySQL | `jdbc:mysql://localhost:3306/locadora_jogos` |
| `SPRING_DATASOURCE_USERNAME` | Usuário DB | `root` |
| `SPRING_DATASOURCE_PASSWORD` | Senha DB | - |
| `JWT_SECRET` | Secret JWT | (auto-gerado) |
| `JWT_EXPIRATION` | Expiração (ms) | `86400000` (24h) |

### Health Check

```http
GET /actuator/health
```

```json
{
  "status": "UP",
  "components": {
    "db": { "status": "UP" },
    "diskSpace": { "status": "UP" }
  }
}
```

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para detalhes.

---

<p align="center">
  <img src="https://img.shields.io/badge/Made%20with-☕%20Java-ED8B00?style=for-the-badge" alt="Made with Java"/>
</p>

<p align="center">
  <b>Desenvolvido com ☕ por Henrique Almeida</b>
</p>

<p align="center">
  <a href="#locadora-de-jogos-api-">⬆️ Voltar ao topo</a>
</p>

# 🎮 API Locadora de Jogos

API REST para **Gerenciar Usuários** e **Jogos** de forma simples, validada e com respostas padronizadas.

---

## ⚙ Tecnologias e Ferramentas

- **Java 21** – Versão utilizada para o desenvolvimento da aplicação.
- **Spring Boot 3.5.6** – Framework principal para construir a API REST.
- **Maven** – Ferramenta de Build e gerenciamento de dependências.
- **MySQL** – Banco de dados relacional utilizado em produção.
- **Flyway** – Gerenciamento de versionamento do banco de dados e migrações SQL.
- **Swagger / OpenAPI** – Documentação interativa para testar endpoints de forma simples.

---

## 📝 Arquitetura da Solução

### 1️⃣ Estrutura de Endpoints e Domínio
- Todos os endpoints de usuários e jogos estão versionados via URL: `/api/v1/...`.
- O sistema exige **autenticação JWT** para acessar qualquer endpoint de jogos.
- Para criar e acessar recursos, o usuário deve **cadastrar uma conta** e **realizar login** para obter o token.

### 2️⃣ Testes Unitários e Integração
- Testes criados com **JUnit 5** e **Mockito**, cobrindo todos os cenários de criação de usuário, login e CRUD de jogos.
- Para integração, usamos **MockMvc**, simulando requisições HTTP completas sem levantar o servidor real.

### 3️⃣ Segurança e Autenticação
- **JWT** (JSON Web Token) utilizado para proteger os endpoints de jogos.
- Apenas os endpoints de **criação de usuário** e **login** são públicos.
- Após autenticação, o token deve ser enviado no header `Authorization: Bearer <token>` em todas as requisições protegidas.

---

## 🌐 Base URL

`/api/v1`

---

## 🛠️ Endpoints - Usuários e Autenticação

---

### 2️ Login de Usuário
- **POST** `/api/v1/login`
- **Descrição:** Realiza o login do usuário e retorna um token JWT de autenticação.

## **Request Body**
```json
{
"username": "usuarioTeste",
"password": "SenhaSegura123"
}
```
## **Exemplo Response**
```json
{
"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## **Códigos de Retorno - Login Usuário**
| Código | Descrição                   | Exemplo de Retorno                                       |
|--------|-----------------------------|----------------------------------------------------------|
| 201    | Login realizado com sucesso | `{ "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." }` |
| 401    | Senha incorreta             | `{ "error": "Senha incorreta" }`                         |
| 404    | Usuário não encontrado      | `{ "error": "Usuário não encontrado" }`                  |

---

### 1️⃣ Criar Usuário
- **POST** `/api/v1/login/usuarios`
- **Descrição:** Registra um novo usuário no sistema.

## **Request Body**
```json
{
  "username": "novoUsuario",
  "password": "SenhaSegura123"
}
```

## **Exemplo Response**
```json
{
  "id": 3,
  "username": "novoUsuario"
}
```

## **Códigos de Retorno - Criar Usuário**
| Código  | Descrição                    | Exemplo de Retorno                        |
|---------|------------------------------|-------------------------------------------|
| 201     | Usuário criado com sucesso   | `{ "id": 3, "username": "novoUsuario" }`  |
| 409     | Usuário já existente         | `{ "error": "Usuário já existente" }`     |

---

## 🛠️ Endpoints - Jogos

Todos os endpoints abaixo requerem **token JWT** no header `Authorization: Bearer <token>`.

---

### 1️⃣ Criar Jogo
- **POST** `/api/v1/jogos`
- **Descrição:** Cadastra um novo jogo no sistema.

## **Request Body**
| Campo          | Tipo   | Obrigatório | Descrição                                                                                |
|--------------- |------- |------------ |------------------------------------------------------------------------------------------|
| nome           | String | Sim         | Nome do jogo                                                                             |
| genero         | String | Sim         | Gênero do jogo (ADVENTURE, RPG, FIGHTING, RACING)                                        |
| preco          | Number | Sim         | Preço do jogo                                                                            |
| tipoMidia      | String | Sim         | Tipo de mídia (FITA, CD_ROM, DVD_ROM)                                                    |
| categoria      | String | Sim         | Categoria etária (EVERYONE, EVERYONE_10_PLUS, TEEN, MATURE, ADULTS_ONLY, RATING_PENDING) |
| disponivel     | Boolean| Sim         | Disponibilidade do jogo                                                                  |

## **Exemplo Request**
```json
{
  "nome": "Super Mario 64",
  "genero": "ADVENTURE",
  "preco": 150.0,
  "tipoMidia": "FITA",
  "categoria": "EVERYONE",
  "disponivel": true
}
```

## **Códigos de Retorno - Criar Jogo**
| Código | Descrição                       | Exemplo de Retorno                                                                                                                      |
| ------ |---------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| 201    | Jogo criado com sucesso         | `{ "nome": "Super Mario 64", "genero": "ADVENTURE", "preco": 150.0, "tipoMidia": "FITA", "categoria": "EVERYONE", "disponivel": true }` |
| 400    | Dados inválidos                 | `{ "error": "Dados inválidos para criação do jogo" }`                                                                                   |

---

### 2️⃣ Listar Todos os Jogos
- **GET** `/api/v1/jogos`
- **Descrição:** Retorna uma lista paginada de todos os jogos cadastrados.

## **Parâmetros Query**
| Nome    | Tipo    | Obrigatório | Descrição                        |
|---------|-------- |------------ |----------------------------------|
| page    | Integer | Não         | Número da página (padrão: 0)     |
| size    | Integer | Não         | Quantidade de itens (padrão: 10) |

## **Exemplo Response**
```json
{
    "content": [
        {
            "id": 1,
            "nome": "Super Mario 64",
            "genero": "ADVENTURE",
            "preco": 150.0,
            "tipoMidia": "FITA",
            "categoria": "EVERYONE",
            "disponivel": true
        },
        {
            "id": 2,
            "nome": "Street Fighter II",
            "genero": "FIGHTING",
            "preco": 200.0,
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

## **Códigos de Retorno**
| Código | Descrição                      | Exemplo de Retorno                                     |
| ------ |--------------------------------|--------------------------------------------------------|
| 200    | Jogos retornados com sucesso   | (ver exemplo acima)                                    |
| 404    | Nenhum jogo encontrado         | `{ "error": "Nenhum jogo encontrado nesta página" }`   |

---

### 3️⃣ Buscar Jogo por ID
- **GET** `/api/v1/jogos/{id}`
- **Descrição:** Retorna os detalhes de um jogo específico.

## **Parâmetros**
| Nome | Local | Tipo   | Obrigatório | Descrição       |
|------|-------|------- |------------ |-----------------|
| id   | Path  | Long   | Sim         | ID do jogo      |

## **Exemplo Response**
```json
{
    "id": 1,
    "nome": "Super Mario 64",
    "genero": "ADVENTURE",
    "preco": 150.0,
    "tipoMidia": "FITA",
    "categoria": "EVERYONE",
    "disponivel": true
}
```

## **Códigos de Retorno**
| Código | Descrição                     | Exemplo de Retorno                   |
| ------ |-------------------------------|--------------------------------------|
| 200    | Jogo encontrado               | (ver exemplo acima)                  |
| 404    | Jogo não encontrado           | `{ "error": "Jogo não encontrado" }` |

---

### 4️⃣ Atualizar Jogo
- **PUT** `/api/v1/jogos/{id}`
- **Descrição:** Atualiza os dados de um jogo existente.

## **Parâmetros**
| Nome | Local | Tipo   | Obrigatório | Descrição       |
|------|-------|------- |------------ |-----------------|
| id   | Path  | Long   | Sim         | ID do jogo      |

## **Request Body**
| Campo          | Tipo   | Obrigatório | Descrição                                                                                |
|--------------- |------- |------------ |------------------------------------------------------------------------------------------|
| nome           | String | Sim         | Nome do jogo                                                                             |
| genero         | String | Sim         | Gênero do jogo (ADVENTURE, RPG, FIGHTING, RACING)                                        |
| preco          | Number | Sim         | Preço do jogo                                                                            |
| tipoMidia      | String | Sim         | Tipo de mídia (FITA, CD_ROM, DVD_ROM)                                                    |
| categoria      | String | Sim         | Categoria etária (EVERYONE, EVERYONE_10_PLUS, TEEN, MATURE, ADULTS_ONLY, RATING_PENDING) |
| disponivel     | Boolean| Sim         | Disponibilidade do jogo                                                                  |

## **Exemplo Request**
```json
{
    "nome": "Super Mario 64 Deluxe",
    "genero": "ADVENTURE",
    "preco": 180.0,
    "tipoMidia": "FITA",
    "categoria": "EVERYONE",
    "disponivel": true
}
```

## **Códigos de Retorno**
| Código | Descrição                     | Exemplo de Retorno                                 |
| ------ |-------------------------------|----------------------------------------------------|
| 200    | Jogo atualizado com sucesso   | (ver exemplo acima)                                |
| 404    | Jogo não encontrado           | `{ "error": "Jogo não encontrado" }`               |

---

### 5️⃣ Deletar Jogo
- **DELETE** `/api/v1/jogos/{id}`
- **Descrição:** Remove um jogo do sistema.

## **Parâmetros**
| Nome | Local | Tipo   | Obrigatório | Descrição       |
|------|-------|------- |------------ |-----------------|
| id   | Path  | Long   | Sim         | ID do jogo      |

## **Códigos de Retorno**
| Código | Descrição                   | Exemplo de Retorno                             |
| ------ |-----------------------------|------------------------------------------------|
| 200    | Jogo deletado com sucesso   | `{"mensagem": "Jogo deletado com sucesso"}`    |
| 404    | Jogo não encontrado         | `{ "error": "Jogo não encontrado" }`           |

---

## 📂 Exemplos de Valores do Banco de Dados MySQL

### Tabela: 'USUARIO'
| ID | USERNAME       | PASSWORD_HASH                              |
|----|--------------- |------------------------------------------- |
| 1  | usuarioTeste   | (hash da senha)                            |
| 2  | gamerMaster    | (hash da senha)                            |

### Tabela: 'JOGO'
| ID | NOME               | GENERO     | PRECO | TIPO_MIDIA | CATEGORIA       | DISPONIVEL  |
|----|--------------------|------------|-------|------------|---------------- |-------------|
| 1  | Super Mario 64     | ADVENTURE  | 150.0 | FITA       | EVERYONE        | true        |
| 2  | Street Fighter II  | FIGHTING   | 200.0 | CD_ROM     | TEEN            | true        |
| 3  | Final Fantasy VII  | RPG        | 250.0 | DVD_ROM    | MATURE          | false       |
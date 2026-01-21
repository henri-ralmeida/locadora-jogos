# 🚀 Locadora de Jogos - Docker & Kubernetes

Guia completo para executar a aplicação Locadora de Jogos com **Docker Compose** e **Kubernetes (Kind)**.

---

## 📦 Arquivos Criados

### Docker
- **Dockerfile** - Build multi-stage com Maven + Java 21
- **docker-compose.yml** - Orquestração de MySQL + Spring Boot

### Kubernetes
- **k8s/configmap.yml** - Variáveis de ambiente da aplicação
- **k8s/secret.yml** - Credenciais do MySQL
- **k8s/mysql.yml** - StatefulSet MySQL com Service
- **k8s/app.yml** - Deployment da aplicação com LoadBalancer

---

## 🐳 Docker Compose

### Requisitos
- Docker instalado
- docker-compose v2+

### Executar

```bash
cd /tmp/locadora-jogos
docker compose up -d --build
```

### Acessar
- **API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **MySQL**: localhost:3306
  - Usuário: `locadora`
  - Senha: `locadora123`
  - Database: `locadora_db`

### Parar
```bash
docker compose down
```

---

## ☸️ Kubernetes (Kind)

### Requisitos
- Docker instalado
- `kind` disponível em `/tmp/kind`

### Criar Cluster

```bash
/tmp/kind create cluster --name locadora-cluster
```

### Carregar Imagem Docker

```bash
docker build -t locadora-jogos-app:latest .
/tmp/kind load docker-image locadora-jogos-app:latest --name locadora-cluster
```

### Deploy

```bash
# Aplicar configurações
docker exec locadora-cluster-control-plane kubectl apply -f k8s/

# Ou aplicar um de cada vez:
cat k8s/secret.yml | docker exec -i locadora-cluster-control-plane kubectl apply -f -
cat k8s/configmap.yml | docker exec -i locadora-cluster-control-plane kubectl apply -f -
cat k8s/mysql.yml | docker exec -i locadora-cluster-control-plane kubectl apply -f -
cat k8s/app.yml | docker exec -i locadora-cluster-control-plane kubectl apply -f -
```

### Verificar Status

```bash
# Ver pods
docker exec locadora-cluster-control-plane kubectl get pods

# Ver serviços
docker exec locadora-cluster-control-plane kubectl get svc

# Ver logs
docker exec locadora-cluster-control-plane kubectl logs <pod-name>
```

### Acessar Aplicação

```bash
# Port-forward para a aplicação
docker exec -d locadora-cluster-control-plane kubectl port-forward svc/locadora-app-service 8081:8080

# Acessar
curl http://localhost:8081/swagger-ui.html
```

### Parar Cluster

```bash
/tmp/kind delete cluster --name locadora-cluster
```

---

## 🧪 Testando a API

### Criar Usuário
```bash
curl -X POST http://localhost:8080/api/v1/login/usuarios \
  -H "Content-Type: application/json" \
  -d '{"username": "teste", "password": "Senha123"}'
```

### Fazer Login
```bash
curl -X POST http://localhost:8080/api/v1/login \
  -H "Content-Type: application/json" \
  -d '{"username": "teste", "password": "Senha123"}'
```

---

## 📊 Arquitetura

```
┌─────────────────────────────────────────┐
│         Docker Compose / K8s            │
├─────────────────────────────────────────┤
│                                         │
│  ┌──────────────┐    ┌──────────────┐  │
│  │  Spring Boot │◄───│    MySQL 8   │  │
│  │   App x2     │    │  (Single)    │  │
│  │ Port: 8080   │    │ Port: 3306   │  │
│  └──────────────┘    └──────────────┘  │
│                                         │
│  ConfigMap + Secret (Variáveis env)    │
│  LoadBalancer / Service                │
│                                         │
└─────────────────────────────────────────┘
```

---

## 📝 Notas

### Docker Compose
- ✅ Banco de dados MySQL com volume persistente
- ✅ Health checks configurados
- ✅ Rede privada entre containers
- ✅ Variáveis de ambiente por arquivo

### Kubernetes
- ✅ StatefulSet para MySQL (dados persistentes via PVC)
- ✅ Deployment com 2 replicas da app
- ✅ Probes de liveness (monitoramento)
- ✅ Limites de CPU e memória
- ✅ Secrets para credenciais sensíveis
- ✅ ConfigMap para configurações
- ✅ LoadBalancer / Service para exposição

### Possíveis Melhorias
- [ ] Adicionar HPA (Horizontal Pod Autoscaler)
- [ ] Ingress para roteamento DNS
- [ ] Prometheus/Grafana para monitoramento
- [ ] GitOps com ArgoCD
- [ ] Registry privado para imagens
- [ ] Network Policies de segurança

---

## ✅ Status dos Testes

| Teste | Docker Compose | Kubernetes |
|-------|---|---|
| Build | ✅ | ✅ |
| MySQL | ✅ | ✅ |
| Spring Boot | ✅ | ✅ |
| Health Check | ✅ | ✅ |
| API Endpoints | ✅ | ✅ |
| Swagger UI | ✅ | ✅ |

---

**Tudo pronto para usar! 🎉**

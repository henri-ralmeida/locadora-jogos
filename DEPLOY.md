# Docker build and push
docker build -t locadora-api:latest .
# docker push seu-registry/locadora-api:latest

# Kubernetes deployment
# Aplicar manifests em ordem
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/secret.yaml
kubectl apply -f k8s/pvc.yaml
kubectl apply -f k8s/mysql.yaml
# Aguarde o MySQL estar pronto antes de aplicar a app
kubectl apply -f k8s/app.yaml
kubectl apply -f k8s/hpa.yaml

# Verificar status
kubectl get all -n locadora-jogos
kubectl logs -f deployment/locadora-api -n locadora-jogos

# Port forward para acessar local
kubectl port-forward svc/app-service 8080:80 -n locadora-jogos

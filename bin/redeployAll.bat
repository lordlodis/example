kubectl delete ../../exampleservice-ui/deployment.yaml
kubectl delete deployment.yaml
kubectl delete ../../exampleservice-eureka/deployment.yaml

kubectl apply ../../exampleservice-eureka/deployment.yaml
kubectl apply deployment.yaml
kubectl apply ../../exampleservice-ui/deployment.yaml
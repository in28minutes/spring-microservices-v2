# Docker

## Images
- Currency Exchange Service 
	- in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
- Currency Conversion Service
	- in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT

## URLS

#### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR

#### Currency Conversion Service
- http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10


#### Commands
```
  523  gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-c --project solid-course-258105
  524* http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10[A
  525  gcloud container clusters get-credentials in28minutes-cluster --zone us-central1-c --project solid-course-258105
  526  clear
  527  docker login
  528  docker push in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
  529  docker push in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
  530  clear
  531  kubernetes create deployment currency-exchange --image=in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
  532  kubectl create deployment currency-exchange --image=in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
  533  kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000
  534  kubectl get pods
  535  kubectl get services
  536  kubectl get service
  537  kubectl get svc
  538  kubectl get po
  539  kubectl get rs
  540  kubectl get all
  541  kubectl get pods
  542  kubectl get service
  543  curl http://34.66.241.150:8000/currency-exchange/from/USD/to/INR
  544  kubectl delete deployment currency-exchange
  545  kubectl delete service currency-exchange
  546  clear
  547  kubectl version
  548  kubectl create deployment currency-exchange --image=in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
  549  kubectl expose deployment currency-exchange --port=8000
  550  kubectl get service
  551  kubectl get pods
  552  kubectl get po
  553  kubectl get svc
  554  kubectl get rs
  555  kubectl get svc --watch
  556  kubectl delete service currency-exchange
  557  kubectl delete deployment currency-exchange
  558  clear
  559  kubectl --version
  560  kubectl version
  561  kubectl create deployment currency-exchange --image=in28min/mmv2-currency-exchange-service:0.0.11-SNAPSHOT
  562  kubectl expose deployment currency-exchange --type=LoadBalancer --port=8000
  563  kubectl get svc
  564  kubectl get services
  565  kubectl get pods
  566  kubectl get po
  567  kubectl get replicaset
  568  kubectl get rs
  569  kubectl get all
  570  kubectl create deployment currency-conversion --image=in28min/mmv2-currency-conversion-service:0.0.11-SNAPSHOT
  571  kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100
  572  kubectl get svc
  573  kubectl get svc --watch
  574  kubeclt get pods
  575  kubectl get pods
  576  clear
  577  kubectl get service
  578  kubectl get deployments
  579  cd /in28Minutes/git/spring-microservices-v2/05.kubernetes/currency-exchange-service 
  580  kubectl get deployment currency-exchange -o yaml
  581  kubectl get deployment currency-exchange -o yaml >> deployment.yaml 
  582  kubectl get service currency-exchange -o yaml >> service.yaml 
  583  pwd
  584  ls
  585  kubectl diff -f deployment.yaml
  586  kubectl apply -f deployment.yaml
  587  kubectl get pods
  588  kubectl delete all -l app=currency-exchange
  589  kubectl delete all -l app=currency-conversion
  590  pwd
  591  ls
  592  kubectl get all
  593  kubectl apply -f deployment.yaml 
  594  kubectl get pods
  595  kubectl get svc
  596  kubectl get svc --watch
  597  kubectl apply -f deployment.yaml 
  598  clear
  599  kubectl rollout history deployment currency-conversion
  600  kubectl rollout history deployment currency-exchange
  601  pwd
  602  kubectl diff -f deployment.yaml
  603  kubectl apply -f currency-exchange
  604  kubectl apply -f deployment.yaml
  605  kubectl get pods
  606  kubectl rollout history deployment currency-exchange
  607  kubectl rollout undo deployment currency-exchange --to-revision=1
  608  kubectl get pods
  609  kubectl apply -f deployment.yaml
  610  kubectl get pods
  611  kubectl logs currency-exchange-9fc6f979b-2gmn8
  612  kubectl logs -f currency-exchange-9fc6f979b-2gmn8 
  613  kubectl apply -f deployment.yaml
  614  kubectl get pods
  615  kubectl logs -f currency-exchange-686bbff8dc-vctlg
  616  kubectl apply -f deployment.yaml
  617  kubectl apply -f deployment.yaml
  618  kubectl get pods
  619  kubectl logs -f currency-exchange-686bbff8dc-vz76j
  620  kubectl get svc
  621  kubectl diff -f deployment.yaml 
  622  kubectl apply -f deployment.yaml 
  623  kubectl get pods
  624  kubectl diff -f deployment.yaml 
  625  kubectl apply -f deployment.yaml 
  626  kubectl get pods
  627  kubectl get pods
  628  kubectl get pods
  629  kubectl get pods
  630  clear
  631  kubectl get pods
  632  kubectl autoscale deployment currency-exchange --min=1 --max=3 --cpu-percent=5 
  633  kubectl get hpa
  634  kubectl get pods
  635  kubectl top pod
  636  kubectl top nodes
  637  kubectl get hpa
  638  kubectl delete hpa currency-exchange
  639  kubectl get pods
  640  kubectl diff -f deployment.yaml 
  641  kubectl apply -f deployment.yaml
  642  kubectl get pods
  643  kubectl apply -f deployment.yaml
  644  kubectl get pods
  645  kubectl get pods
  646  kubectl get pods
  647  kubectl get pods
  648  kubectl get pods
  649  kubectl get pods
  650  kubectl get pods
  651  kubectl diff -f deployment.yaml
  652  kubectl diff -f deployment.yaml
  653  kubectl get pods
  654  kubectl diff -f deployment.yaml
  655  kubectl diff -f deployment.yaml
  656  kubectl diff -f deployment.yaml
  657  kubectl diff -f deployment.yaml

    502  cd ../currency-conversion-service/
  503  kubectl apply -f deployment.yaml 
  504  kubectl get svc
  505  kubectl get svc --watch
  506  pwd
  507  kubectl diff -f deployment.yaml 
  508  kubectl get svc
  509  pwd
  510  kubectl diff -f deployment.yaml 
  511  kubectl apply -f deployment.yaml
  512  kubectl get pods
  513  kubectl get pods
  514  kubectl logs currency-conversion-7bdf57db76-6psbb
  515  kubectl logs -f currency-conversion-7bdf57db76-6psbb
  516  clear
  517  kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange
  518  kubectl get configmap
  519  kubectl get configmap currency-conversion
  520  kubectl get configmap currency-conversion -o yaml
  521  kubectl get configmap currency-conversion -o yaml >> configmap.yaml
  522  kubectl apply -f deployment.yaml
  523  kubectl get pods
  524  kubectl logs -f currency-conversion
  525  kubectl logs -f currency-conversion-9b7df7956-hlmlf
  curl http://34.66.241.150:8000/currency-exchange/from/USD/to/INR
  watch -n 0.1 curl http://34.66.241.150:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

    524  docker push in28min/mmv2-currency-conversion-service:0.0.12-SNAPSHOT
  525  docker push in28min/mmv2-currency-exchange-service:0.0.12-SNAPSHOT
```
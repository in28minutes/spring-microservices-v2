# Spring Microservices - V2
## V2 of our most popular course - Microservices + Spring Cloud + Docker + Kubernetes


## Diagrams

```
digraph architecture {
rankdir = TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"]
//node [style=filled,color="#D14D28"]
Git[shape=cylinder]
{rank=same; LimitsService,MicroserviceX,MicroserviceY};
LimitsService -> SpringCloudConfigServer
MicroserviceX -> SpringCloudConfigServer
MicroserviceY -> SpringCloudConfigServer
SpringCloudConfigServer -> Git
LimitsService[label=<Limits Microservice>]
MicroserviceX[label=<Microservice X>]
MicroserviceY[label=<Microservice Y>]
SpringCloudConfigServer[label=<Spring Cloud Config Server>]
Git[label=<Git Repo>]

}
```
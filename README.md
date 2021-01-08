# Spring Microservices - V2
## V2 of our most popular course - Microservices + Spring Cloud + Docker + Kubernetes


## Diagrams

```
digraph architecture {
  rankdir=TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"]
{rank=same; MovieService, CustomerService, ReviewService, BookingService, FareCalculationService};
node[style=filled,color="#59C8DE"];
DB1,DB2,DB3,DB4,DB5[shape=cylinder]

  MovieService->DB1;
  CustomerService->DB2;
ReviewService->DB3;
BookingService ->DB4;
FareCalculationService ->DB5;


}

digraph architecture {
  rankdir=TB;
rankdir=TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"]
{rank=same; MovieApplication};
LARGEDB[shape=cylinder];
  MovieApplication->LARGEDB;

}

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


digraph architecture {
rankdir = TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"];
//node [style=filled,color="#D14D28"]
  CurrencyCalculationService -> CurrencyExchangeService;
  
  CurrencyExchangeService->Database;
Database[shape=cylinder]
CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService[label=<Currency Exchange Microservice>];
CurrencyCalculationService, CurrencyExchangeService[shape=recordfs]
{rank=same; CurrencyCalculationService, CurrencyExchangeService};
  CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService[label=<Currency Exchange Microservice>];

}


Microservices-Environments
~~~~~~~~~~~~~~~~~~~~~~~~~~~`
digraph architecture {
  rankdir=LR;

node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"];


  CurrencyCalculationService -> CurrencyExchangeService -> MicroserviceX

  subgraph CurrencyCalculationService {
      {rank=same; CurrencyCalculationService,CCDEV,CCQA,CCSTAGE, CCPROD};
  }
 
  subgraph CurrencyExchangeService {
      CurrencyExchangeService;
      {rank=same; CurrencyExchangeService,CEDEV,CEQA, CESTAGE, CEPROD};
  }
  
  subgraph MicroserviceX {
      CurrencyExchangeService;
      {rank=same; MicroserviceX,LSDEV,LSQA, LSSTAGE, LSPROD};
  }
 
  CCDEV,CEDEV,LSDEV[label=<DEV>,width=1]
  CCQA,CEQA,LSQA[label=<QA>,width=1]
  CCSTAGE,CESTAGE,LSSTAGE[label=<STAGE>,width=1]
  CCPROD,CEPROD,LSPROD[label=<PROD>,width=1]
  CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService[label=<Currency Exchange Microservice>];
MicroserviceX[label=<Microservice X>]

}

NAMINGSERVER
#######################
digraph architecture {
rankdir = TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"];

{rank=same; CurrencyCalculationService, CurrencyExchangeService};
CurrencyExchangeService -> NamingServer;
CurrencyCalculationService -> NamingServer;
MicroserviceX -> NamingServer
  CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService[label=<Currency Exchange Microservice>];
MicroserviceX[label=<Microservice X>]
NamingServer[label=<Naming Server or Service Registry>]
}

ClientSideLoadBalancing
#######################
digraph architecture {
rankdir = TB;
node[shape=component]
node[shape="rect"]
node[style=filled,color="#59C8DE"];

{rank=same; CurrencyExchangeService1, CurrencyExchangeService2, CurrencyExchangeService3};
{rank=same; LoadBalancer, NamingServer };
LoadBalancer -> CurrencyExchangeService1
LoadBalancer -> CurrencyExchangeService2
LoadBalancer -> CurrencyExchangeService3
CurrencyCalculationService -> LoadBalancer
LoadBalancer -> NamingServer
CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService1[label=<Currency Exchange - Instance 1>];
CurrencyExchangeService2[label=<Currency Exchange - Instance 2>];
CurrencyExchangeService3[label=<Currency Exchange - Instance 3>];
LoadBalancer[label=<Load Balancer>]
NamingServer[label=<Naming Server>]
}

ZipkinDistributedTracingServer
###############################
digraph architecture {
rankdir = TB;
node[shape=component]
node[shape="rect"]
node[style=filled,color="#59C8DE"];

Database[shape=cylinder]
RabbitMQ[shape=underline,style=unfilled,color="#000000"]
{rank=same; APIGateway, CurrencyCalculationService, CurrencyExchangeService};
APIGateway -> CurrencyCalculationService -> CurrencyExchangeService
CurrencyExchangeService -> RabbitMQ
CurrencyCalculationService -> RabbitMQ;
APIGateway -> RabbitMQ
RabbitMQ -> ZipkinDistributedTracingServer
ZipkinDistributedTracingServer -> Database

  CurrencyCalculationService[label=<Currency Conversion Microservice>];
CurrencyExchangeService[label=<Currency Exchange Microservice>];
APIGateway[label=<API Gateway>];
RabbitMQ[label=<Rabbit MQ>];
ZipkinDistributedTracingServer[label=<Distributed Tracing Server>];
}

CurrencyExchangeMicroserviceDeployment
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~``
digraph architecture {
rankdir = TB;

node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"];

{rank=same; DEV, QA, STAGE, PROD};

DEV -> DEV1

QA -> QA1
QA -> QA2

STAGE -> STAGE1

PROD -> PROD1
PROD -> PROD2
PROD -> PROD3
PROD -> PROD4

}

CurrencyConversionMicroserviceDeployment
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~``
digraph architecture {
rankdir = TB;
node[shape=component]
node[shape=record]
node[style=filled,color="#59C8DE"];

{rank=same; DEV, QA, STAGE, PROD};

DEV -> DEV1

QA -> QA1
QA -> QA2

STAGE -> STAGE1

PROD -> PROD1

}


```
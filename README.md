# bpms-assignment
GPTE Red Hat BPMS Suite 6.3 Advanced Process Development Assignment

Installation
============
0. Download the [gpte-bpms-advanced-630.vdi](https://drive.google.com/open?id=0B8mmXW6hJKdiaVpndWxFV3Nmbkk) VirtualBox image
0. Start VirtualBox and navigate to: Machine → New.
0. In the Create Virtual Machine panel, populate its fields:
0. Name: gpte-bpms-advanced
0. Type: Linux
0. Version: Red Hat (64-bit)
0. Click Next.
0. In the Memory panel, specify 4096 MB of RAM and click Next.
0. In the Hard Disk panel:
0. Select Use an existing virtual hard disk file.
0. Browse your hard drive and select the previously downloaded gpte-bpms-advanced-630.vdi image.
0. Click Create.
0. Click Start.

Configuration
=============
0. Create the following users:
```
sales1
executive1
```
0. With the following groups:
```
sales1=user,sales,kie-server,rest-all
executive1=user,executive,kie-server,rest-all
```

Dependencies
============
0. data-model
```
mvn clean package install
cp target/data-model-0.1.jar ~/lab/bpms/standalone/deployments/business-central.war/WEB-INF/lib
```
0. permit-service
```
mvn clean package install
cp target/permit.war ~/lab/bpms/standalone/deployments
```

Kie Server
==========
0. Deploy -> Rule Deployments
0. Add Container
0. Name: neworderpermitting_x_y
0. Group Name: com.solarvillage.neworderpermitting
0. Artifact Id: process-definition
0. Version: x.y
0. Finish
0. Start Container

Commands
========
0. Non HOA Member
```
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel", "_address":"Faria_lima", "_installationType":"residential", "_area":"100", "_hoaMember":"n"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances
```

0. HOA Member
```
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel", "_address":"Faria_lima", "_installationType":"residential", "_area":"100", "_hoaMember":"y"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances
```

0. Sales1
```
curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/claimed"
curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/started"
curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_installationRequested":"n"}' --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/completed"
```

0. Executive1
```
curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/claimed"
curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/started"
curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_finalDecision":"n"}' --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/completed"
```

0. Groups
```
curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=sales"
curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=executive"
```


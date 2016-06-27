# bpms-assignment
GPTE Red Hat BPMS Suite 6.3 Advanced Process Development Assignment

Installation
============
1. Download the [gpte-bpms-advanced-630.vdi](https://drive.google.com/open?id=0B8mmXW6hJKdiaVpndWxFV3Nmbkk) VirtualBox image
2. Start VirtualBox and navigate to: Machine → New.
* In the Create Virtual Machine panel, populate its fields:
* Name: gpte-bpms-advanced
* Type: Linux
* Version: Red Hat (64-bit)
3. Click Next.
4. In the Memory panel, specify 4096 MB of RAM and click Next.
5. In the Hard Disk panel:
* Select Use an existing virtual hard disk file.
* Browse your hard drive and select the previously downloaded gpte-bpms-advanced-630.vdi image.
* Click Create.
6. Click Start.

Configuration
=============
1. Create the following users:
* sales1
* executive1

2. With the following groups:
* sales1=user,sales,kie-server,rest-all
* executive1=user,executive,kie-server,rest-all

Dependencies
============
1. data-model
* mvn clean package install
* cp target/data-model-0.1.jar ~/lab/bpms/standalone/deployments/business-central.war/WEB-INF/lib

2. permit-service
* mvn clean package install
* cp target/permit.war ~/lab/bpms/standalone/deployments

Kie Server
==========
1. Deploy -> Rule Deployments
2. Add Container
3. Name: neworderpermitting_x_y
4. Group Name: com.solarvillage.neworderpermitting
5. Artifact Id: process-definition
6. Version: x.y
7. Finish
8. Start Container

Commands
========
1. Non HOA Member
- curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel", "_address":"Faria_lima", "_installationType":"residential", "_area":"100", "_hoaMember":"n"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances

2. HOA Member
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel", "_address":"Faria_lima", "_installationType":"residential", "_area":"100", "_hoaMember":"y"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances

Sales1
2.1 curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/claimed"
2.2 curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/started"
2.3 curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_installationRequested":"n"}' --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/completed"

Executive1
2.4 curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/claimed"
2.5 curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/started"
2.6 curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_finalDecision":"n"}' --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_x_y/tasks/<ID>/states/completed"

Groups
2.7 curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=sales"
2.8 curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=executive"




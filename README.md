# bpms-assignment
GPTE Red Hat BPMS Suite 6.3 Advanced Process Development Assignment
<p align="center"><img src="/files/png/bpmn2.png?raw=true"></p>


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

User Configuration
==================
* Create the following users:
```
sales1/redhat@123
executive1/redhat@123
```
* With the following groups:
```
sales1=user,sales,kie-server,rest-all
executive1=user,executive,kie-server,rest-all
```

Email Configuration
===================
* Update the file: 
```
~/lab/bpms/standalone/deployments/kie-server.war/WEB-INF/classes/userinfo.properties
```
```
sales1=sales1@acme.org:en-UK:sales1
executive1=executive1@acme.org:en-UK:executive1
```

* Start SMTP:
```
cd ~/lab/simple-smtp
$ ./simple-smtp
```

* Configure SMTP:
```
cd ~/lab/bpms/bin
./jboss-cli.sh -c --controller=127.0.0.1:9999
[standalone@127.0.0.1:9999] /system-property=org.kie.mail.session:add(value="java:jboss/mail/Default")
[standalone@127.0.0.1:9999] /subsystem=mail/mail-session=default:write-attribute(name=from, value=bpms@acme.org)
[standalone@127.0.0.1:9999] /subsystem=mail/mail-session=default/server=smtp:write-attribute(name=username,value=admin)
[standalone@127.0.0.1:9999] /subsystem=mail/mail-session=default/server=smtp:write-attribute(name=password,value=password)
[standalone@127.0.0.1:9999] /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=mail-smtp:write-attribute(name=host,value=localhost)
[standalone@127.0.0.1:9999] /socket-binding-group=standard-sockets/remote-destination-outbound-socket-binding=mail-smtp:write-attribute(name=port,value=2525)
[standalone@127.0.0.1:9999] exit
```

Dependencies
============
* data-model
```
mvn clean package install
cp target/data-model-1.0.jar ~/lab/bpms/standalone/deployments/business-central.war/WEB-INF/lib
```
* permit-service
```
mvn clean package install
cp target/permit.war ~/lab/bpms/standalone/deployments
```

Kie Server
==========
0. Deploy -> Rule Deployments
0. Add Container
0. Name: neworderpermitting_1_0
0. Group Name: com.solarvillage.neworderpermitting
0. Artifact Id: process-definition
0. Version: 1.0
0. Finish
0. Start Container

Commands
========
* Start a Non HOA Member instance
```
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel B. G. Goncalves", "_address":"Av. Brigadeiro Faria Lima, 3900 - Sao Paulo - Brazil", "_installationType":"residential", "_area":"100", "_hoaMember":"n"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances
```

* Start a HOA Member instance
```
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '{"_name":"Daniel B. G. Goncalves", "_address":"Av. Brigadeiro Faria Lima, 3900 - Sao Paulo - Brazil", "_installationType":"residential", "_area":"100", "_hoaMember":"y"}' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/processes/com.solarvillage.neworderpermitting.NewOrderPermitting/instances
```

* Signal for a meeting request with a sales representative (within 2 minutes after creating a HOA Member instance)
```
curl -X POST -H "Accept: application/json" -H "Content-Type: application/json" --user jboss:bpms -d '"meetingRequest"' http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/processes/instances/{id}/signal/meetingRequest
```

* Sales1: claim a task
```
curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/claimed"
```
* Sales1: start a task
```
curl -X PUT -H "Accept: application/json" --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/started"
```
* Sales1: complete a task
```
curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_installationRequested":"n"}' --user sales1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/completed"
```

* Executive1: claim a task
```
curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/claimed"
```
* Executive1: start a task
```
curl -X PUT -H "Accept: application/json" --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/started"
```
* Executive1: complete a task
```
curl -X PUT -H "Accept: application/json" -H "Content-Type: application/json" -d '{"_finalDecision":"n"}' --user executive1:redhat@123 "http://localhost:8080/kie-server/services/rest/server/containers/neworderpermitting_1_0/tasks/{id}/states/completed"
```

* Groups: all tasks assigned for sales group
```
curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=sales"
```
* Groups: all tasks assigned for executive group
```
curl -X GET -H "Accept: application/json" --user jboss:bpms "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/pot-owners?groups=executive"
```


JAXWS-Ex-HelloWorldEndpoint

How to run:
* mvn clean install -P mvn-deploy
* get wsdl from: http://localhost:8080/JAXWS-Ex-HelloWorldEndpoint-1.0-SNAPSHOT/HelloWorld?wsdl
* start soapui (L:\startSoapUL.cmd)
    * Create new soap project
    * enter wdsl url (http://localhost:8080/JAXWS-Ex-HelloWorldEndpoint-1.0-SNAPSHOT/HelloWorld?wsdl)
    * test the service

* see JAXWS-Ex-HelloWorldClient project for how to access the service using a java client.






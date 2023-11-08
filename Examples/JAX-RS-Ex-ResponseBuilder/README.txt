JAX-RS-Ex-ResponseBuilder

Demonstrates the use of a ResponseBuilder to 
control the content type of the response. 

See FileResource class. 

Run:
mvn install -P client-build
mvn install -P mvn-deploy
mvn install -P client-build
Reload Project (in IntelliJ)
Run RestClient as Java application.


Operations:
http://localhost:8080/JAX-RS-Ex-ResponseBuilder/resources/files?name=some.xml
http://localhost:8080/JAX-RS-Ex-ResponseBuilder/resources/files?name=index.html

Supports:
GET

Mime types: 
- application/xml
- text/html


JAX-RS-Ex-SubresourceLocators

Demonstrate the use of subresource locators to produce hierarchical urls.


Run
mvn install -P client-build
mvn install -P mvn-deploy
mvn install -P client-build
Reload Project (in IntelliJ)
Run RestClient as Java application.

Operations:
http://localhost:8080/JAX-RS-Ex-SubresourceLocators-1.0-SNAPSHOT/resources/courses/LB1300
http://localhost:8080/JAX-RS-Ex-SubresourceLocators-1.0-SNAPSHOT/resources/courses/LB1300/events/0
http://localhost:8080/JAX-RS-Ex-SubresourceLocators-1.0-SNAPSHOT/resources/courses/LB1300/events/0/students/0

Supports:
GET

Mime types: 
application/xml

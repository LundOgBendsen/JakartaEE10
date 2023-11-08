REST - sample, returning file

Demonstrates how to return a file from a resource. The java.io.FIle class i known to Jax-RS so the contents will be returned to the client.
See the CourseResource class and Main class.

Run
mvn install -P client-build
mvn install -P mvn-deploy
mvn install -P client-build
Run RestClient as Java application.


Operations:
http://localhost:8080/JAX-RS-Ex-ReturningFile-1.0-SNAPSHOT/startAppServer.cmd

Supports:
GET

Mime types: 
text/html

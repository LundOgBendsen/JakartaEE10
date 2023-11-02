JEE-Ex-JAXRS21-SSE

Demonstrates server sent events (SEE) with 2 client types - a Java client and a web client

See SseResource for server implementation.
See SseClient for Java client implementation.
See index.html for web client

Operations:
To build and deploy the server side:
mvn install -P mvn-deploy

To build Java client:
mvn install -P client-build

To run:
1. Deploy to server
2. Go to http://localhost:8080/JEE-Ex-JAXRS21-SSE-1.0-SNAPSHOT/ to see web client
3. Run SseClient as a Java App

Also notice the server console output.

JEE-Ex-CDI2.0-Events

Demonstrates async events, priorities, and error handling in CDI 2.0.



How to run
1. Run on server or
mvn install -P mvn-deploy
2. Go to: http://localhost:8080/JEE-Ex-CDI2.0-Events/index.html and see console output
3  Go to: http://localhost:8080/JEE-Ex-CDI2.0-Events/index.html?error=true to see handling of observers throwing exceptions.

See
* BusinessServiceImpl
* AuditObserver
* LoggingObserver
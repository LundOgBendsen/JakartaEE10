JPA-Ex-DAO-Pattern

Shows the classic dao pattern. 

See
- PersonRepository (dao interface)
- PersonRepositoryImpl (dao implementation)
- StandaloneJpaClient (client)


How to run:
1. mvn clean install
2. Reload maven dependencies (Maven > Reload project)
3. Run dk/lundogbendsen/client/standalone/StandaloneJpaClient.java
as a Java Application.

NB. You may have to stop the database, run L:\deleteDatabases.cmd, start the database before running the StandaloneJpaClient class.


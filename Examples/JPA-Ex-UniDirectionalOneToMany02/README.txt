JPA-Ex-UniDirectionalOneToMany02

Demonstrates a one to many association with navigation 
from one side (uni-directional)

See dk/lundogbendsen/model/Person.java which has an association
to a Collection of Cars.

How to run:
1. mvn clean install
2. Reload maven dependencies (Maven > Reload project)
3. Run dk/lundogbendsen/client/standalone/StandaloneJpaClient.java
as a Java Application.

NB. You may have to stop the database, run L:\deleteDatabases.cmd, start the database before running the StandaloneJpaClient class.

NB. Formerly this project was named JPA-Ex-Uni-directional-OneToMany(navigation-from-many-side)


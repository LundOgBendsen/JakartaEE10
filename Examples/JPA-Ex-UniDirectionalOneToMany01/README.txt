JPA-Ex-UniDirectionalOneToMany01

Demonstrate a one way association managed by JPA. Navigation is from the one to the many side.

See dk/lundogbendsen/model/Person.java which has an association
to a Collection of Cars.

How to run:
1. mvn clean install
2. Reload maven dependencies (Maven > Reload project)
3. Run dk/lundogbendsen/client/standalone/StandaloneJpaClient.java
as a Java Application.

NB. Formerly this project was named JPA-Ex-UniDirectionalOneToMany(NavigationFromTheOneMultiplicity)

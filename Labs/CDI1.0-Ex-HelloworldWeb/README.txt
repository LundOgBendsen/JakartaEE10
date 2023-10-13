CDI1.0-Ex-HelloWorld
Demonstrates the use of CDI in web app.
Built using maven and latest InteliJ version.

Error during artifact deployment. See server log for details.
 Artifact CDI1.0-Ex-HelloworldWeb:war exploded: java.lang.Exception: {"WFLYCTL0080: Failed services" => {"jboss.deployment.unit.\"CDI1.0-Ex-HelloworldWeb-1.0-SNAPSHOT.war\".WeldStartService" => "Failed to start service
    Caused by: org.jboss.weld.exceptions.DeploymentException: WELD-001408: Unsatisfied dependencies for type BusinessService with qualifiers @Default
  at injection point [BackedAnnotatedField] @Inject private dk.lundogbendsen.web.GreeterServlet.service
  at dk.lundogbendsen.web.GreeterServlet.service(GreeterServlet.java:0)
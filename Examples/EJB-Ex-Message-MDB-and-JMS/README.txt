EJB-Ex-Message - MDB and JMS

Demonstrates use of JMS.
MessageProducerClient -> jms/queue/test -> MDB -> jms/topic/test -> MDB + MessageConsumerClient

How to run:
mvn install -P mvn-deploy
mvn install -P client-build

Run the class: client.ejb.mdb.MessageConsumerClient.java
as a Java Application.

Run the class: client.ejb.mdb.MessageProducerClient.java
as a Java Application.

Notice the queue definitions in standalone-lb.xml in server config folder.

Security has been disabled for ActiveMQ.


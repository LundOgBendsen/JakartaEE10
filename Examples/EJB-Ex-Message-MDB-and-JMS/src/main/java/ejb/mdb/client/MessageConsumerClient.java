package ejb.mdb.client;

import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageConsumerClient {
	
	//private static final String EJB_JNDI = "EJB-Ex-Session-HelloWorld-1.0-SNAPSHOT/HelloBean!dk.lundogbendsen.ejb.Hello";

	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	public static void main(String[] args) throws Exception {

		// Supply the JNDI location for the destination (queue/topic)
		// from which we are retrieving messages
		// String destinationJndiLocation = "queue/testQueue";
		String destinationJndiLocation = "jms/topic/test";

		// Use the JNDI tree to retrieve the resources
		final Context ctx = createInitialContext();

		ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
		Destination destination = (Destination) ctx.lookup(destinationJndiLocation);

		// Create connection + session + consumer
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageConsumer consumer = session.createConsumer(destination);

		// Register a MessageListener
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println(textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		// Activate message receiving and quit after 100 seconds
		System.out.println("Waiting for messages...");
		connection.start();
		TimeUnit.SECONDS.sleep(100);

		// Free resources again
		connection.close();
	}
}

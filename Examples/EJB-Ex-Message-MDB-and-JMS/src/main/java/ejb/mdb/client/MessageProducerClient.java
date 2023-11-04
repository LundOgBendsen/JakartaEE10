package ejb.mdb.client;

import java.util.Hashtable;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MessageProducerClient {

	private static Context createInitialContext() throws NamingException {
		Hashtable<String, Object> env = new Hashtable<>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
		InitialContext ctx = new InitialContext(env);
		return ctx;
	}

	public static void main(String[] args) throws Exception {

		// Supply the JNDI location for the destination (queue/topic)
		// to which we are sending messages
		String destinationJndiLocation = "jms/queue/test";
		// String destinationJndiLocation = "topic/testTopic";

		final Context ctx = createInitialContext();
		
        ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
		Destination destination = (Destination) ctx.lookup(destinationJndiLocation);

		// Create connection + session + consumer + message object
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(destination);
		TextMessage textMessage = session.createTextMessage();

		// Send messages now
		for (int n = 1; n <= 5; n++) {
			textMessage.setText("Message " + n);
			producer.send(textMessage);
			System.out.println("MessageProducerClient sent [" + textMessage.getText() + "]");
		}

		// Free resources again
		connection.close();
	}
}

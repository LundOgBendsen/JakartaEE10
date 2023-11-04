package ejb.mdb.bean;

import jakarta.annotation.Resource;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJBException;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

//Property "destination" is a JBoss specific way to declare
//the JNDI location of the JMS destination being listened to
@MessageDriven(name = "QueueToTopicBean", activationConfig = {

		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),

		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/queue/test"),

		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })

public class QueueToTopicBean implements MessageListener {

	// We use the vendor specific attribute "mappedName"
	// to indicate global JNDI entries. Using this attribute
	// is not guaranteed to be supported by all vendors!

	@Resource(mappedName = "java:/jms/topic/test")
	private Destination destination;

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;

	public void onMessage(Message msg) {
		Connection connection = null;
		try {

			// Log the message contents
			TextMessage inMessage = (TextMessage) msg;
			System.out.println("QueueToTopicBean received [" + inMessage.getText() + "]");

			connection = factory.createConnection();

			// The container SHOULD ignore arguments to createSession for
			// containere managed TX. The EJB 3.0 specificatoin recommends
			// that we always supply the following values:
			// transacted=true og acknowledgeMode=SESSION_TRANSACTED=0 (13.3.5,
			// p.332).
			// JBoss 5 however does not IGNORE the values: They must be :
			// transacted=false og acknowledgeMode=SESSION_TRANSACTED=0
			Session session = connection.createSession();//false, Session.SESSION_TRANSACTED);

			MessageProducer producer = session.createProducer(destination);

			// Forward the message to the MessageConsumerBean
			TextMessage outMessage = session.createTextMessage();
			outMessage.setText(inMessage.getText());
			producer.send(outMessage);
		} catch (JMSException e) {
			throw new EJBException(e);
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

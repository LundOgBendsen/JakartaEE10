package ejb.mdb.bean;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJBException;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

// Property "destinationType" defaults to =javax.jms.Queue
// - we must therefore explicitly tell the container that
// we are listening to a Topic.

// Property "destination" is a JBoss specific way to declare
// the JNDI lokationen of the JMS destination being listened to
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "jms/topic/test") })
public class MessageConsumerBean implements MessageListener {

	public void onMessage(Message message) {
		try {
			TextMessage tm = (TextMessage) message;
			System.out.println("MessageConsumerBean received [" + tm.getText() + "]");
		} catch (Exception e) {
			throw new EJBException(e);
		}
	}
}



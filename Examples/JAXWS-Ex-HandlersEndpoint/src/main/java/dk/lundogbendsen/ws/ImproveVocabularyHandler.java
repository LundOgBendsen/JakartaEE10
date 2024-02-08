package dk.lundogbendsen.ws;

import java.util.Set;
import javax.xml.namespace.QName;
import jakarta.xml.ws.handler.MessageContext;
import jakarta.xml.ws.handler.soap.SOAPHandler;
import jakarta.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Node;

/**
 * This JAX-WS handler shows how to manipulate the soap body. It replaces words
 * in the body's text elements.
 * 
 */
public class ImproveVocabularyHandler implements SOAPHandler<SOAPMessageContext> {

	// words we wish to replace
	private String[] badWords = { "damn", "fool", "crap" };

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("ImproveVocabularyHandler.handleFault()");
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("ImproveVocabularyHandler.handleMessage()");

		try {
			// we assume that the body has only one element containing a
			// text-node with the name (sayHello(name))
			Node sayHello = context.getMessage().getSOAPBody().getFirstChild();
			Node name = sayHello.getChildNodes().item(0);
			String textContent = name.getTextContent().trim().toLowerCase();
			for (String s : badWords) {
				textContent = textContent.replace(s, "xxx");
			}
			name.setTextContent(textContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// continue processing of the request
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

	@Override
	public void close(MessageContext context) {
		// do nothing
	}

}

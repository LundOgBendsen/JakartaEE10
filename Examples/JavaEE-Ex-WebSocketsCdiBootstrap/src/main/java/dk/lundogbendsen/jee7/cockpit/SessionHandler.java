package dk.lundogbendsen.jee7.cockpit;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;
import jakarta.websocket.Session;
import jakarta.xml.bind.annotation.XmlTransient;

@ApplicationScoped
public class SessionHandler {
	String pw1;
	String pw2;
	
	
	
	private final Set<Session> sessions = new HashSet<Session>();

	public void addSession(final Session session) {
		sessions.add(session);
	}

	public void removeSession(final Session session) {
		sessions.remove(session);
	}

	public void sendToSessions(final String message) {
		for (Session session : sessions) {
			sendToSession(session, message);
		}
	}

	private void sendToSession(final Session session, final String message) {
		try {
			session.getBasicRemote().sendText(message);
			System.out.println("Sent " + message.toString() + " to session "
					+ session.getId());
		} catch (IOException ex) {
			sessions.remove(session);
		}
	}
}
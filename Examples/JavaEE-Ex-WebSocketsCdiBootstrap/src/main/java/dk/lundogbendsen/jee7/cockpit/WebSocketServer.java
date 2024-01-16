package dk.lundogbendsen.jee7.cockpit;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ApplicationScoped
@ServerEndpoint("/wsserver")
public class WebSocketServer {

	@Inject
	private SessionHandler valueSessionHandler;

	@OnOpen
	public void open(final Session session) {
		System.out.println("WS-Server: got a connection");
		valueSessionHandler.addSession(session);
	}

	@OnClose
	public void close(final Session session) {
		System.out.println("WS-Server: closed a connection");
		valueSessionHandler.removeSession(session);
	}

	@OnError
	public void onError(final Throwable error) {
		Logger.getLogger(WebSocketServer.class.getName()).log(
				Level.SEVERE, null, error);
	}

	@OnMessage
	public void handleMessage(final String message, final Session session) {
		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();
		}
	}
}

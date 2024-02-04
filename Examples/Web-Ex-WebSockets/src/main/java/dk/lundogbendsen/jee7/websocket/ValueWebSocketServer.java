package dk.lundogbendsen.jee7.websocket;

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
@ServerEndpoint("/actions")
public class ValueWebSocketServer {
	@Inject
	private ValueSessionHandler valueSessionHandler;

	@OnOpen
	public void open(final Session session) {
		valueSessionHandler.addSession(session);
	}

	@OnClose
	public void close(final Session session) {
		System.out.println("Removing af session!");
		valueSessionHandler.removeSession(session);
	}

	@OnError
	public void onError(final Throwable error) {
		System.out.println("ERROR!!!!!!!!!");
		
		Logger.getLogger(ValueWebSocketServer.class.getName()).log(
				Level.SEVERE, null, error);
	}

	@OnMessage
	public void handleMessage(final String message, final Session session) {
		try (JsonReader reader = Json.createReader(new StringReader(message))) {
			JsonObject jsonMessage = reader.readObject();

			String command = jsonMessage.getString("action");
			switch (command) {
			case "add": {
				System.out.println("Received " + jsonMessage.toString()
						+ " from session " + session.getId());
				Device device = new Device();
				device.setName(jsonMessage.getString("name"));
				device.setDescription(jsonMessage.getString("description"));
				device.setType(jsonMessage.getString("type"));
				device.setShared(jsonMessage.getString("shared")
						.equalsIgnoreCase("Shared"));
				device.setStatusOff();
				try {
					device.setAutoOffSec(Integer.parseInt(jsonMessage
							.getString("autooffsec").trim()));
				} catch (NumberFormatException e) {
					device.setAutoOffSec(0);
				}
				System.out.println("Interpreted as " + device);
				valueSessionHandler.addDevice(session, device);
				break;
			}

			case "remove": {
				int id = jsonMessage.getInt("id");
				valueSessionHandler.removeDevice(session, id);
				break;
			}

			case "toggle": {
				int id = jsonMessage.getInt("id");
				valueSessionHandler.toggleDevice(session, id);
				break;
			}

			default: {
				System.out.println("Unknown message " + command);
				break;
			}
			}
		}
	}
}

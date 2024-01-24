package dk.lundogbendsen.jee7.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.Resource;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.JsonObject;
import jakarta.json.spi.JsonProvider;
import jakarta.websocket.Session;

@ApplicationScoped
public class ValueSessionHandlerWithAuto
{
  private int deviceId = 0;
  private final Set<Session> sessions = new HashSet<Session>();
  private final Set<Device> devices = new HashSet<Device>();
  private final HashMap<Session, Set<Device>> privatedevices = new HashMap<Session, Set<Device>>();

  public void addSession(final Session session)
  {
    sessions.add(session);

    Set<Device> priv = privatedevices.get(session);
    if (priv == null)
    {
      priv = new HashSet<Device>();
    }
    priv.addAll(devices);

    for (Device device : priv)
    {
      JsonObject addMessage = createAddMessage(device);
      sendToSession(session, addMessage);
    }

  }

  public void removeSession(final Session session)
  {
    sessions.remove(session);
    privatedevices.remove(session);
  }

  public void addDevice(final Session session, final Device device)
  {
    device.setId(deviceId);
    deviceId++;

    if (device.isShared())
    {
      devices.add(device);
    }
    else
    {
      Set<Device> priv = privatedevices.get(session);
      if (priv == null)
      {
        priv = new HashSet<Device>();
        privatedevices.put(session, priv);
      }
      priv.add(device);
    }

    JsonObject addMessage = createAddMessage(device);
    if (device.isShared())
      sendToSessions(addMessage);
    else
      sendToSession(session, addMessage);
  }

  public void removeDevice(final Session session, final int id)
  {
    Device device = getDeviceById(session, id);
    if (device != null)
    {
      if (device.isShared())
      {
        devices.remove(device);
      }
      else
      {
        Set<Device> priv = privatedevices.get(session);
        if (priv != null)
        {
          priv.remove(device);
        }
      }

      JsonProvider provider = JsonProvider.provider();
      JsonObject removeMessage = provider.createObjectBuilder().add("action", "remove").add("id", id).build();
      if (device.isShared())
        sendToSessions(removeMessage);
      else
        sendToSession(session, removeMessage);
    }
  }

  @Resource(lookup = "java:comp/DefaultManagedScheduledExecutorService")
  ManagedScheduledExecutorService executor;

  public void toggleDevice(final Session session, final int id)
  {
    JsonProvider provider = JsonProvider.provider();
    Device device = getDeviceById(session, id);
    if (device != null)
    {
      if (device.isStatusOn())
      {
        device.setStatusOff();
      }
      else
      {
        device.setStatusOn();
      }
      JsonObject updateDevMessage = provider.createObjectBuilder().add("action", "toggle").add("id", device.getId()).add("status", device.isStatusOn() ? "On" : "Off").build();
      if (device.isShared())
      {
        sendToSessions(updateDevMessage);
      }
      else
      {
        sendToSession(session, updateDevMessage);
      }

      if (device.isStatusOn() && device.getAutoOffSec() > 0) createPendingSwitchOff(session, id, device.getAutoOffSec());

    }
  }

  private void createPendingSwitchOff(final Session session, final int id, final int sec)
  {
    @SuppressWarnings("unused")
    ScheduledFuture<?> future = executor.schedule(new Runnable()
    {

      @Override
      public void run()
      {
        if (session.isOpen())
        {
          Device device = getDeviceById(session, id);
          if (device != null && device.isStatusOn())
          {
            System.out.println("Automatic switch off");
            toggleDevice(session, id);
          }
        }
      }
    }, sec, TimeUnit.SECONDS);
  }

  private Device getDeviceById(final Session session, final int id)
  {
    for (Device device : devices)
    {
      if (device.getId() == id)
      {
        return device;
      }
    }

    Set<Device> priv = privatedevices.get(session);
    if (priv != null)
    {
      for (Device device : priv)
      {
        if (device.getId() == id)
        {
          return device;
        }
      }
    }

    return null;
  }

  private JsonObject createAddMessage(final Device device)
  {
    JsonProvider provider = JsonProvider.provider();
    JsonObject addMessage = provider.createObjectBuilder().add("action", "add").add("id", device.getId()).add("name", device.getName()).add("type", device.getType().getDescription()).add("shared", device.isShared() ? "Shared" : "Private").add("status", device.isStatusOn() ? "On" : "Off").add("autooffsec", device.getAutoOffSec()).add("description", device.getDescription()).build();
    return addMessage;
  }

  private void sendToSessions(final JsonObject message)
  {
    for (Session session : sessions)
    {
      sendToSession(session, message);
    }
  }

  private void sendToSession(final Session session, final JsonObject message)
  {
    try
    {
      session.getBasicRemote().sendText(message.toString());
      System.out.println("Sent " + message.toString() + " to session " + session.getId());
    }
    catch (IOException ex)
    {
      sessions.remove(session);
      privatedevices.remove(session);
      Logger.getLogger(ValueSessionHandlerWithAuto.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
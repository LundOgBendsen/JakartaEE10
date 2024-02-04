package dk.lundogbendsen.jee7.websocket;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

public class Device
{
  private int id;
  private String name;
  private boolean status;
  private ApplianceType type;
  private String description;
  private boolean shared;
  private int autoOffSec;
  
  public Device()
  {
  }

  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public boolean isStatusOn()
  {
    return status;
  }

  public ApplianceType getType()
  {
    return type;
  }

  public String getDescription()
  {
    return description;
  }

  public boolean isShared()
  {
    return shared;
  }

  public int getAutoOffSec()
  {
    return autoOffSec;
  }
  
  public void setId(final int id)
  {
    this.id = id;
  }

  public void setName(final String name)
  {
    this.name = name;
  }

  public void setStatusOn()
  {
    this.status = true;
  }

  public void setStatusOff()
  {
    this.status = false;
  }

  public void setStatus(final boolean status)
  {
    this.status = status;
  }

  public void setType(final String type)
  {
    this.type = ApplianceType.valueOf(type.trim().toUpperCase());
  }

  public void setDescription(final String description)
  {
    this.description = description;
  }

  public void setShared(final boolean shared)
  {
    this.shared = shared;
  }

  public void setAutoOffSec(final int autoOffSec)
  {
    this.autoOffSec = autoOffSec;
  }

}

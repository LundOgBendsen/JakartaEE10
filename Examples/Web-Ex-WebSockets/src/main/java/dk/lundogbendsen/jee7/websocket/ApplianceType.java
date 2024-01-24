package dk.lundogbendsen.jee7.websocket;

public enum ApplianceType
{
  LED("LED light"),
  NEON("Neon light"),
  INCANDESCENT("Incandescent light"),
  FAN("Ceiling fan"),
  OTHER("Other");

  private final String description;

  public String getDescription()
  {
    return description;
  }

  private ApplianceType(final String description)
  {
    this.description = description;
  }
}

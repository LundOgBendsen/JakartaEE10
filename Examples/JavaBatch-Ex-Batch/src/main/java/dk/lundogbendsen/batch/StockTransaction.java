package dk.lundogbendsen.batch;

import java.io.Serializable;
import java.util.Date;

public class StockTransaction implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final String sku;

  private final String name;

  private final int units;

  private final Date lastOrderDate;

  public StockTransaction(final String sku, final String name, final int units, final Date lastOrderDate)
  {
    super();
    this.sku = sku;
    this.name = name;
    this.units = units;
    this.lastOrderDate = lastOrderDate;
  }

  public String getSku()
  {
    return sku;
  }

  public String getName()
  {
    return name;
  }

  public int getUnits()
  {
    return units;
  }

  public Date getLastOrderDate()
  {
    return lastOrderDate;
  }

  @Override
  public String toString()
  {
    return "StockTransaction [sku=" + sku + ", name=" + name + ", units=" + units + ", lastOrderDate=" + lastOrderDate + "]";
  }

}

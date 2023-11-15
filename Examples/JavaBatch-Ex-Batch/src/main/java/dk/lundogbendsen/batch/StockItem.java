package dk.lundogbendsen.batch;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EQUIPMENT_STOCK")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "StockItem.findAll", query = "SELECT c FROM StockItem c"),
  @NamedQuery(name = "StockItem.findBySKU", query = "SELECT c FROM StockItem c WHERE c.sku = :sku") })
public class StockItem implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "SKU")
  private String sku;

  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "name")
  private String name;

  @Basic(optional = false)
  @NotNull
  @Column(name = "units")
  private int units;

  @Basic(optional = true)
  @Temporal(TemporalType.DATE)
  @Column(name = "lastorderdate")
  private Date lastOrderDate;

  @Basic(optional = true)
  @Temporal(TemporalType.DATE)
  @Column(name = "lastresupplydate")
  private Date lastResupplyDate;

  public StockItem()
  {
  }


  public StockItem(final String sku, final String name)
  {
    super();
    this.sku = sku;
    this.name = name;
    this.units = 0;
    this.lastOrderDate = null;
    this.lastResupplyDate = null;
  }


  public String getSku()
  {
    return sku;
  }


  public void setSku(final String sku)
  {
    this.sku = sku;
  }


  public String getName()
  {
    return name;
  }


  public void setName(final String name)
  {
    this.name = name;
  }


  public int getUnits()
  {
    return units;
  }


  public void setUnits(final int units)
  {
    this.units = units;
  }

  public void addUnits(final int units)
  {
    this.units += units;
  }


  public Date getLastOrderDate()
  {
    return lastOrderDate;
  }


  public void setLastOrderDate(final Date lastOrderDate)
  {
    this.lastOrderDate = lastOrderDate;
  }


  public Date getLastResupplyDate()
  {
    return lastResupplyDate;
  }


  public void setLastResupplyDate(final Date lastResupplyDate)
  {
    this.lastResupplyDate = lastResupplyDate;
  }


  @Override
  public String toString()
  {
    return "StockItem [sku=" + sku + ", name=" + name + ", units=" + units + ", lastOrderDate=" + lastOrderDate + ", lastResupplyDate=" + lastResupplyDate + "]";
  }


  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((sku == null) ? 0 : sku.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj)
  {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    StockItem other = (StockItem) obj;
    if (sku == null)
    {
      if (other.sku != null) return false;
    }
    else
      if (!sku.equals(other.sku)) return false;
    return true;
  }

}

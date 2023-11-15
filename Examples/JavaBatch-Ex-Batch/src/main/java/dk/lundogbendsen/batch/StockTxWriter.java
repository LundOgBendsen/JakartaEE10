package dk.lundogbendsen.batch;

import java.util.Date;
import java.util.List;

import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Named
@Dependent
public class StockTxWriter extends AbstractItemWriter
{

  @PersistenceContext
  EntityManager em;

  @Override
  public void writeItems(final List<Object> list)
  {
    for (Object o : list)
    {
      StockTransaction s = (StockTransaction) o;

      StockItem si = em.find(StockItem.class, s.getSku());
      if (si == null)
      {
        si = new StockItem(s.getSku(), s.getName());
        si.setUnits(s.getUnits());
        si.setLastOrderDate(s.getLastOrderDate());
        si.setLastResupplyDate(new Date());

        em.persist(si);
      }
      else
      {
    	 //update StockItem. Since the entity is in managed state all
    	 //changes will be written to the database upon commit.
    	si.addUnits(s.getUnits());
        si.setLastOrderDate(s.getLastOrderDate());
        si.setLastResupplyDate(new Date());
      }
    }
  }
}

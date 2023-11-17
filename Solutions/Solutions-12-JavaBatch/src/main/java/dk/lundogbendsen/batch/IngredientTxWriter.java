package dk.lundogbendsen.batch;

import java.util.List;

import dk.lundogbendsen.batch.recipes.model.Ingredient;
import jakarta.batch.api.chunk.AbstractItemWriter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Named
@Dependent
public class IngredientTxWriter extends AbstractItemWriter
{

  @PersistenceContext
  EntityManager em;

  @Override
  public void writeItems(final List<Object> list)
  {
    for (Object o : list)
    {
      IngredientTransaction transaction = (IngredientTransaction) o;

      Query findByName = em.createNamedQuery("Ingredient.findByName");
      findByName.setParameter("name", transaction.getName());
      List<Ingredient> resultList = findByName.getResultList();

      if (resultList.isEmpty())
      {
        System.out.println("batch: creating new ingredient");
        Ingredient ingredient = new Ingredient();
        ingredient.setUnit(transaction.getUnit());
        ingredient.setCalories(transaction.getCalories());
        ingredient.setName(transaction.getName());

        em.persist(ingredient);
      }
      else
      {
        //update Ingredient. Since the entity is in managed state all
        //changes will be written to the database upon commit.
        System.out.println("batch: updating ingredients: " + resultList.size());

        for (Ingredient ingredient : resultList) {
          ingredient.setUnit(transaction.getUnit());
          ingredient.setCalories(transaction.getCalories());
        }

      }
    }
  }
}

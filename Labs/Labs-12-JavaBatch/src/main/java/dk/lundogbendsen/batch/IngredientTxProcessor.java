package dk.lundogbendsen.batch;


import java.security.InvalidParameterException;
import java.util.Random;

import dk.lundogbendsen.batch.recipes.model.MeasureUnit;
import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class IngredientTxProcessor implements ItemProcessor
{
  private final static Random ran = new Random();

  @Override
  public IngredientTransaction processItem(final Object t)
  {
    String line = (String) t;

    //[name, unit(int), calories(double)]
    String[] tokens = line.split("[\\s]*;[\\s]*");

    String name = null;
    MeasureUnit mUnit = null;
    double calories = 0.0;
    //insert code here
/*
     name = tokens[0];
     unit = Integer.parseInt(tokens[1]);
     calories = Double.parseDouble(tokens[2]);
    MeasureUnit mUnit = MeasureUnit.Gr; // default unit
    try {
      mUnit = MeasureUnit.getMeasureUnitByInt(unit);
    }
    catch (InvalidParameterException e)
    {
    }
*/
    try
    {
      int ms = 3000 + ran.nextInt(7000);
      Thread.sleep(ms);
    }
    catch (InterruptedException e)
    {
    }

    IngredientTransaction st = null; //create a new object = new IngredientTransaction(name, mUnit, calories);
    return st;
  }
}

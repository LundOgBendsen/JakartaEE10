package com.recipes.batch;


import java.security.InvalidParameterException;
import java.util.Random;

import com.recipes.model.MeasureUnit;
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
    String line = (String) t; //reader returns strings

    String[] tokens = line.split("[\\s]*;[\\s]*");

    String name = tokens[0];
    int unit = Integer.parseInt(tokens[1]);
    double calories = Double.parseDouble(tokens[2]);
    MeasureUnit mUnit = MeasureUnit.Gr; // default unit
    try {
      mUnit = MeasureUnit.getMeasureUnitByInt(unit);
    }
    catch (InvalidParameterException e)
    {
    }

    try
    {
      int ms = 3000 + ran.nextInt(7000);
      Thread.sleep(ms);
    }
    catch (InterruptedException e)
    {
    }

    IngredientTransaction st = new IngredientTransaction(name, mUnit, calories);
    return st;
  }
}

package com.recipes.batch;


import com.recipes.model.MeasureUnit;

import java.io.Serializable;

public class IngredientTransaction implements Serializable
{

  private final String name;

  private final MeasureUnit unit;

  private final double calories;

  public IngredientTransaction(final String name, final MeasureUnit unit, final double calories)
  {
    super();

    this.name = name;
    this.unit = unit;
    this.calories = calories;
  }
  public String getName()
  {
    return name;
  }

  public MeasureUnit getUnit()
  {
    return unit;
  }

  public double getCalories()
  {
    return calories;
  }

  @Override
  public String toString()
  {
    return "IngredientTransaction [name=" + name + ", unit=" + unit + ", calories=" + calories + "]";
  }

}

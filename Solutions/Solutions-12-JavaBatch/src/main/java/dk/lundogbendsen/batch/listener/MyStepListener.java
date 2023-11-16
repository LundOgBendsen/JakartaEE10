package dk.lundogbendsen.batch.listener;

import jakarta.batch.api.listener.AbstractStepListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class MyStepListener extends AbstractStepListener
{
  @Override
  public void beforeStep()
  {
    System.out.println("beforeStep");
  }
  @Override
  public void afterStep()
  {
    System.out.println("afterStep");
  }
}

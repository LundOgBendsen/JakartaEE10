package dk.lundogbendsen.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractItemProcessListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class MyProcessListener extends AbstractItemProcessListener
{
  @Override
  public void beforeProcess(final Object a)
  {
    System.out.println("beforeProcess " + a);
  }

  @Override
  public void afterProcess(final Object a, final Object b)
  {
    System.out.println("afterProcess " + a + " : " + b);
  }
}

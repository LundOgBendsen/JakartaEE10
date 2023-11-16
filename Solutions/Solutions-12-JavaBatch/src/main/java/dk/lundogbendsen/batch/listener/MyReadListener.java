package dk.lundogbendsen.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractItemReadListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class MyReadListener extends AbstractItemReadListener
{
  @Override
  public void beforeRead()
  {
    System.out.println("beforeRead");
  }
  @Override
  public void afterRead(final Object r)
  {
    System.out.println("afterRead: "+r);
  }
}

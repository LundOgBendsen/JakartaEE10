package dk.lundogbendsen.batch.listener;

import java.util.List;

import jakarta.batch.api.chunk.listener.AbstractItemWriteListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class MyWriteListener extends AbstractItemWriteListener
{
  @Override
  public void beforeWrite(final List<Object> w)
  {
    System.out.println("beforeWrite" + w);
  }

  @Override
  public void afterWrite(final List<Object> w)
  {
    System.out.println("afterWrite: " + w);
  }
}

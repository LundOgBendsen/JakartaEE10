package dk.lundogbendsen.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractChunkListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class MyChunkListener extends AbstractChunkListener
{
  @Override
  public void beforeChunk()
  {
    System.out.println("beforeChunk");
  }
  @Override
  public void afterChunk()
  {
    System.out.println("afterChunk");
  }
}

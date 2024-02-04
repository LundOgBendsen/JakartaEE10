package com.recipes.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractChunkListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;

@Named
@Dependent
public class LoggingChunkListener extends AbstractChunkListener
{
  @Inject
  Logger log;

  @Override
  public void beforeChunk()
  {
    log.info("beforeChunk");
  }
  @Override
  public void afterChunk()
  {
    log.info("afterChunk");
  }
}

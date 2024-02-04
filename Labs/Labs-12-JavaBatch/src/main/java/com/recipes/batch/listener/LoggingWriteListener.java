package com.recipes.batch.listener;

import java.util.List;
import java.util.logging.Logger;

import jakarta.batch.api.chunk.listener.AbstractItemWriteListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@Dependent
public class LoggingWriteListener extends AbstractItemWriteListener
{
  @Inject
  Logger log;
  @Override
  public void beforeWrite(final List<Object> w)
  {
    log.info("beforeWrite" + w);
  }

  @Override
  public void afterWrite(final List<Object> w)
  {
    log.info("afterWrite: " + w);
  }
}

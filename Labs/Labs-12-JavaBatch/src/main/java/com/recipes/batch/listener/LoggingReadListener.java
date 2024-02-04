package com.recipes.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractItemReadListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;

@Named
@Dependent
public class LoggingReadListener extends AbstractItemReadListener
{
  @Inject
  Logger log;
  @Override
  public void beforeRead()
  {
    log.info("beforeRead");
  }
  @Override
  public void afterRead(final Object r)
  {
    log.info("afterRead: "+r);
  }
}

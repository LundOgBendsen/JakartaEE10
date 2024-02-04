package com.recipes.batch.listener;

import jakarta.batch.api.chunk.listener.AbstractItemProcessListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;

@Named
@Dependent
public class LoggingProcessListener extends AbstractItemProcessListener
{
  @Inject
  Logger log;
  @Override
  public void beforeProcess(final Object a)
  {
    log.info("beforeProcess " + a);
  }

  @Override
  public void afterProcess(final Object a, final Object b)
  {
    log.info("afterProcess " + a + " : " + b);
  }
}

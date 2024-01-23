package com.recipes.batch.listener;

import jakarta.batch.api.listener.AbstractStepListener;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.logging.Logger;

@Named
@Dependent
public class LoggingStepListener extends AbstractStepListener
{
  @Inject
  Logger log;
  @Override
  public void beforeStep()
  {
    log.info("beforeStep");
  }
  @Override
  public void afterStep()
  {
    log.info("afterStep");
  }
}

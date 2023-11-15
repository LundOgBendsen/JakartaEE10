package dk.lundogbendsen.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class StockTxProcessor implements ItemProcessor
{
  private final static SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");
  static
  {
    format.setLenient(false);
  }

  private final static Random ran = new Random();

  @Override
  public StockTransaction processItem(final Object t)
  {
    String line = (String) t;

    String[] tokens = line.split("[\\s]*;[\\s]*");

    String sku = tokens[0];
    String name = tokens[1];
    int units = Integer.parseInt(tokens[2]);

    Date date;

    try
    {
      String d = tokens[3];
      date = format.parse(d);
    }
    catch (ParseException e)
    {
      System.out.println("could not parse date from: " + tokens[3]);
      return null;
    }

    try
    {
      int ms = 3000 + ran.nextInt(7000);
      Thread.sleep(ms);
    }
    catch (InterruptedException e)
    {
    }

    StockTransaction st = new StockTransaction(sku, name, units, date);
    return st;
  }
}

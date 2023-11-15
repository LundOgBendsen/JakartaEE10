package dk.lundogbendsen.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;

@Named
@Dependent
public class StockTxReader extends AbstractItemReader
{

  private BufferedReader reader;

  @Override
  public void open(final Serializable checkpoint) throws Exception
  {
    reader = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/META-INF/SkiMasks4U.csv")));

    try
    {
      String header = reader.readLine();
      System.out.println("skip line: " + header);
    }
    catch (IOException ex)
    {
      Logger.getLogger(StockTxReader.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  @Override
  public String readItem()
  {
    try
    {
      String line = reader.readLine();
      System.out.println("read line: " + line);
      return line;
    }
    catch (IOException ex)
    {
      Logger.getLogger(StockTxReader.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }
}

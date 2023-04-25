/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netz.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.*;

/** Class to change the Loggerformat for Logger
 *
 * @author le
 */
public class OhmLogger 
{
  private static Logger lg = null;
  private static String log_LEVEL;
  private static String log_FILE;
  
  private OhmLogger()
  {
  }
  
  public static Logger getLogger()
  {
    if (lg == null)
    {
      lg = Logger.getLogger("OhmLogger");
      initLogger();
    }
    return lg;
  }
  
  private static void initLogger()  
  {
    try
    {
      Properties probs = new Properties();
      InputStream is = OhmLogger.class.getResourceAsStream("config" + File.separator + "logger.properties");
      
      probs.load(is);
      log_LEVEL = probs.getProperty("LOG_LEVEL");
      log_FILE = probs.getProperty("LOG_DATEI");
    }
    catch (IOException ex)
    {
      log_LEVEL = "all";
      log_FILE = "/tmp/log.txt";
    }
    
    try{
      FileHandler fh = new FileHandler( log_FILE );
      fh.setFormatter(new OhmFormatter());
      lg.addHandler(fh);
    }
    catch (IOException ex)
    {
      
    }
    
      // Einstellungen in Properties-Datei LOG_FILE="/tmp/log.txt"
      
      ConsoleHandler ch = new ConsoleHandler();
      ch.setFormatter(new OhmFormatter());
      lg.addHandler(ch);
      lg.setUseParentHandlers(false);
  }
}

class OhmFormatter extends SimpleFormatter
{
  @Override
  public String format(LogRecord record)
  {
    String logLine = "";
    LocalDateTime ldt = LocalDateTime.now();
    ldt.format(DateTimeFormatter.ISO_DATE);
    logLine += ldt.toString();
    logLine += " - " + record.getLevel().toString();
    logLine += " in " + record.getSourceClassName();
    logLine += " : " + record.getMessage();
    logLine += "\n";
    return logLine;
  }
}

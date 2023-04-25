/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeispielNetz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author le
 */
public class Start 
{
  private String hostUrl;
  private String dateiname;
  
  public Start(String hostUrl, String dateiname)
  {
    this.hostUrl = hostUrl;
    this.dateiname = dateiname;
  }
  
  public void kopiereDatei() throws MalformedURLException, IOException
  {
    URL oURL = new URL(hostUrl + "/" + dateiname);
    InputStream is = oURL.openStream();
    BufferedInputStream in = new BufferedInputStream(is);
    
    String tmpVerzeichnis = System.getProperty("java.io.tmpdir");
    String ausgabeDateiname = tmpVerzeichnis + File.separator + dateiname;
    
    FileOutputStream fos = new FileOutputStream(ausgabeDateiname);
    BufferedOutputStream out = new BufferedOutputStream(fos);
    
    int wert = 0;
    while ( (wert = in.read()) >= 0 )
    {
      out.write(wert);
    }
    in.close();
    out.close(); // out.flush()
    System.out.println("Datei erfolgreich geladen - siehe VZ: " + tmpVerzeichnis);
  }

  public static void main(String[] args) 
  {
    if (args.length != 2)
    {
      System.err.println("2 Parameter nötig: Host-URL und Dateiname");
    }
    else
    {
      Start mainApp = new Start(args[0], args[1]);
      try
      {
        mainApp.kopiereDatei();
      }
      catch (Exception ex)
      {
        System.err.println(ex);
      }
    }
  }
}

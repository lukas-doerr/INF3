/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BeispielNetz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.*;

/**
 *
 * @author le
 */
public class Client 
{
  private static final Logger lg = Logger.getLogger("netz");
  private static final int PORT = 35000;
  private static final String IP = "127.0.0.1";
  
  
  public Client() throws IOException
  {
    lg.info("Client: versuche mich mit Server zu verbinden...");
    
    Socket s = new Socket(IP, PORT);  // ACHTUNG: BLOCKIERT!!!
    lg.info("Client: Verbindung hergestellt");
    
    //byte!!!
    InputStream iStream = s.getInputStream();
    OutputStream oStream = s.getOutputStream();
    
    // Umwandlung von byte in Character (String)
    InputStreamReader isr = new InputStreamReader(iStream, "UTF-8");
    OutputStreamWriter osr = new OutputStreamWriter(oStream, "UTF-8");
    
    // Puffer
    BufferedReader in = new BufferedReader(isr);
    //BufferedWriter out = new BufferedWriter(osr);
    //bessere Möglichkeit
    PrintWriter out = new PrintWriter(osr);
    
    lg.info("Client: Streams initialisiert");
    
    out.println("Hallo Server Du - ich bin ein Client ...");
    out.flush(); // wichtig!!!
    lg.info("Client: Nachricht wurde versendet");
    
    lg.info("Client: warte jetzt auf Bestätigung");
    String nachricht = in.readLine(); // ACHTUNG -> BLOCKIERT!
    lg.info("Client: Bestätigung erhalten");
    
    System.out.println("Client: Die Bestätigung lautet: " + nachricht);
    
    in.close();
    out.close();
    
  }
  
  

  public static void main(String[] args) 
  {
    try
    {
      new Client();
    }
    catch (Exception ex)
    {
      lg.severe(ex.toString());
    }
  }

}

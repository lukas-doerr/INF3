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
public class Server 
{
  private static final Logger lg = Logger.getLogger("netz");
  private static final int PORT = 35000;
  
  
  public Server() throws IOException
  {
    ServerSocket sSocket = new ServerSocket(PORT);
    lg.info("Server: Warte auf Verbindung ...");
    Socket s = sSocket.accept(); // ACHTUNG -> BLOCKIERT!!!!
    lg.info("Server: Verbindung akzeptiert");
    
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
    
    lg.info("Server: Streams initialisiert");
    
    lg.info("Server: warte jetzt auf Nachricht");
    String nachricht = in.readLine(); // ACHTUNG -> BLOCKIERT!
    lg.info("Server: Nachricht erhalten");
    
    System.out.println("Server: Die NACHRICHT lautet: " + nachricht);
    
    // Achtung: Ausgabe blockiert NICHT!!!
    out.println("Server -> lieber Client ... ich habe Deine Nachricht erhalten");
    
    lg.info("Server: Quittung versendet");
    
    // WICHTIG!!
    out.flush();
    
    in.close();
    out.close();
    
  }
  
  

  public static void main(String[] args) 
  {
    try
    {
      new Server();
    }
    catch (Exception ex)
    {
      lg.severe(ex.toString());
    }
  }

}

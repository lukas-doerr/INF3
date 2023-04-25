/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Logger;
import netz.util.OhmLogger;

/**
 *
 * @author LiQui
 */
public class Transceiver implements Runnable
{

  // Loggervariablen
  private static final Logger lg = OhmLogger.getLogger();

  // Verbindungseinstellungen
  private volatile boolean serverMode;
  private volatile boolean connect;
  
  

  // Verbindungsvariablen
  private Socket socket;
  private final Object connectionLOCK;
  private InputStream is;
  private ObjectInputStream isr;
  //private BufferedReader in;
  private Object inputMessage;
  private OutputStream os;
  private ObjectOutputStream osr;
  //private PrintWriter out;
  
  
  private volatile UserData usrData;

  // Threadvariablen
  private final Object threadLOCK;
  private ExecutorService eService;
  private volatile boolean running;

  private SubmissionPublisher<UserData> iPublisher;
  public Transceiver() throws IOException
  {
    // Connection
    socket = new Socket();
    connectionLOCK = new Object();
    serverMode = false;
    //
    // Thread
    threadLOCK = new Object();
    running = false;
    eService = Executors.newSingleThreadExecutor();
    connect = false;
    usrData = new UserData("127.0.0.1", 35000);
    usrData.isConnected = false;
    iPublisher = new SubmissionPublisher<>();
  }
  
  public void start()
  {
    synchronized (threadLOCK)
    {
      connect = true;
      eService.execute(this);
    }
  }
  
  public void stop()
  {
    synchronized (threadLOCK)
    {
      connect = false;
    }
  }


  @Override
  public void run()
  {
    while (true)
    {
      while(connect)
      {
        while(!usrData.isConnected)
        {
          connect();      
        }
        
        try
        {
        transceive();
        }
        catch (IOException ex)
        {
          System.err.println(ex);
         lg.info(ex.toString());
        }
      }
    }
  }

  public void addValueSubscription(Subscriber<UserData> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }

  public void sendMessage(Object outputMessage) throws IOException
  {
    if (!socket.isClosed())
    {
      osr.writeObject(outputMessage);
      osr.flush();
      if (outputMessage != null)
      {
        lg.info("Nachricht gesendet: " + outputMessage);
      }
    }
  }
  

  public void setServerMode(boolean serverMode)
  {
    synchronized (connectionLOCK)
    {
      this.serverMode = serverMode;
    }
  }

  public void setUser(String user)
  {
      usrData.setUser(user);
  }
  public void setIP(String ip)
  {
    synchronized (connectionLOCK)
    {
      usrData.setIP(ip);
    }
  }

  public void setPort(int port)
  {
    synchronized (connectionLOCK)
    {
      usrData.setPort(port);
    }
  }

  public void transceive() throws IOException
  {
    if (!socket.isClosed())
    {
      try
      {
        lg.info("Starting Read Line");
        inputMessage = isr.readObject();
        lg.info("Stopping Read Line Msg:" + inputMessage.toString());
        
        usrData.setMessage(inputMessage);        
        iPublisher.submit(usrData);
      }
      catch (Exception ex)
      {
          lg.info("Disconnect hier ");
          disconnect();
      }
    }
  }

  public void connect()
  {
    try
    {
      synchronized (connectionLOCK)
      {
        if (serverMode)
        {
          
          ServerSocket sSocket = new ServerSocket(usrData.getPort());
          lg.info("Transceiver: Server wartet auf Verbindung mit Client");
          
          socket = sSocket.accept();
          lg.info("Transceiver: Server hat die Verbindung mit Client aufgebaut");
          sSocket.close();
        }
        else
        {
          lg.info("Transceiver: Client wartet auf Verbindung mit Server");
          socket = new Socket(usrData.getIP(), usrData.getPort());
          lg.info("Transceiver: Client hat die Verbindung mit Server aufgebaut");
        }

        os = socket.getOutputStream();
        osr = new ObjectOutputStream(os);
        //out = new PrintWriter(osr);

        is = socket.getInputStream();
        isr = new ObjectInputStream(is);
        //in = new BufferedReader(isr);

        usrData.isConnected = true;
      }
    }
    catch (IOException ex)
    {
      lg.info(ex.toString() + ": Connect");
      System.err.println(ex);
    }
  }

  public void disconnect()
  {
    if (usrData.isConnected)
    {
      lg.info("Transceiver: Disconnect");
      try
      {
        socket.close();
        lg.info("Socket Closed");
        usrData.isConnected = false;
        iPublisher.submit(usrData);
      }
      catch (IOException ex)
      {
          
        System.err.println(ex);
      }
    }
    stop();
  }

  public boolean isConnected()
  {
    return connect;
  }

  public boolean isRunning()
  {
    return running;
  }

  public boolean isServerMode()
  {
    return serverMode;
  }

}

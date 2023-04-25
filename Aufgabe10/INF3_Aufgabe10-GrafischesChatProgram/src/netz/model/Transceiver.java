/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
  private volatile String ip;
  private volatile int port;
  private volatile boolean isConnected;

  // Verbindungsvariablen
  private Socket socket;
  private final Object connectionLOCK;
  private InputStream is;
  private InputStreamReader isr;
  private BufferedReader in;
  private String inputMessage;
  private OutputStream os;
  private OutputStreamWriter osr;
  private PrintWriter out;

  // Threadvariablen
  private final Object threadLOCK;
  private ExecutorService eService;
  private volatile boolean running;

  private SubmissionPublisher<String> iPublisher;
  private SubmissionPublisher<Boolean> bPublisher;
  public Transceiver() throws IOException
  {
    // Connection
    socket = new Socket();
    connectionLOCK = new Object();
    serverMode = false;
    ip = "127.0.0.1";
    port = 35000;
    //
    // Thread
    threadLOCK = new Object();
    running = false;
    eService = Executors.newSingleThreadExecutor();

    bPublisher = new SubmissionPublisher<>();
    iPublisher = new SubmissionPublisher<>();
  }

  public void start()
  {
    synchronized (threadLOCK)
    {
      lg.info("Transceiver: Thread Start");
      running = true;
      threadLOCK.notifyAll();
    }
    eService.execute(this);
  }

  private void stop()
  {
    synchronized (threadLOCK)
    {
      lg.info("Transceiver: Request Thread Stop");
      running = false;
    }
  }

  @Override
  public void run()
  {
    while (true)
    {
      while (!running)
      {
        synchronized (threadLOCK)
        {
          try
          {
            lg.info("Transceiver: Thread Stop");
            threadLOCK.wait();

          }
          catch (InterruptedException ex)
          {
            System.err.println(ex);
          }
        }
      }
      try
      {
        if(isConnected){
          transceive();
        }else{
          connect();
        }
      }
      catch (IOException ex)
      {
        System.err.println(ex);
        lg.info(ex.toString());
      }
    }
  }

  public void addValueSubscription(Subscriber<String> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }

  public void sendMessage(String outputMessage)
  {
    if (!socket.isClosed())
    {
      out.println(outputMessage);
      out.flush();
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

  public void setIP(String ip)
  {
    synchronized (connectionLOCK)
    {
      this.ip = ip;
    }
  }

  public void setPort(int port)
  {
    synchronized (connectionLOCK)
    {
      this.port = port;
    }
  }

  public void transceive() throws IOException
  {
    if (!socket.isClosed())
    {
      try
      {
        lg.info("Starting Read Line");
        inputMessage = in.readLine();
        lg.info("Stopping Read Line Msg:" + inputMessage);
        if (inputMessage != null)
        {
          lg.info("Nachricht empfangen: " + inputMessage);
          iPublisher.submit(inputMessage);
        }
        else
        {
          lg.info("Disconnect hier ");
          disconnect();
        }
      }
      catch (Exception ex)
      {
        lg.info(ex.toString());
        System.err.println(ex);
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
          
          ServerSocket sSocket = new ServerSocket(port);
          lg.info("Transceiver: Server wartet auf Verbindung mit Client");
          
          socket = sSocket.accept();
          lg.info("Transceiver: Server hat die Verbindung mit Client aufgebaut");
         sSocket.close();
        }
        else
        {
          lg.info("Transceiver: Client wartet auf Verbindung mit Server");
          socket = new Socket(ip, port);
          lg.info("Transceiver: Client hat die Verbindung mit Server aufgebaut");
        }

        os = socket.getOutputStream();
        osr = new OutputStreamWriter(os, "UTF-8");
        out = new PrintWriter(osr);

        is = socket.getInputStream();
        isr = new InputStreamReader(is, "UTF-8");
        in = new BufferedReader(isr);

        isConnected = true;
        start();
        bPublisher.submit(isConnected);
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
    if (isConnected())
    {
      lg.info("Transceiver: Disconnect");
      try
      {
        out.println();
        out.flush();
        socket.close();
        lg.info("Socket Closed");
        isConnected = false;
      }
      catch (IOException ex)
      {
        System.err.println(ex);
      }
    }
    stop();
    bPublisher.submit(isConnected);
  }

  public boolean isConnected()
  {
    return isConnected;
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

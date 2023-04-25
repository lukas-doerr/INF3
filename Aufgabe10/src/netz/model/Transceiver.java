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
import java.util.logging.Logger;

/**
 *
 * @author LiQui
 */
public class Transceiver implements Runnable
{

    // Loggervariablen
    private static final Logger lg = Logger.getLogger("netz");

    // Verbindungseinstellungen
    private volatile boolean serverMode;
    private volatile String ip;
    private volatile int port;

    private final String discString = "Disconnect";

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

    public Transceiver()
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

    }

    private void start()
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
                        threadLOCK.wait();
                        lg.info("Transceiver: Thread Stop");
                    } catch (InterruptedException ex)
                    {
                        System.err.println(ex);
                    }
                }
            }
            try
            {
                if (socket.isConnected())
                {
                    transceive();
                } else
                {
                    stop();
                }
            } catch (IOException ex)
            {
                System.err.println(ex);
            }
        }
    }

    public void sendMessage(String outputMessage)
    {
        if (socket.isConnected())
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
        os = socket.getOutputStream();
        osr = new OutputStreamWriter(os, "UTF-8");
        out = new PrintWriter(osr);
        is = socket.getInputStream();
        isr = new InputStreamReader(is, "UTF-8");
        in = new BufferedReader(isr);
        inputMessage = in.readLine();
        if (inputMessage != null)
        {
            lg.info("Nachricht empfangen: " + inputMessage);
        }
        if (inputMessage.equals(discString))
        {
            lg.info("Das Ding wird ausgeführt!");
            disconnectWithMessage();
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
                } else
                {
                    lg.info("Transceiver: Client wartet auf Verbindung mit Server");
                    socket = new Socket(ip, port);
                    lg.info("Transceiver: Client hat die Verbindung mit Server aufgebaut");
                }

            }
        } catch (IOException ex)
        {
            System.err.println("ex");
        }
        if (socket.isConnected())
        {
            start();
        }
    }

    public void disconnect()
    {
        if (socket.isConnected())
        {
            lg.info("Transceiver: Disconnect by user");
            stop();
            sendMessage(discString);
            closeAll();
        }
    }

    private void disconnectWithMessage()
    {
        lg.info("Transceiver: Disconnect with Message");
        stop();
        sendMessage("Disconnect Bestätigt!");
        closeAll();
    }

    private void closeAll()
    {
        lg.info("Transceiver: Closed all!");
        try
        {
            in.close();
            isr.close();
            is.close();
            out.close();
            osr.close();
            os.close();
            if (!socket.isClosed())
            {
                socket.close();
            }
        } catch (IOException ex)
        {
            System.err.println("ex");
        }
    }

    public boolean isConnected(){
        return socket.isConnected();
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

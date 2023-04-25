/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz.model;

import java.io.IOException;
import java.util.concurrent.Flow.Subscriber;

/**
 *
 * @author Carmin Kern
 */
public class Model
{

    private Transceiver transceiver;
    
    public Model() throws IOException
    {
        transceiver = new Transceiver();
    }

    public void connect()
    {
        transceiver.start();
    }
    
    public void disconnect()
    {
        transceiver.disconnect();
    }

    public void Send(String Message)
    {
        transceiver.sendMessage(Message);
    }
    public boolean isConnected(){
        return transceiver.isConnected();
    }
    public boolean isServerMode(){
        return transceiver.isServerMode();
    }
    
    public void setIP( String ip){
        transceiver.setIP(ip);
    }
    public void setPort( int port){
        transceiver.setPort(port);
    }
    public void setServerMode(boolean serverMode)
    {
        transceiver.setServerMode(serverMode);
    }
    
    public void doSubscribe(Subscriber<String> subscriber)
    {
        transceiver.addValueSubscription(subscriber);
    }
}

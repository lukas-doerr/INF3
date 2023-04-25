/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz.model;

import java.io.IOException;

/**
 *
 * @author Carmin Kern
 */
public class Model
{

    private Transceiver transceiver;
    
    public Model()
    {
        transceiver = new Transceiver();
    }

    public void connect()
    {
        transceiver.connect();
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
}

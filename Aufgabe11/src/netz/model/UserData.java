/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.model;

/**
 *
 * @author lukas
 */
public class UserData {
    
    private String ip;
    private int port;
    private Object message;
    private String user;
    public boolean isConnected;
    
    public UserData(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }
    
    public String getIP()
    {
        return ip;
    }
    
    public void setPort(int port)
    {
        this.port = port;
    }
    
    public int getPort()
    {
        return port;
    }
    
    public void setIP(String ip)
    {
        this.ip = ip;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public Object getMessage()
    {
        return message;
    }
    
    public void setMessage(Object message)
    {
        this.message = message;
    }
}

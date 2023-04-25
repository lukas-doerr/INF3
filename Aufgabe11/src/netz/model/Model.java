/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz.model;

import java.awt.Color;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;

/**
 *
 * @author Carmin Kern
 */
public class Model
{
    private final GrafikData gData;
    private final Transceiver transceiver;
    
    public Model() throws IOException
    {
        transceiver = new Transceiver();
        gData = new GrafikData();
    }

    //Connection
    public void connect()
    {
        transceiver.start();
    }
    
    public void disconnect()
    {
        transceiver.disconnect();
    }

    public void send(Object Message) throws IOException
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
    
    public void setUser(String user)
    {
        transceiver.setUser(user);
    }
    
    public void setServerMode(boolean serverMode)
    {
        transceiver.setServerMode(serverMode);
    }
    
    public void doSubscribe(Subscriber<UserData> subscriber)
    {
        transceiver.addValueSubscription(subscriber);
    }
    
    
    
    //Grafik 
    public void deleteContent() {
        gData.deleteContent();
    }

    public Color getColor() {
        return gData.getColor();
    }

    public void setColor(Color c) {
        gData.setColor(c);
    }

    public int getStroke() {
        return gData.getStroke();
    }

    public void setStroke(int t) {
        gData.setStroke(t);
    }

    public void addFigure(int stroke) {
        gData.addFigure(stroke);
    }
    
    public void addFigure(Figure stroke) {
        gData.addFigure(stroke);
    }

    public void addPoint(Point p) {
        gData.addPoint(p);
    }

    public Figure getCurrentFigure() {
        return gData.getCurrentFigure();
    }

    public List<Figure> getFigures() {
        return gData.getFigures();
    }

    public void savePoints(String filename) throws FileNotFoundException, IOException {
        gData.savePoints(filename);
    }

    public void loadPoints(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        gData.loadPoints(filename);
    }
    
    
}

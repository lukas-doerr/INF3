/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counter;

import counter.controller.CounterSubscriber;
import counter.model.Model;
import counter.view.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JComponent;

/**
 *
 * @author doerrlu76113
 */
public class Counter extends JComponent {



    private final View view;
    private final Model model;
    private final CounterSubscriber subscriber;
    
    private CopyOnWriteArrayList<CounterListener> listenerList;

    
    public Counter() {
        view = new View();
        model = new Model();
        
        subscriber = new CounterSubscriber(model, view, this);
        
        this.setLayout(new BorderLayout());
        this.add(view);

        listenerList = new CopyOnWriteArrayList<>();
        
    }


    
    // Listener
    public void addCounterListener(CounterListener listener) {
        listenerList.add(listener);
    }

    public void removeCounterListener(CounterListener listener) {
        listenerList.remove(listener);
    }

    public void fireCounterListener(CounterEvent event) {
        listenerList.forEach(listener -> listener.CounterDone(event));
    }
 
    // Properties
    public void setCurrentValue(int newCurrentValue){
        model.setCurrentValue(newCurrentValue);
    }
    public int getCurrentValue(){
        
        return model.getCurrentValue();
    }
    public void setStartValue(int newStartValue) {
        model.setStartValue(newStartValue);
    }
    public int getStartValue() {
        return model.getStartValue();
    }
    public void setEndValue(int newEndValue) {
        model.setEndValue(newEndValue);
    }
    public int getEndValue() {
        return model.getEndValue();
    }
    public void setTimerDelay(int newTimerDelay) {
        model.setTimerDelay(newTimerDelay);
    }
    public int getTimerDelay() {
        return model.getTimerDelay();
    }
    public void setColor(Color newColor) {
        view.setColor(newColor);
    }
    public Color getColor() {
        return view.getColor();
    }
    // Methoden
    public void startCounter() {
        model.start();
    }

    public void stopCounter() {
        model.stop();
    }

    public void resetCounter() {
        model.reset();
    }
    
    // Viewzeugs 
    @Override
    public void paintComponent(Graphics g) {
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width = 100;
        size.height = 100;
        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        Dimension size = super.getMinimumSize();
        size.width = 50;
        size.height = 50;
        return size;
    }

}

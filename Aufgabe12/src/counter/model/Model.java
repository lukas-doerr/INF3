/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counter.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author doerrlu76113
 */
public class Model implements Runnable {

    private final ExecutorService eService;
    private volatile boolean running;

    private final SubmissionPublisher<Value> iPublisher;

    private final Value value;
    private Integer timerDelay;

    public Model() {
        running = false;
        eService = Executors.newSingleThreadExecutor();
        eService.execute(this);
        iPublisher = new SubmissionPublisher<>();
        value = new Value();
        value.setStart(10);
        value.setEnd(0);
        value.reset();
        iPublisher.submit(value);
        timerDelay = 1000;
    }

    public void start() {
        if (!running) {
            if(value.isEnd()){
                value.reset();
            }
            synchronized (this) {
                running = true;
                iPublisher.submit(value);
            }
            
        }
    }

    public void stop() {
        if (running) {
            synchronized (this) {
                running = false;
            }
        }
    }

    public void reset() {
        value.reset();
        iPublisher.submit(value);
    }

    @Override
    public void run() {
        while (true) {
            while (running) {
                try {
                    Thread.sleep(timerDelay);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                value.next();
                iPublisher.submit(value);
                if (value.isEnd()) {
                    stop();
                }
            }
        }
    }
    public Integer getPreviousValue(){
        return value.getPrevious();
    }
    public void setCurrentValue(Integer newCurrentValue){
        value.setCurrent(newCurrentValue);
    }
    public Integer getCurrentValue(){
        return value.getCurrent();
    }

    public void setStartValue(Integer newStartValue) {
        value.setStart(newStartValue);
    }

    public Integer getStartValue() {
        return value.getStart();
    }

    public void setEndValue(Integer newEndValue) {
        value.setEnd(newEndValue);
    }

    public Integer getEndValue() {
        return value.getEnd();
    }

    public void setTimerDelay(Integer newTimerDelay) {
        timerDelay = newTimerDelay;
    }

    public Integer getTimerDelay() {
        return timerDelay;
    }

    public void addSubscriber(Subscriber<Value> subscriber) {
        iPublisher.subscribe(subscriber);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package synchronize.model;


import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Level;
import java.util.logging.Logger;
import synchronize.util.OhmLogger;
/**
 *
 * @author lukas
 */
public class BanditModel implements Subscriber<int[]>
{
    private static Logger lg = OhmLogger.getLogger();
    private SubmissionPublisher<int[]> iPublisher;
    
    private Counter model0;
    private Counter model1;
    private Counter model2;
    
    private int[] data;
    
    private Subscription subscription0;
    private Subscription subscription1;
    private Subscription subscription2;
    
    public BanditModel() 
    {
        model0 = new Counter();
        model1 = new Counter();
        model2 = new Counter();
        iPublisher = new SubmissionPublisher<>();
        data = new int[]{0,0,0};
    }
    
    public void start() 
    {
        model0.start();
        model1.start();
        model2.start();
    }
    
    public void stop()
    {
        model0.stop();
        model1.stop();
        model2.stop();
    }
    
    public void addValueSubscriptior(Subscriber<int[]> subscriber)
    {
        iPublisher.subscribe(subscriber);
    }
    
    public void doSubscribe()
    {
        model0.addValueSubscriptior(this);
        model1.addValueSubscriptior(this);
        model2.addValueSubscriptior(this);
        lg.info("Subscribed");
    }

    @Override
    public void onSubscribe(Subscription s) 
    {    
        //this.subscription0 = s;
        lg.info("Requestet");
        
        
        if (this.subscription0 == null) 
        {
            this.subscription0 = s;
            lg.log(Level.INFO, "Subscription 0");
        } 
        else if (this.subscription1 == null)
        {
            this.subscription1 = s;
            lg.log(Level.INFO, "Subscription 1");
        }
        else
        {
            this.subscription2 = s;
            lg.log(Level.INFO, "Subscription 2");
        }
        s.request(1);
    }

    @Override
    public void onNext(int[] t) 
    {
        if(model0.id == t[0]) 
        {
            data[0] = t[1];
            lg.log(Level.INFO, "StringValue0:" + data[0] );
            subscription1.request(1);
        }
        
        if (model1.id == t[0])
        {
            data[1] = t[1];
            lg.log(Level.INFO, "StringValue1:" + data[1] );
            subscription2.request(1);
        }
        
        if (model2.id == t[0])
        {
            data[2] = t[1];
            lg.log(Level.INFO, "StringValue2:" + data[2] );
            subscription0.request(1);
        }
        iPublisher.submit(data);
    }

    @Override
    public void onError(Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

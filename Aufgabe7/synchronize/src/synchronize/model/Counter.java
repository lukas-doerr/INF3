/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package synchronize.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Logger;
import synchronize.util.OhmLogger;

/**
 *
 * @author le
 */
public class Counter implements Runnable // Callable
{
  //Singleton:
  private static Logger lg = OhmLogger.getLogger();
  
  private final Object LOCK;
  private volatile int counter;
  private volatile int value;  //private AtomicInteger value;
  /** Zustandsvariable -> bei Nebenläufigkeit immer nötig 
      boolean für 2 Werte - bei mehreren Werten: enum
   */
  private volatile boolean running; 
  private SubmissionPublisher<int[]> iPublisher;
  //private Thread thd;
  private ExecutorService eService;
  
  public int id;
  
  public Counter()
  {
    counter = 1;
    value = 0;
    id = (int) (1 + 499*Math.random());
    running = false;
    iPublisher = new SubmissionPublisher<>();
    eService = Executors.newSingleThreadExecutor();
    LOCK = new Object();
  }
  
  public void addValueSubscriptior(Subscriber<int[]> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
  public void start()
  {
    synchronized(LOCK)
    {
      lg.info("Start");
      running = true;
      LOCK.notifyAll();
    }
    eService.execute(this); // submit(this) liefert Future Object
  }
  
  public void stop()
  {
    synchronized(LOCK)
    {
      running = false;
    }
  }

  public int getValue()
  {
    synchronized(this)
    {
      lg.info("Value: " + value);
      return value;
    }
  }

  @Override
  public void run()
  {
    int[] data;  
    while (true)
    {
      while (!running) // nicht if verwenden!
      {
        synchronized(LOCK)
        {
          try
          {
            LOCK.wait();
          }
          catch (InterruptedException ex)
          {
            System.err.println(ex);
          }
        }
      }  // end of while 
      
     
      try
      {
        Thread.sleep(50);
      }
      catch (InterruptedException ex)
      {
        System.err.println(ex);
      }
      counter++;
      synchronized(this)
      {
        value = (int)(1 + 8*Math.random());
      }
      // Subscriber benachrichtigen und Wert mitsenden (via Event)
      data = new int[]{id, value};
      lg.info(String.valueOf(id));
      iPublisher.submit(data);  
    }
  }
}


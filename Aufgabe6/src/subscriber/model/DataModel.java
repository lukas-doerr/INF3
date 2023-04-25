/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package subscriber.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * @author le
 */
public class DataModel implements Runnable // Callable
{
  private int value;
  /** Zustandsvariable -> bei Nebenläufigkeit immer nötig 
      boolean für 2 Werte - bei mehreren Werten: enum
   */
  private boolean running, show; 
  private SubmissionPublisher<Integer> iPublisher;
  //private Thread thd;
  private ExecutorService eService;
  
  public DataModel()
  {
    value = 1;
    running = false;
    show = false;
    iPublisher = new SubmissionPublisher<>();
    eService = Executors.newSingleThreadExecutor();
  }
  
  public void addValueSubscriptior(Subscriber<Integer> subscriber)
  {
    iPublisher.subscribe(subscriber);
  }
  
  public void start()
  {
    running = true;
    show = true;
    eService.execute(this); // submit(this) liefert Future Object
  }
  
  public void stop()
  {
    show = false;
  }
  

  @Override
  public void run()
  {
    while (true)
    {
      try
      {
        Thread.sleep(20);
      }
      catch (InterruptedException ex)
      {
        System.err.println(ex);
      }
      //value++;
      value = (int)(1 + 6*Math.random());
      // Subscriber benachrichtigen und Wert mitsenden (via Event)
      if(show)
      {
        iPublisher.submit(value);  
      }
    }
  }
}

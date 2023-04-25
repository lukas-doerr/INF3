/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package synchronize.controller;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.logging.Logger;
import synchronize.model.BanditModel;
import synchronize.util.OhmLogger;
import synchronize.view.MainView;

/**
 *
 * @author le
 */
public class ValueAdapter implements Subscriber<int[]>
{
  private static Logger lg = OhmLogger.getLogger();  
  
  private String strValue0;
  private String strValue1;
  private String strValue2;
  
  private MainView view;
  private BanditModel model;
  private Subscription subscription;
  
  public ValueAdapter(MainView view, BanditModel model)
  {
    this.view = view;
    this.model = model;
    model.doSubscribe();
  }
  
  public void doSubscribe()
  {
    model.addValueSubscriptior(this);
  }

  @Override
  public void onSubscribe(Flow.Subscription subscription)
  {
    lg.info("Subscribe");
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public void onNext(int[] item)
  {
    strValue0 = String.valueOf(item[0]);
    view.getLblCounter0().setText(strValue0);
    strValue1 = String.valueOf(item[1]);
    view.getLblCounter1().setText(strValue1);
    strValue2 = String.valueOf(item[2]);
    view.getLblCounter2().setText(strValue2);
    subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable)
  {
    System.err.println(throwable);
  }

  @Override
  public void onComplete()
  {
    System.err.println("FERTIG");
  }
}

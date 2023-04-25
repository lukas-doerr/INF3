/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counter.controller;

import counter.Counter;
import counter.CounterEvent;
import counter.model.Model;
import counter.model.Value;
import counter.view.View;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 *
 * @author LiQui
 */
public final class CounterSubscriber implements Subscriber<Value>{
    private final Model model;
    private final View view;
    private final Counter counter;


    private Subscription subscription;
    
    public CounterSubscriber(Model model,View view, Counter counter){
        this.model = model;
        this.view = view;
        this.counter = counter;
        doSubscribe();
    }
    
    public void doSubscribe(){
        model.addSubscriber(this);
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        System.err.println("onSubscribe");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Value value) {
        System.err.println("onNext");
        view.setNumber(value);
        counter.firePropertyChange("CurrentValue", value.getPrevious(), value.getCurrent());
        if(value.isEnd()){
            System.err.println("counter.fire event");
            counter.fireCounterListener(new CounterEvent(this));
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable);
    }

    @Override
    public void onComplete() {
        System.err.println("onComplete");
    }
    
}

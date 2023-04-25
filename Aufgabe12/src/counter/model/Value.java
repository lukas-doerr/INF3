/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package counter.model;

import java.util.Objects;

/**
 *
 * @author LiQui
 */
public class Value {
    private Integer previousValue;
    private Integer currentValue;
    private Integer startValue;
    private Integer endValue;
    
    public Value(){
        previousValue=0;
        currentValue=0;
        startValue=0;
        endValue=0;
    }
    public Integer is(){
        return currentValue;
    }
    public void next(){
        previousValue = currentValue;
        if( startValue<endValue){
            if(!isEnd()){
                currentValue++;
            }
        }else{
            if(!isEnd()){
                currentValue--;
            }
        }
    }
    public void reset(){
        previousValue = currentValue;
        currentValue=startValue;
    }
    public Integer getPrevious(){
        return previousValue;
    }
    public void setCurrent(Integer newCurrentValue){
        currentValue = newCurrentValue;
    }
    public Integer getCurrent(){
        return currentValue;
    }
    public void setStart(Integer newStartValue){
        this.startValue = newStartValue;
    }
    public Integer getStart(){
        return startValue;
    }
    public void setEnd(Integer newEndValue){
        this.endValue = newEndValue;
    }
    public Integer getEnd(){
        return endValue;
    }
    public boolean isEnd(){
        if( currentValue == endValue){
            return true;
        }else{
            return false;
        }
    }
}

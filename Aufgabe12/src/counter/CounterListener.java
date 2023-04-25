/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package counter;

import java.util.EventListener;

/**
 *
 * @author doerrlu76113
 */
public interface CounterListener extends EventListener
{
  public void CounterDone(CounterEvent evt);
}
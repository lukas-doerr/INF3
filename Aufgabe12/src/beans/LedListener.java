/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.EventListener;

/**
 *
 * @author le
 */
public interface LedListener extends EventListener
{
  public void ledStateChanged(LedEvent evt);
}

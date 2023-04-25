/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.EventObject;

/**
 *
 * @author le
 */
public class LedEvent extends EventObject
{
  public LedEvent(Object source)
  {
    super(source);
  }
}

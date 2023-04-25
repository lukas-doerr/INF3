/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netz.controller.commands;

import java.util.HashMap;

/**
 *
 * @author le
 */
public class CommandInvoker 
{
  private HashMap<Object, CommandInterface> commands;
  
  public CommandInvoker()
  {
    commands = new HashMap<>();
  }
  
  public void addCommand(Object key, CommandInterface value)
  {
    commands.put(key, value);
  }
  
  public void executeCommand(Object key)
  {
    commands.get(key).execute();
  }
}

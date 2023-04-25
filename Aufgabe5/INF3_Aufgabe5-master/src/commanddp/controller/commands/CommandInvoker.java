/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commanddp.controller.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author le
 */
public class CommandInvoker 
{
  private HashMap<Object, CommandInterface> commands;
  private Stack<CommandInterface> undoStack;
  
  
  public CommandInvoker()
  {
    commands = new HashMap<>();
    undoStack = new Stack<>();
    
  }
  
  public void addCommand(Object key, CommandInterface value)
  {
    commands.put(key, value);
  }
  
  public void executeCommand(Object key)
  {
    commands.get(key).execute();
    if (commands.get(key).isUndoable())
    {
      CommandInterface cmd = commands.get(key);
      undoStack.push(cmd);
    }
  }
  
  public void undoCommand()
  {
    if (!undoStack.empty())
    {
      undoStack.pop().undo();
    }
  }
  
}

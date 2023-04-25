/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller.commands;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author doerr
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

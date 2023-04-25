/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcgrafik.controller.commands;

/**
 *
 * @author le
 */
public interface CommandInterface
{
  public void execute();
  public void undo();
  public boolean isUndoable();
  //public void redo();
}

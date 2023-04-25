/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mvcgrafik.controller.commands;

import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author doerr
 */
public class CommandPrint implements CommandInterface
{
  private GrafikView view;
  private GrafikModel model;
  
  public CommandPrint(GrafikView view, GrafikModel model)
  {
    this.view = view;
    this.model = model;
  }

  @Override
  public void execute()
  {
    view.doPrint();
  }

  @Override
  public void undo()
  {

  }

  @Override
  public boolean isUndoable()
  {
    return false;
  }
}

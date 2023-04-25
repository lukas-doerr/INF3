/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commanddp.controller;

import commanddp.controller.commands.CommandInvoker;
import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author le
 */
public class UndoController implements ActionListener
{
  private EditorView view;
  private EditorModel model;
  private CommandInvoker invoker;
  
  public UndoController(EditorView view, EditorModel model, CommandInvoker invoker)
  {
    this.view = view;
    this.model = model;
    this.invoker = invoker;
  }
  
  public void registerEvents()
  {
    view.getBtnUndo().addActionListener(this);
    view.getMenuUndo().addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    invoker.undoCommand();
  }
}

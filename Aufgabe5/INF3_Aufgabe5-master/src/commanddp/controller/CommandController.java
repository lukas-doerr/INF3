/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commanddp.controller;

import commanddp.controller.commands.CommandInvoker;
import commanddp.controller.commands.CommandOpen;
import commanddp.controller.commands.CommandSave;
import commanddp.controller.commands.CommandAdd;
import commanddp.controller.commands.CommandDelete;
import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author le
 */
public class CommandController implements ActionListener
{
  private EditorView view;
  private EditorModel model;
  private CommandInvoker invoker;
  
  public CommandController(EditorView view, EditorModel model, CommandInvoker invoker)
  {
    this.view = view;
    this.model = model;
    this.invoker = invoker;
  }
  
  public void registerEvents()
  {
    view.getBtnOpen().addActionListener(this);
    view.getMenuOpen().addActionListener(this);

    view.getBtnSave().addActionListener(this);
    view.getMenuSave().addActionListener(this);
    
    view.getBtnAdd().addActionListener(this);
    view.getMenuAdd().addActionListener(this);
    view.getPopAdd().addActionListener(this);
    
    view.getBtnDelete().addActionListener(this);
    view.getMenuDelete().addActionListener(this);
    view.getPopDelete().addActionListener(this);
  }
  
  public void registerCommands()
  {
    CommandOpen cmdOpen = new CommandOpen(view, model);
    CommandSave cmdSave = new CommandSave(view, model);
    CommandAdd cmdAdd = new CommandAdd(view, model);
    CommandDelete cmdDelete = new CommandDelete(view, model);
    
    invoker.addCommand(view.getMenuOpen(), cmdOpen);
    invoker.addCommand(view.getBtnOpen(), cmdOpen);

    invoker.addCommand(view.getMenuSave(), cmdSave);
    invoker.addCommand(view.getBtnSave(), cmdSave);
    
    invoker.addCommand(view.getMenuAdd(), cmdAdd);
    invoker.addCommand(view.getBtnAdd(), cmdAdd);
    invoker.addCommand(view.getPopAdd(), cmdAdd);
    
    invoker.addCommand(view.getMenuDelete(), cmdDelete);
    invoker.addCommand(view.getBtnDelete(), cmdDelete);
    invoker.addCommand(view.getPopDelete(), cmdDelete);
  }

  /**
   * Polymorphismus!!!! zur Entscheidung welche Aktion durchgeführt wird
   * @param evt evt.getSource liefert Eventquelle als key für Hashmap im Invoker
   */
  @Override
  public void actionPerformed(ActionEvent evt)
  {
    Object key = evt.getSource();
    invoker.executeCommand(key);
  }
}

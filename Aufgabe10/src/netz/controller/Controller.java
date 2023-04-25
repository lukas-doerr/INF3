/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netz.controller;
import netz.model.Model;
import netz.view.View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import netz.controller.commands.CommandInvoker;
import netz.controller.commands.CommandConnect;
import netz.controller.commands.CommandSend;
import netz.controller.commands.CommandServerMode;


/**
 *
 * @author Carmin Kern
 */
public class Controller implements ActionListener
{
  private View view;
  private Model model;
  private CommandInvoker invoker;
  
  public Controller(View view, Model model, CommandInvoker invoker)
  {
    this.view = view;
    this.model = model;
    this.invoker = invoker;
  }
  
  public void registerEvents()
  {
    view.getBtnConnect().addActionListener(this);
    view.getBtnSend().addActionListener(this);
    view.getBtnServerMode().addActionListener(this);
  }
  
  public void registerCommands()
  {
    CommandConnect cmdConnect = new CommandConnect(view, model);
    CommandSend cmdSend = new CommandSend(view,model);
    CommandServerMode cmdServerMode = new CommandServerMode(view, model);
    
    invoker.addCommand(view.getBtnConnect(), cmdConnect);
    invoker.addCommand(view.getBtnSend(), cmdSend);
    invoker.addCommand(view.getBtnServerMode(), cmdServerMode);
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

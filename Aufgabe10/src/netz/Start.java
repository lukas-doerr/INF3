/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package netz;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import netz.controller.Controller;
import netz.controller.commands.CommandInvoker;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author le
 * Die Startklasse realisiert das DP "Builder"
 * jetzt kommt ein zweiter Entwickler hinzu
 */
public class Start 
{
  public Start()
  {
    View view = new View();
    Model model = new Model();

    CommandInvoker invoker = new CommandInvoker();
    Controller ctrlCommand = new Controller(view, model, invoker);

    ctrlCommand.registerEvents();
    ctrlCommand.registerCommands();
       
    view.setVisible(true);
  }

  public static void main(String[] args) 
  {
    try    
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }    
    catch (Exception ex)    
    {
      JOptionPane.showMessageDialog(null, ex.toString());
    }
    new Start();
  }

}

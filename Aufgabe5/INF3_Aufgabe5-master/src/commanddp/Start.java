/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package commanddp;

import commanddp.controller.CommandController;
import commanddp.controller.UndoController;
import commanddp.controller.commands.CommandInvoker;
import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

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
    EditorView view = new EditorView();
    EditorModel model = new EditorModel();
    view.gettContent().setModel(model);

    CommandInvoker invoker = new CommandInvoker();
    CommandController ctrlCommand = new CommandController(view, model, invoker);
    UndoController ctrUndo = new UndoController(view, model, invoker);
    
    ctrlCommand.registerEvents();
    ctrlCommand.registerCommands();
    
    ctrUndo.registerEvents();
    
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

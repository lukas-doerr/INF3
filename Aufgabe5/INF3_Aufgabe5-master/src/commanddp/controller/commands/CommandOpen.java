/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddp.controller.commands;

import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Nutzt den Polymorphismus Implementiert das Command-Design-Pattern Funktion:
 * Öffnen und Einlesen einer Textdatei
 *
 * @author le
 * @see CommandInterface
 */
public class CommandOpen implements CommandInterface
{
    private EditorView view;
    private EditorModel model;
    private CommandInvoker invoker;

    public CommandOpen(EditorView view, EditorModel model)
    {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute()
    {
        JFileChooser fc = view.getFcOpenSave();
        fc.setDialogTitle("Datei öffnen");
        
        int choice = fc.showOpenDialog(view);
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            try
            {
                model.datenLesen(f);
                view.getLblStatus().setText("Datei: " + f.getAbsolutePath());
            }catch (Exception e)
            {
                JOptionPane.showMessageDialog(new JFrame(),"Fehler beim Laden der Datei!","Warnung",JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void undo()
    {
    }

  /**
   *
   * @return true falls Kommando sinnvoll rückgängig gemacht werden kann
   */
  @Override
  public boolean isUndoable()
  {
    return false;
  }
}

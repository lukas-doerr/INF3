/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commanddp.controller.commands;

import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import java.io.File;
import java.io.IOException;
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
public class CommandSave implements CommandInterface
{
    private EditorView view;
    private EditorModel model;
    private CommandInvoker invoker;

    public CommandSave(EditorView view, EditorModel model)
    {
      this.view = view;
      this.model = model;
    }

    @Override
    public void execute()
    {
        JFileChooser fc = view.getFcOpenSave();
        fc.setDialogTitle("Datei speichern");
        int choice = fc.showSaveDialog(view);
        if (choice == JFileChooser.APPROVE_OPTION)
        {
            File f = fc.getSelectedFile();
            try
            {
                model.datenSpeichern(f);
                view.getLblStatus().setText("Datei: " + f.getAbsolutePath());
            }catch (IOException e)
            {
                JOptionPane.showMessageDialog(new JFrame(),"Fehler beim Speichern der Datei!","Warnung",JOptionPane.WARNING_MESSAGE);
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

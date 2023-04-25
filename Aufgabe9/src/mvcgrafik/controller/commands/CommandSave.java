/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller.commands;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;

/**
 *
 * @author doerr
 */
public class CommandSave implements CommandInterface
{

  private FensterView view;
  private GrafikModel model;

  public CommandSave(FensterView view, GrafikModel model)
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
        model.savePoints(f.getAbsolutePath());
        //view.getLblStatus().setText("Datei: " + f.getAbsolutePath());
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(new JFrame(), "Fehler beim Speichern der Datei!", "Warnung", JOptionPane.WARNING_MESSAGE);
      }
    }
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

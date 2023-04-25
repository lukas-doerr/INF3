/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author doerr
 */
public class CommandOpen implements CommandInterface
{

  private View view;
  private Model model;

  public CommandOpen(View view, Model model)
  {
    this.view = view;
    this.model = model;
  }

  @Override
  public void execute()
  {
//    JFileChooser fc = view.getFcOpenSave();
//    fc.setDialogTitle("Datei öffnen");
//
//    int choice = fc.showOpenDialog(view);
//    if (choice == JFileChooser.APPROVE_OPTION)
//    {
//      File f = fc.getSelectedFile();
//      try
//      {
//        model.loadPoints(f.getAbsolutePath());
//        view.repaint();
//        //view.getLblStatus().setText("Datei: " + f.getAbsolutePath());
//      }
//      catch (Exception e)
//      {
//        JOptionPane.showMessageDialog(new JFrame(), "Fehler beim Laden der Datei!", "Warnung", JOptionPane.WARNING_MESSAGE);
//      }
//    }
  }

}

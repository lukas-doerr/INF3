/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcgrafik;

import java.awt.BorderLayout;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import mvcgrafik.controller.CommandController;
import mvcgrafik.controller.GrafikController;
import mvcgrafik.controller.commands.CommandInvoker;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author le
 */
public class Start
{

  public Start()
  {
    GrafikView view = new GrafikView();
    GrafikModel model = new GrafikModel();
    FensterView frm = new FensterView();
    view.setModel(model);

    CommandInvoker invoker = new CommandInvoker();
    GrafikController gController = new GrafikController(frm, view, model, invoker);
    CommandController cController = new CommandController(frm, view, model, invoker);
    cController.registerEvents();
    cController.registerCommands();
    gController.registerEvents();
    gController.registerCommands();

    frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frm.getContentPane().add(view, BorderLayout.CENTER);
    frm.setSize(800, 600);
    frm.setVisible(true);
  }

  public static void main(String[] args) throws ClassNotFoundException
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    }
    catch (InstantiationException ex)
    {
      Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnsupportedLookAndFeelException ex)
    {
      Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
    }

    new Start();
  }

}

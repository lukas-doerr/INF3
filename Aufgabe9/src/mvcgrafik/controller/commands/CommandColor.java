/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller.commands;

import java.awt.Color;
import javax.swing.JColorChooser;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author lukas
 */
public class CommandColor implements CommandInterface 
{

  private FensterView view;
  private GrafikModel model;

  public CommandColor(FensterView view, GrafikModel model)
  {
    this.view = view;
    this.model = model;
  }
    @Override
    public void execute() 
    {
        Color c = JColorChooser.showDialog(view, "Choose Pencil Color", Color.yellow);
        model.setColor(c);
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

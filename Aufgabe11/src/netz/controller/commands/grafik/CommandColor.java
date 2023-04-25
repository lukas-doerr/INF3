/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;

import java.awt.Color;
import javax.swing.JColorChooser;
import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author lukas
 */
public class CommandColor implements CommandInterface 
{

  private View view;
  private Model model;

  public CommandColor(View view, Model model)
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
    
}

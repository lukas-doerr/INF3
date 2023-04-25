/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package netz.controller.commands.grafik;

import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.GrafikView;

/**
 *
 * @author doerr
 */
public class CommandPrint implements CommandInterface
{
  private GrafikView view;
  private Model model;
  
  public CommandPrint(GrafikView view, Model model)
  {
    this.view = view;
    this.model = model;
  }

  @Override
  public void execute()
  {
    view.doPrint();
  }

}

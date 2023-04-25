/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package synchronize;

import synchronize.controller.StartStopController;
import synchronize.controller.ValueAdapter;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import synchronize.model.BanditModel;
import synchronize.view.MainView;

/**
 *
 * @author le
 */
public class Start 
{
  public Start()
  {
    MainView view = new MainView();
    BanditModel model = new BanditModel();
    StartStopController controller = new StartStopController(view, model);
    controller.registerEvents();
    ValueAdapter vAdapter = new ValueAdapter(view, model);
    vAdapter.doSubscribe();
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

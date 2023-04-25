/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.OverlayLayout;
import javax.swing.WindowConstants;

/**
 *
 * @author le
 */
public class Start 
{
  public Start()
  {
    JFrame fenster = new JFrame();
    fenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Container cont = fenster.getContentPane();
    cont.setLayout(new OverlayLayout(cont));

    RotationLine sec = new RotationLine(1000, 1f, 10);
    RotationLine min = new RotationLine(1000*60, 1f, 20);
    RotationLine std = new RotationLine(1000*60*60, 0.5f, 20);
    
    cont.add(sec);
    cont.add(min);
    cont.add(std);
    
    sec.setOpaque(false); // undurchsichtig -> false => ist also durchsichtig
    min.setOpaque(false); // undurchsichtig -> false => ist also durchsichtig
    std.setOpaque(false); // undurchsichtig -> false => ist also durchsichtig
    
    sec.start();
    min.start();
    std.start();
    
    fenster.setSize(800, 600);
    fenster.setVisible(true);
    
  }

  public static void main(String[] args) 
  {
    new Start();
  }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafik;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

/**
 *
 * @author le
 */
public class Zeichenflaeche extends JComponent // JPanel
{
  private Ellipse2D.Float ellipse;
  private BasicStroke pinsel;
  private static final float DICKE = 60f;
  
  public Zeichenflaeche()
  {
    ellipse = new Ellipse2D.Float();
    pinsel = new BasicStroke(DICKE);
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // neue Lib benutzen!!!!!!!
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    
    int breite = this.getWidth() - 1;
    int hoehe = this.getHeight() - 1;
    
    float x = DICKE/2;
    float y = DICKE/2;
    breite -= DICKE;
    hoehe -= DICKE;
    ellipse.setFrame(x, y, breite, hoehe);
    
    g2.setStroke(pinsel);
    g2.setPaint(Color.RED);
    
    g2.draw(ellipse);
    //g2.fill(ellipse);
  }
  
}

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
import java.awt.geom.Line2D;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author le
 */
public class RotationLine extends JComponent implements Runnable // JPanel
{
  private static Logger lg = Logger.getLogger("grafik");
  
  private Line2D.Float linienobjekt;
  private BasicStroke pinsel;
  private volatile double delta;
  private float maxRadius;
  
  private long schlafzeit;
  private ExecutorService eService; // Thread thd
  private Future task;
  private final float DICKE;
  private final float LENGTH;
  
  public RotationLine(long schlafzeit, float length, float thikness)
  {
    this.schlafzeit = schlafzeit;
    this.DICKE = thikness;
    this.LENGTH = length;
    delta = -96;
    linienobjekt = new Line2D.Float();
    pinsel = new BasicStroke(DICKE);
    maxRadius = 200;
    eService = Executors.newSingleThreadExecutor();
    task = null;
  }
  
  public void start()
  {
    if (task == null)
    {
      task = eService.submit(this);
    }
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g; // neue Lib benutzen!!!!!!!
    
    int breite = this.getWidth() - 1;
    int hoehe = this.getHeight() - 1;
    int middleX = breite/2;
    int middleY = hoehe/2;
    double endX;
    double endY;
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        
    
    synchronized(this)
    {
      maxRadius = -DICKE/2 + Math.min(breite, hoehe)/2;
      endX = middleX + Math.cos(Math.toRadians(delta))*maxRadius*LENGTH;
      endY = middleY + Math.sin(Math.toRadians(delta))*maxRadius*LENGTH;
    }
  
    linienobjekt.setLine(middleX, middleY, endX, endY);
    g2.setStroke(pinsel);
    g2.setPaint(Color.RED);
    
    g2.draw(linienobjekt);
    
//    g2.fill(kreisobjekt);
//    g2.setPaint(Color.BLACK);
//    g2.draw(kreisobjekt);
    //g2.fill(ellipse);
  }

  @Override
  public void run()
  {
    while (true)
    {
      synchronized(this)
      {
        delta += 6;
      }
      this.repaint();  // Neuzeichnen -> paintComponent
      try
      {
        Thread.sleep(schlafzeit);
      }
      catch (Exception ex)
      {
        lg.warning(ex.toString());
      }
    }
  }
  
}

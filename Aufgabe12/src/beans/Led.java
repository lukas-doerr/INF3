/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import javax.swing.JComponent;

/**
 *
 * @author le
 */
public class Led extends JComponent 
{
  private static Logger lg = Logger.getLogger("grafik");
  private Ellipse2D.Float kreisobjekt;
  private BasicStroke pinsel;
  private float radius;
  private boolean eingeschaltet;
  private Color farbe;
  private static final float DICKE = 4f;
  //Thread-sichere Liste!
  private CopyOnWriteArrayList<LedListener> listenerListe;
  
  
  public Led()
  {
    kreisobjekt = new Ellipse2D.Float();
    pinsel = new BasicStroke(DICKE);
    radius = 100;
    farbe = Color.RED;
    eingeschaltet = true;
    listenerListe = new CopyOnWriteArrayList<>();
  }
  
  //eigener Event
  public void addLedListener(LedListener listener)
  {
    listenerListe.add(listener);
  }
  public void removeLedListener(LedListener listener)
  {
    listenerListe.remove(listener);
  }
  //Event feuern
  public void fireLedEvent(LedEvent evt)
  {
    listenerListe.forEach(listener -> listener.ledStateChanged(evt));
  }
  
  public void hinUndHerschalten()
  {
    this.setEingeschaltet(!eingeschaltet);
  }

  public boolean isEingeschaltet()
  {
    return eingeschaltet;
  }

  public void setEingeschaltet(boolean eingeschaltet)
  {
    boolean alt = this.eingeschaltet;
    boolean neu = eingeschaltet;
    this.eingeschaltet = eingeschaltet;
    this.firePropertyChange("eingeschaltet", alt, neu);
    this.repaint();
  }

  public Color getFarbe()
  {
    return farbe;
  }

  public void setFarbe(Color farbe)
  {
    this.farbe = farbe;
    this.repaint();
    this.fireLedEvent(new LedEvent(this));
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
    
    radius = -DICKE/2 + Math.min(breite, hoehe)/2;
    
    float x = breite/2f - radius;
    float y = hoehe/2f - radius;

    kreisobjekt.setFrame(x, y, 2*radius, 2*radius);
    
    g2.setStroke(pinsel);
    
    if (eingeschaltet)
    {
      g2.setPaint(farbe);
    }
    else
    {
      g2.setPaint(this.getParent().getBackground());
    }
    
    g2.fill(kreisobjekt);
    
    g2.setPaint(Color.BLACK);
    g2.draw(kreisobjekt);
  }
}

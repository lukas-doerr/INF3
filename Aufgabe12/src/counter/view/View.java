/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package counter.view;

import counter.model.Value;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class View extends JComponent {

    private final Ellipse2D.Float kreisobjekt;
    private final BasicStroke pinsel;
    private static final float DICKE = 4f;

    private int number;
    private Color color;
    
    public View() {
        kreisobjekt = new Ellipse2D.Float();
        pinsel = new BasicStroke(DICKE);
        color = Color.BLACK;
    }
    
    public void setNumber(Value value) {
        number = value.is();
    }
    public void setColor(Color newColor){
        color = newColor;
    }
    public Color getColor(){
        return color;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(pinsel);
        g2.setPaint(color);

        int breite = this.getWidth();
        int hoehe = this.getHeight();
        float r = -DICKE / 2 + Math.min(breite, hoehe) / 2;
        float x = breite / 2f - r;
        float y = hoehe / 2f - r;
        kreisobjekt.setFrame(x, y, 2 * r, 2 * r);

        g2.setFont(new Font("Arial", Font.BOLD, (int) r));
        String t = String.valueOf(number);
        int xt = (breite - g2.getFontMetrics().stringWidth(t)) / 2;
        int yt = ((hoehe - g2.getFontMetrics().getHeight()) / 2) + g2.getFontMetrics().getAscent();

        g2.drawString(t, xt, yt);
        g2.draw(kreisobjekt);
    }

}

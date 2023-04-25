/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcgrafik.view;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import mvcgrafik.model.Figure;
import mvcgrafik.model.GrafikModel;

/**
 *
 * @author le
 */
public class GrafikView extends JComponent implements Printable
{
  private Line2D.Float line;
  private GrafikModel model;

  public GrafikView()
  {
    line = new Line2D.Float();
  }

  public void setModel(GrafikModel model)
  {
    this.model = model;
  }

  public void drawLine(Point p1, Point p2, Figure fig)
  {
    Graphics2D g2 = (Graphics2D) this.getGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    line.setLine(p1, p2);
    g2.setStroke(new BasicStroke(fig.getStroke()));
    g2.setColor(fig.getColor());
    g2.draw(line);
    g2.dispose(); // GAAAANNNNNZZZZZZ WICHTIG!!!!!!
  }


  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    //Lambda
    model.getFigures().forEach(fig ->
    {
      Point currentPoint = null;
      Point lastPoint;
      g2.setStroke(new BasicStroke(fig.getStroke()));
      g2.setColor(fig.getColor());
      for (Point p : fig.getPoints())
      {
        if (currentPoint == null)
        {
          currentPoint = p;
        }
        else
        {
          lastPoint = currentPoint;
          currentPoint = p;

          line.setLine(lastPoint, currentPoint);
          g2.draw(line);
        }
      };
    });

  }

  public void doPrint()
  {
    HashPrintRequestAttributeSet printSet
            = new HashPrintRequestAttributeSet();
    printSet.add(DialogTypeSelection.NATIVE);
    PrinterJob pj = PrinterJob.getPrinterJob();
    pj.setPrintable(this);
    if (pj.printDialog(printSet))
    {
      try
      {
        pj.print(printSet);
      }
      catch (PrinterException ex)
      {
        JOptionPane.showMessageDialog(this, ex.toString());
      }
    }
  }

  @Override
  public int print(Graphics gp, PageFormat pf, int pageIndex) throws PrinterException
  {
    Graphics2D g2p = (Graphics2D) gp;
    if (pageIndex == 0)
    {
      g2p.translate(pf.getImageableX(), pf.getImageableY());
      g2p.scale(pf.getImageableWidth() / this.getWidth(),
              pf.getImageableHeight() / this.getHeight());
      super.print(g2p);
      return Printable.PAGE_EXISTS;
    }
    else
    {
      return Printable.NO_SUCH_PAGE;
    }
  }

}

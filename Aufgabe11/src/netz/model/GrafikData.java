/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz.model;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import netz.util.OhmLogger;

/**
 *
 * @author le Serializable
 */
public class GrafikData {
    private static final Logger lg = OhmLogger.getLogger();
    
    
    private ArrayList<Figure> figures;
    private Figure currentFigure;
    private int stroke;
    private Color color;

    public GrafikData() {
        this.stroke = 1;
        this.color = Color.BLACK;
        figures = new ArrayList<>();
        currentFigure = new Figure(stroke, color);
        figures.add(currentFigure);
    }

    public void deleteContent() {
        figures = new ArrayList<>();
        currentFigure = new Figure(stroke, color);
        figures.add(currentFigure);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
        currentFigure.setColor(c);
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int t) {
        stroke = t;
        currentFigure.setStroke(t);
    }

    public void addFigure(int stroke) {
        currentFigure = new Figure(stroke, color);
        figures.add(currentFigure);
    }

    public void addFigure(Figure fig) {
        lg.info(String.valueOf(fig.getStroke()));
        figures.add(fig);
    }

    public void addPoint(Point p) {
        currentFigure.addPoint(p);
    }

    public Figure getCurrentFigure() {
        return currentFigure;
    }

    public List<Figure> getFigures() {
        return Collections.unmodifiableList(figures);
    }

    public void savePoints(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(figures);
        oos.flush(); // wichtig! leert und schreibt damit den Puffer
        oos.close();
    }

    public void loadPoints(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        if (obj instanceof ArrayList) {
            figures = (ArrayList<Figure>) obj;
        }
        ois.close();
    }
}

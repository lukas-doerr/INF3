/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author doerrlu76113
 */
public class Figure implements Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<Point> points;
    private Point lastPoint;
    private Point currentPoint;
    private Color color;
    private int stroke;

    public Figure(int stroke, Color color) {
        this.stroke = stroke;
        this.color = color;
        points = new ArrayList<>();
    }

    public void addPoint(Point p) {
        if (currentPoint == null) {
            currentPoint = p;
        }
        lastPoint = currentPoint;
        currentPoint = p;
        points.add(currentPoint);
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public List<Point> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color = c;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int t) {
        stroke = t;
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcgrafik.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import mvcgrafik.controller.commands.CommandColor;
import mvcgrafik.controller.commands.CommandInvoker;
import mvcgrafik.controller.commands.CommandNew;
import mvcgrafik.controller.commands.CommandPencilStroke;
import mvcgrafik.model.Figure;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author le
 */
public class GrafikController extends MouseAdapter implements MouseMotionListener {

    private GrafikView view;
    private GrafikModel model;
    private FensterView frm;
    private CommandInvoker invoker;

    public GrafikController(FensterView frm, GrafikView view, GrafikModel model, CommandInvoker invoker) {
        this.view = view;
        this.model = model;
        this.frm = frm;
        this.invoker = invoker;
    }

    public void setModel(GrafikModel model) {
        this.model = model;
    }

    public void registerCommands() {
        CommandNew cmdNew = new CommandNew(view, model, this);
        CommandColor cmdColor = new CommandColor(frm, model);
        CommandPencilStroke cmdStroke = new CommandPencilStroke(frm, model);

        invoker.addCommand(frm.getMenuPencilColor(), cmdColor);
        invoker.addCommand(frm.getSpinSize(), cmdStroke);

        invoker.addCommand(frm.getBtn_add(), cmdNew);
        invoker.addCommand(frm.getMenuNewFile(), cmdNew);
    }

    public void registerEvents() {
        view.addMouseMotionListener(this);
        view.addMouseListener(this);
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        if (evt.getButton() == MouseEvent.BUTTON1) {
            model.addFigure((int)frm.getSpinSize().getValue());
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            view.doPrint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point cPoint = evt.getPoint();
        Figure cFigure = model.getCurrentFigure();

        cFigure.addPoint(cPoint);
        Point lPoint = cFigure.getLastPoint();
        view.drawLine(lPoint, cPoint, cFigure);
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
    }
}

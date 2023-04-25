/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import netz.controller.commands.CommandConnect;
import netz.controller.commands.CommandInvoker;
import netz.controller.commands.CommandSend;
import netz.controller.commands.CommandServerMode;
import netz.controller.commands.grafik.CommandAddFigure;
import netz.controller.commands.grafik.CommandColor;
import netz.controller.commands.grafik.CommandNew;
import netz.controller.commands.grafik.CommandOpen;
import netz.controller.commands.grafik.CommandPencilStroke;
import netz.controller.commands.grafik.CommandPrint;
import netz.controller.commands.grafik.CommandSave;
import netz.model.Model;
import netz.view.GrafikView;
import netz.view.View;

/**
 *
 * @author Carmin Kern
 */
public class Controller extends MouseAdapter implements ActionListener, ChangeListener, MouseMotionListener {

    private View view;
    private GrafikView gView;
    private Model model;
    private CommandInvoker invoker;

    public Controller(View view, Model model, CommandInvoker invoker) {
        this.view = view;
        this.model = model;
        this.invoker = invoker;

        JPanel chatPane = (JPanel) view.getContentPane().getComponent(2);
        JPanel grafikPane = (JPanel) chatPane.getComponent(1);
        this.gView = (GrafikView) grafikPane.getComponent(1);
        this.gView.setModel(model);
    }

    public void registerEvents() {

        view.getBtnConnect().addActionListener(this);
        view.getBtnSend().addActionListener(this);
        view.getBtnServerMode().addActionListener(this);

        //view.getBtn_open().addActionListener(this);
        view.getMenuOpen().addActionListener(this);

        //view.getBtn_save().addActionListener(this);
        view.getMenuSave().addActionListener(this);

        //view.getBtn_add().addActionListener(this);
        view.getMenuNewFile().addActionListener(this);

        view.getMenuPencilColor().addActionListener(this);
        view.getSpinSize().addChangeListener(this);

        view.getMenuPrint().addActionListener(this);

        gView.addMouseMotionListener(this);
        gView.addMouseListener(this);

    }

    public void registerCommands() {
        CommandConnect cmdConnect = new CommandConnect(view, model);
        CommandSend cmdSend = new CommandSend(view, model);
        CommandServerMode cmdServerMode = new CommandServerMode(view, model);

        invoker.addCommand(view.getBtnConnect(), cmdConnect);
        invoker.addCommand(view.getBtnSend(), cmdSend);
        invoker.addCommand(view.getBtnServerMode(), cmdServerMode);

        // Grafik Commands
        CommandNew cmdNew = new CommandNew(gView, model);
        CommandColor cmdColor = new CommandColor(view, model);
        CommandPencilStroke cmdStroke = new CommandPencilStroke(view, model);
        CommandSave cmdSave = new CommandSave(view, model);
        CommandOpen cmdOpen = new CommandOpen(view, model);
        CommandPrint cmdPrint = new CommandPrint(gView, model);
        CommandAddFigure cmdAddFigure = new CommandAddFigure(view, model);

        invoker.addCommand(MouseEvent.BUTTON1, cmdAddFigure);

        //invoker.addCommand(view.getBtn_open(), cmdOpen);
        invoker.addCommand(view.getMenuOpen(), cmdOpen);

        //invoker.addCommand(view.getBtn_save(), cmdSave);
        invoker.addCommand(view.getMenuSave(), cmdSave);

        invoker.addCommand(view.getMenuPrint(), cmdPrint);

        invoker.addCommand(view.getMenuPencilColor(), cmdColor);
        invoker.addCommand(view.getSpinSize(), cmdStroke);

        //invoker.addCommand(view.getBtn_add(), cmdNew);
        invoker.addCommand(view.getMenuNewFile(), cmdNew);
    }

    /**
     * Polymorphismus!!!! zur Entscheidung welche Aktion durchgeführt wird
     *
     * @param evt evt.getSource liefert Eventquelle als key für Hashmap im
     * Invoker
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        Object key = evt.getSource();
        invoker.executeCommand(key);
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        Object key = ce.getSource();
        invoker.executeCommand(key);
    }

    @Override
    public void mouseReleased(MouseEvent evt) {
        Object key = evt.getButton();
        invoker.executeCommand(key);
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
        Point cPoint = evt.getPoint();
        model.addPoint(cPoint);
        gView.drawLine();
    }
}

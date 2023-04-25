/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import mvcgrafik.controller.commands.CommandInvoker;
import mvcgrafik.controller.commands.CommandOpen;
import mvcgrafik.controller.commands.CommandPrint;
import mvcgrafik.controller.commands.CommandSave;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author doerr
 */
public class CommandController implements ActionListener, ChangeListener {

    private FensterView view;
    private GrafikModel model;
    private CommandInvoker invoker;
    private GrafikView grafik;

    public CommandController(FensterView view, GrafikView grafik, GrafikModel model, CommandInvoker invoker) {
        this.view = view;
        this.grafik = grafik;
        this.model = model;
        this.invoker = invoker;
    }

    public void registerEvents() {
        view.getBtn_open().addActionListener(this);
        view.getMenuOpen().addActionListener(this);

        view.getBtn_save().addActionListener(this);
        view.getMenuSave().addActionListener(this);

        view.getBtn_add().addActionListener(this);
        view.getMenuNewFile().addActionListener(this);

        view.getMenuPencilColor().addActionListener(this);
        view.getSpinSize().addChangeListener(this);

        view.getMenuPrint().addActionListener(this);
    }

    public void registerCommands() {
        CommandSave cmdSave = new CommandSave(view, model);
        CommandOpen cmdOpen = new CommandOpen(view, model);
        CommandPrint cmdPrint = new CommandPrint(grafik, model);

        invoker.addCommand(view.getMenuOpen(), cmdOpen);
        invoker.addCommand(view.getBtn_save(), cmdSave);
        invoker.addCommand(view.getMenuSave(), cmdSave);

        invoker.addCommand(view.getMenuPrint(), cmdPrint);
    }

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
}

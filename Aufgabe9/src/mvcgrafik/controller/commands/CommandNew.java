/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller.commands;

import mvcgrafik.controller.GrafikController;
import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author doerr
 */
public class CommandNew implements CommandInterface {

    private GrafikView view;
    private GrafikModel model;
    private GrafikController gc;

    public CommandNew(GrafikView view, GrafikModel model, GrafikController gc) {
        this.view = view;
        this.model = model;
        this.gc = gc;
    }

    @Override
    public void execute() {
        model = new GrafikModel();
        view.setModel(model);
        gc.setModel(model);
        view.repaint();
    }

    @Override
    public void undo() {
    }

    @Override
    public boolean isUndoable() {
        return false;
    }

}

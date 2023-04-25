/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.controller.commands;

import mvcgrafik.model.GrafikModel;
import mvcgrafik.view.FensterView;

/**
 *
 * @author lukas
 */
public class CommandPencilStroke implements CommandInterface {

    private FensterView view;
    private GrafikModel model;

    public CommandPencilStroke(FensterView view, GrafikModel model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute() {
        int stroke = (int) view.getSpinSize().getValue();
        model.setStroke(stroke);
    }

    @Override
    public void undo() {

    }

    @Override
    public boolean isUndoable() {
        return false;
    }

}

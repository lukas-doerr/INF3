/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import netz.controller.commands.CommandInterface;
import netz.model.Figure;
import netz.model.Model;
import netz.view.View;



/**
 *
 * @author doerr
 */
public class CommandAddFigure implements CommandInterface {

    private final View frm;
    private final Model model;

    public CommandAddFigure(View frm, Model model) {
        this.frm = frm;
        this.model = model;
    }

    @Override
    public void execute() {
        Figure currentFig = model.getCurrentFigure();
        try {
            model.send(currentFig);
        } catch (IOException ex) {
            Logger.getLogger(CommandAddFigure.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.addFigure((int) frm.getSpinSize().getValue());
    }
}

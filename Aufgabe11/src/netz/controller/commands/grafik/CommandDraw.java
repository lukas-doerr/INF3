/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.GrafikView;
import netz.view.View;

/**
 *
 * @author doerr
 */
public class CommandDraw implements CommandInterface {

    private GrafikView view;
    private Model model;

    /**
     *
     * @param view
     * @param model
     */
    private 

    CommandDraw(GrafikView view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute() {
        try {
            model = new Model();
        } catch (IOException ex) {
            Logger.getLogger(CommandDraw.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.setModel(model);
        view.repaint();
    }


}

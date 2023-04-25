/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;


import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.GrafikView;
import netz.view.View;

/**
 *
 * @author doerr
 */
public class CommandNew implements CommandInterface {

    private final GrafikView view;
    private Model model;

    public CommandNew(GrafikView view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute() {
        model.deleteContent();
        view.repaint();
    }


}

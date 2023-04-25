/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands.grafik;


import netz.controller.commands.CommandInterface;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author lukas
 */
public class CommandPencilStroke implements CommandInterface {

    private View view;
    private Model model;

    public CommandPencilStroke(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute() {
        int stroke = (int) view.getSpinSize().getValue();
        model.setStroke(stroke);
    }


}

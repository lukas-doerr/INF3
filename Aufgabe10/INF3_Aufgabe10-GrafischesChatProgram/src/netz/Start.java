/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netz;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import netz.controller.Controller;
import netz.controller.ReceiveAdapter;
import netz.controller.commands.CommandInvoker;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author le Die Startklasse realisiert das DP "Builder" jetzt kommt ein
 * zweiter Entwickler hinzu
 */
public class Start {

    private View view;
    private Model model;

    public Start() throws IOException {
        view = new View();
        model = new Model();

        CommandInvoker invoker = new CommandInvoker();
        Controller ctrlCommand = new Controller(view, model, invoker);
        ReceiveAdapter receive = new ReceiveAdapter(view, model);

        ctrlCommand.registerEvents();
        ctrlCommand.registerCommands();


        view.setSize(800, 600);
        view.setVisible(true);
        receive.doSubscribe();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new Start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }

    }

}

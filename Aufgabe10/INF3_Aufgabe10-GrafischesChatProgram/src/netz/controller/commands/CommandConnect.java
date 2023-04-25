/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands;

import netz.model.Model;
import netz.view.View;

/**
 *
 * @author LiQui
 */
public class CommandConnect implements CommandInterface
{
    private View view;
    private Model model;
    private CommandInvoker invoker;

    public CommandConnect(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }
    
    @Override
    public void execute()
    {
        if(!model.isConnected()){
            model.setIP(view.getTfIP().getText());
            model.setPort(Integer.parseInt(view.getTfPort().getText()));
            model.connect();
            view.getBtnConnect().setText("Trennen");
            view.getBtnServerMode().setEnabled(false);
            view.getTfIP().setEnabled(false);
            view.getTfPort().setEnabled(false);
        }else{
            model.disconnect();
            view.getBtnConnect().setText("Verbinden");
            view.getBtnServerMode().setEnabled(true);
            if(!model.isServerMode()){
              view.getTfIP().setEnabled(true);
            }
            view.getTfPort().setEnabled(true);
        }
    }
    
}

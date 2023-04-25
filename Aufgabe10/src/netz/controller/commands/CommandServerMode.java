/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands;

import java.awt.Color;
import netz.model.Model;
import netz.view.View;

/**
 *
 * @author LiQui
 */
public class CommandServerMode implements CommandInterface
{
    private View view;
    private Model model;
    private CommandInvoker invoker;

    public CommandServerMode(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }
    
    @Override
    public void execute()
    {
        if(model.isServerMode()){
            model.setServerMode(false);
            view.getBtnServerMode().setBackground(Color.red);
            view.getTfIP().setEnabled(true);
        }else{
            model.setServerMode(true);
            view.getBtnServerMode().setBackground(Color.green);
            view.getTfIP().setEnabled(false);
        }
    }
    
}

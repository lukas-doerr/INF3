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
public class CommandSend implements CommandInterface
{

    private View view;
    private Model model;
    private CommandInvoker invoker;

    public CommandSend(View view, Model model)
    {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute()
    {
        if (model.isConnected())
        {
            model.Send(view.getTfMessage().getText());
            view.getTfMessage().setText("");
        }
    }

}

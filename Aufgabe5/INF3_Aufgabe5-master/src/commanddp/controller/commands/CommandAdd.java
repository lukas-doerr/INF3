/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commanddp.controller.commands;

import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LiQui
 */
public class CommandAdd implements CommandInterface
{
    private EditorView view;
    private EditorModel model;
    private CommandInvoker invoker;

    public CommandAdd(EditorView view, EditorModel model)
    {
      this.view = view;
      this.model = model;
    }

    @Override
    public void execute()
    {
        model.eintragHinzufuegen();
    }

    @Override
    public void undo()
    {
        JOptionPane.showMessageDialog(new JFrame(),"UNDO ADD","UNDO",JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean isUndoable()
    {
        return true;
    }
}

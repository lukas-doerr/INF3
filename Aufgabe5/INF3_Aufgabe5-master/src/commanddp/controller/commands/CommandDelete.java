/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commanddp.controller.commands;

import commanddp.model.EditorModel;
import commanddp.view.EditorView;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author lukas
 */
public class CommandDelete implements CommandInterface
{      
    private EditorView view;
    private EditorModel model;
    private CommandInvoker invoker;
    private int selectedRow;
    private int lastRow;
    private Stack<Integer> rowStack;
    private Stack<ArrayList<String>> rowDataStack;
    private ArrayList<String> data;

    
    public CommandDelete(EditorView view, EditorModel model)
    {
        this.view = view;
        this.model = model;
        rowStack = new Stack<>();
        rowDataStack = new Stack<>();
    }

    @Override
    public void execute() 
    {
        selectedRow = view.gettContent().getSelectedRow();
        lastRow = view.gettContent().getRowCount() - 1;
        if( view.gettContent().isRowSelected(selectedRow)){
            rowStack.push(selectedRow);
            data = model.getRowData(selectedRow);
            rowDataStack.push(data);
            model.eintragLoeschen(selectedRow);
        }else{
            rowStack.push(lastRow);
            data = model.getRowData(lastRow);
            rowDataStack.push(data);
            model.eintragLoeschen(lastRow);
        }
    }

    @Override
    public void undo() 
    {
        JOptionPane.showMessageDialog(new JFrame(),"UNDO DELETE","UNDO",JOptionPane.WARNING_MESSAGE);
        model.insertRowData(rowStack.pop(), rowDataStack.pop());
        //rowStack.pop();
        //rowDataStack.pop();

    }

    @Override
    public boolean isUndoable() 
    {
        return true;
    }
}

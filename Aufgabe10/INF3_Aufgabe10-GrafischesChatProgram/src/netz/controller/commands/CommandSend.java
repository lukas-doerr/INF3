/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller.commands;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import netz.model.Model;
import netz.util.OhmLogger;
import netz.view.View;

/**
 *
 * @author LiQui
 */
public class CommandSend implements CommandInterface {
    private static final Logger lg = OhmLogger.getLogger();
    
    private View view;
    private Model model;
    private CommandInvoker invoker;

    public CommandSend(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void execute() {
        if (model.isConnected()) {
            String message = view.getTfMessage().getText();
            StyledDocument doc = view.getTfCommunikation().getStyledDocument();
            Style style = view.getTfCommunikation().addStyle("", null);
            StyleConstants.setForeground(style, Color.blue);
            try {
                doc.insertString(doc.getLength(), message + "\n", style);
            } catch (BadLocationException ex) {
                lg.info("Fehler bei ");
            }
            model.Send(message);
            view.getTfMessage().setText("");
        }
    }

}

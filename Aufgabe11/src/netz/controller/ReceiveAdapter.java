/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller;

import java.awt.Color;
import java.awt.Point;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import netz.model.Figure;
import netz.model.Model;
import netz.model.UserData;
import netz.util.OhmLogger;
import netz.view.GrafikView;
import netz.view.View;

/**
 *
 * @author lukas
 */
public class ReceiveAdapter implements Subscriber<UserData> {

    private static final Logger lg = OhmLogger.getLogger();

    private Object message;

    private View view;
    private Model model;

    private Subscription subscription;

    public ReceiveAdapter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void doSubscribe() {
        model.doSubscribe(this);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        lg.info("Subscribe");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(UserData usrData) {
        if (usrData.isConnected) {
            message = usrData.getMessage();
            String user = usrData.getUser();
            if(message instanceof Figure)
            {
                lg.info("Ist Figure");
                model.addFigure((Figure) message);
                view.repaint();
            }            
            else {
                StyledDocument doc = view.getTfCommunikation().getStyledDocument();
                // Change Textcolor
                Style style = view.getTfCommunikation().addStyle("", null);
                StyleConstants.setForeground(style, Color.darkGray);
                try {
                    doc.insertString(doc.getLength(), user + ": ", style);
                    doc.insertString(doc.getLength(), message + "\n", null);
                } catch (BadLocationException ex) {
                    lg.info("Fehler bei String Darstellung");
                }
            }
            view.getLabel1().setText("Verbunden mit " + user);
            //view.getTfCommunikation().setText(newText);
        } else {
            lg.info("Not connected -------------___>");
            view.getBtnConnect().setText("Verbinden");
            view.getBtnServerMode().setEnabled(true);
            if (!model.isServerMode()) {
                view.getTfIP().setEnabled(true);
            }
            view.getTfPort().setEnabled(true);
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

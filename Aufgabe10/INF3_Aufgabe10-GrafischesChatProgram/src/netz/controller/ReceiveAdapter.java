/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netz.controller;

import java.awt.Color;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
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
 * @author lukas
 */
public class ReceiveAdapter implements Subscriber<String> {

    private static final Logger lg = OhmLogger.getLogger();

    private String message;

    private View view;
    private Model model;

    private Subscription subscription;

    public ReceiveAdapter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void doSubscribe(){
        model.doSubscribe(this);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        lg.info("Subscribe");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        message = item;
        StyledDocument doc = view.getTfCommunikation().getStyledDocument();
        Style style = view.getTfCommunikation().addStyle("", null);
        StyleConstants.setForeground(style, Color.red);
        try {
            doc.insertString(doc.getLength(), message + "\n", style);
        } catch (BadLocationException ex) {
            lg.info("Fehler bei ");
                    }
        //view.getTfCommunikation().setText(newText);
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

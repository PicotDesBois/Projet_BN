package org.Package.pages_graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class defaite extends JFrame{
    private JPanel paneldef;
    private JButton menuButton;

    public defaite(){
        setLocation(400,200);
        setTitle("Défaite");
        setSize(450,300);
        setContentPane(paneldef);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        menuButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu2().setVisible(true);
                setVisible(false);
            }
        });
    }


    public static void main (String args[]) {

        defaite def = new defaite();

    }
}

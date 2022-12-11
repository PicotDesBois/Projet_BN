package org.Package.pages_graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class victoire extends JFrame {
    private JButton menuButton;
    private JPanel panelvic;

    public victoire(){
        setLocation(400,200);
        setTitle("Victoire");
        setSize(450,300);
        setContentPane(panelvic);
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

        victoire vic = new victoire();

    }
}

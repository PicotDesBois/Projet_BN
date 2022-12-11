package org.Package.pages_graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aide extends JFrame {
    private JTextArea leJeuBatailleNavaleTextArea1;
    private JTextArea lesActionsPourDéplacerTextArea;
    private JButton retourButton;
    private JPanel panelaide;

    public Aide() {
        setLocation(400,200);
        setTitle("Aide");
        setSize(450,300);
        setContentPane(panelaide);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        retourButton.addActionListener(new ActionListener() {
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

        Aide aide = new Aide();

    }
}
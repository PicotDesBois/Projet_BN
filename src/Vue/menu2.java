package Vue;

//import org.Package.GameGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class menu2 extends JFrame{
    private JButton nouvellePartieButton;
    private JButton chargerUnePartieButton;
    private JButton aideButton;
    private JPanel Mainpanel;

    public menu2() {
        setLocation(250, 100);
        setTitle("   Welcome   ");
        setSize(800, 400);
        setContentPane(Mainpanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        aideButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new Aide().setVisible(true);
                setVisible(false);
            }
        });
        nouvellePartieButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameGUI().setVisible(true);
                setVisible(false);
            }
        });
    }
    public static void main (String args[]) {

        menu2 myFrame = new menu2();

    }
}


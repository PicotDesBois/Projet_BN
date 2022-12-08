import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menu extends JFrame{
    private JPanel Mainpanel;
    private JPanel mainpanel1;
    private JButton aideButton;
    private JButton chargerUnePartieButton;
    private JButton nouvellePartieButton;

    public menu(){
        setLocation(400,200);
        setTitle("Aide");
        setSize(450,300);
        setContentPane(Mainpanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        aideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Aide().setVisible(true);
                setVisible(false);
            }
        });
    }

public static void main (String args[]) {
    menu myFrame = new menu();
}
}

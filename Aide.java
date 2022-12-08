import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Aide extends JFrame{
    private JTextArea text1;
    private JTextArea TextArea2;
    private JPanel panelaide;
    private JButton returnButton;

    public Aide(){
        setLocation(400,200);
        setTitle("Aide");
        setSize(450,300);
        setContentPane(panelaide);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu().setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main (String args[]) {
        Aide aide = new Aide();
    }
}

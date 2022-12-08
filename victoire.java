import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class victoire extends JFrame{
    private JButton retourMenuButton;
    private JPanel panelvic;

    public victoire(){
        setLocation(400,200);
        setTitle("Victoire");
        setSize(450,300);
        setContentPane(panelvic);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        retourMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu().setVisible(true);
                setVisible(false);
            }
        });


    }
    public static void main (String args[]){
        victoire vic = new victoire();
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class defaite extends JFrame{
    private JButton rmenuButton;
    private JPanel paneldefaite;

    public defaite() {
        setLocation(400,200);
        setTitle("Défaite");
        setSize(450,300);
        setContentPane(paneldefaite);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        rmenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new menu().setVisible(true);
                setVisible(false);
            }
        });

    }

    public static void main(String args[]){
        defaite deaite =new defaite();
    }
}

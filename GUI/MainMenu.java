package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MainMenu extends JFrame{
    public static void main(String[] args) {
        new MainMenu();
    }


    public MainMenu() {
        super("Main Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JButton start = new JButton("Create Simulation");
        JButton exit = new JButton("Exit");
        panel.add(start);
        panel.add(exit);
        add(panel, BorderLayout.CENTER);
        setVisible(true);


        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CreateSimulation();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}

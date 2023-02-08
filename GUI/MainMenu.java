package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * This class is the main menu of the simulation. It has two buttons, one for creating a simulation and one for exiting the program.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class MainMenu extends JFrame {
    /*!
        \brief The main method of the MainMenu class.
        * It creates a new MainMenu object.
        * @see MainMenu
    */
    public static void main(String[] args) {
        new MainMenu();
    }
    /*!
        \brief The constructor of the MainMenu class.
        * The constructor of the MainMenu class.
        * It creates a JFrame with the title "Main Menu", sets the size to 400x400, and sets the default close operation to EXIT_ON_CLOSE.
        * It also sets the layout of the JFrame to BorderLayout and creates a JPanel with GridLayout(2, 1).
        * It then creates two JButtons, one with the text "Create Simulation" and one with the text "Exit".
        * It adds the buttons to the JPanel and adds the JPanel to the JFrame.
        * It then adds an ActionListener to the "Create Simulation" button.
        * The ActionListener disposes the MainMenu JFrame and creates a new CreateSimulation JFrame.
        * It then adds an ActionListener to the "Exit" button.
        * The ActionListener exits the program.
        * @see CreateSimulation
    */
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
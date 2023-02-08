package GUI;
import java.awt.*;
import javax.swing.*;

import Instances.Carnivorous;
import Instances.Prey;

import java.awt.event.*;
import Simulation.Simulation;
/**
 * The CreateSimulation class is a graphical user interface (GUI) for creating and starting a simulation.
 * It allows users to input parameters such as food density, food calories, food color, and world size, 
 * and then start the simulation with either default or custom setup.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class CreateSimulation extends JFrame {
    /*
     * The simulation object that will be created.
     * @see Simulation
     */
    private Simulation simulation; 
    /*!
        \brief The constructor of the CreateSimulation class.
        * It creates a JFrame with the title "Create Simulation", sets the size to 400x400, and sets the default close operation to EXIT_ON_CLOSE.
        * It also sets the layout of the JFrame to BorderLayout and creates a JPanel with GridLayout(5, 2).
        * It then creates JLabels and JTextFields for food density, food calories, food color, and world size, and adds them to the JPanel.
        * It then creates a JButton with the text "Start Simulation" and adds an ActionListener to it.
        * The ActionListener starts the simulation with the parameters that the user has inputted.
        * @see Simulation
    */ 
    public CreateSimulation() {
        super("Create Simulation");
        simulation = new Simulation();
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel foodDensityLabel = new JLabel("Food Density");
        JLabel foodCaloriesLabel = new JLabel("Food Calories");
        JLabel foodColorLabel = new JLabel("Food Color");
        JLabel worldSizeLabel = new JLabel("World Size");

        JTextField foodDensityInput = new JTextField(Float.toString(simulation.foodDensity));
        JTextField foodCaloriesInput = new JTextField(Float.toString(simulation.foodCalories));
        String[] colors = {
            "Red",
            "Green",
            "Blue",
            "Yellow"
        };
        JComboBox < String > foodColorInput = new JComboBox < String > (colors);
        JTextField worldSizeInput = new JTextField(Integer.toString(Simulation.worldSize));

        panel.add(foodDensityLabel);
        panel.add(foodDensityInput);
        panel.add(foodCaloriesLabel);
        panel.add(foodCaloriesInput);
        panel.add(foodColorLabel);
        panel.add(foodColorInput);
        panel.add(worldSizeLabel);
        panel.add(worldSizeInput);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
        JButton startWithDefaultSetup = new JButton("Start Default");
        JButton startWithCustomSetup = new JButton("Customize Setup");
        panel.add(startWithDefaultSetup);
        panel.add(startWithCustomSetup);

        startWithCustomSetup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checkInput(foodDensityInput.getText()) && checkInput(foodCaloriesInput.getText()) && checkInput(worldSizeInput.getText())) {
                    simulation.foodDensity = Float.parseFloat(foodDensityInput.getText());
                    simulation.foodCalories = Float.parseFloat(foodCaloriesInput.getText());
                    Simulation.worldSize = Integer.parseInt(worldSizeInput.getText());
                    switch (foodColorInput.getSelectedIndex()) {
                    case 0:
                        simulation.foodColor = Color.RED;
                        break;
                    case 1:
                        simulation.foodColor = Color.GREEN;
                        break;
                    case 2:
                        simulation.foodColor = Color.BLUE;
                        break;
                    case 3:
                        simulation.foodColor = Color.YELLOW;
                        break;
                    }
                    dispose();
                    new CustomSetup(simulation);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid inputs");
                }
            }
        });

        startWithDefaultSetup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Would you like to run the simulation with graphics?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (checkInput(foodDensityInput.getText()) && checkInput(foodCaloriesInput.getText()) && checkInput(worldSizeInput.getText())) {
                    simulation.foodDensity = Float.parseFloat(foodDensityInput.getText());
                    simulation.foodCalories = Float.parseFloat(foodCaloriesInput.getText());
                    Simulation.worldSize = Integer.parseInt(worldSizeInput.getText());
                    switch (foodColorInput.getSelectedIndex()) {
                    case 0:
                        simulation.foodColor = Color.RED;
                        break;
                    case 1:
                        simulation.foodColor = Color.GREEN;
                        break;
                    case 2:
                        simulation.foodColor = Color.BLUE;
                        break;
                    case 3:
                        simulation.foodColor = Color.YELLOW;
                        break;
                    }
                    dispose();
                    //Default Setup for the simulation
                    //Carn-> count: 75, energy: 300000, size: 8, speed: 8, vision: 50, reproduction: 0.0006, color: orange
                    //Prey-> count: 150, energy: 300000, size: 6, speed: 4.5, vision: 50, reproduction: 0.004, color: magenta, calories: 60k
                    for (int i = 0; i < 75; i++)
                        simulation.addInstance(new Carnivorous("CARN", 300000.0f, 8, 8.0f, 50.0f, 0.0006f, Color.ORANGE));
                    for (int i = 0; i < 150; i++)
                        simulation.addInstance(new Prey("PREY", 300000.0f, 6, 4.5f, 50.0f, 0.012f, 60000.0f, Color.MAGENTA));
                    simulation.start(response == JOptionPane.YES_OPTION);

                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid inputs");
                }
            }
        });

    }
    /*!
        \brief Checks if the input is a valid number.
        * Checks if the input is a valid number.
        * @param input The input that will be checked if it is float.
        * @return Returns true if the input is a valid number, and false if it is not.
    */
    public boolean checkInput(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
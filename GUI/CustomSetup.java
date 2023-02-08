package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Simulation.Simulation;
import Instances.*;
/**
 * CustomSetup class is the class that creates the custom setup window. 
 * It is used to create a simulation with custom settings. 
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class CustomSetup extends JFrame {
    /*
     * The simulation object that will be created.
     * @see Simulation
     */
    Simulation simulation;
    /**
     \brief The constructor of the CustomSetup class.
     * It creates a JFrame with the title "Custom Setup", sets the size to 400x400, and sets the default close operation to EXIT_ON_CLOSE.
     * It also creates JLabels and JTextFields for type, name, energy, size, speed, vision range, reproduction rate, color, number of beings, and calories.
     * It then creates a JComboBox for type and adds items to it.
     * It then creates a JButton with the text "Start Simulation" and adds an ActionListener to it.
     * The ActionListener starts the simulation with the parameters that the user has inputted.
     * @see Simulation
     */

    public CustomSetup(Simulation simulation) {
        super("Custom Setup");
        this.simulation = simulation;
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JLabel type = new JLabel("Type");
        JLabel name = new JLabel("Name");
        JLabel energy = new JLabel("Energy");
        JLabel size = new JLabel("Size");
        JLabel speed = new JLabel("Speed");
        JLabel visionRange = new JLabel("Vision Range");
        JLabel reproductionRate = new JLabel("Reproduction Rate");
        JLabel color = new JLabel("Color");
        JLabel numberOf = new JLabel("Number of beings");
        JLabel calories = new JLabel("Calories (Prey only)");

        JComboBox < String > typeBox = new JComboBox < String > ();
        typeBox.addItem("Carnivorous");
        typeBox.addItem("Prey");

        JComboBox < String > colorBox = new JComboBox < String > ();
        ArrayList < String > colors = new ArrayList < String > ();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");
        colors.add("Orange");
        colors.add("Magenta");
        colors.add("Pink");
        colors.add("White");
        colors.add("Gray");

        for (String c: colors) {
            colorBox.addItem(c);
        }

        JTextField nameInput = new JTextField();
        JTextField energyInput = new JTextField();
        JTextField sizeInput = new JTextField();
        JTextField speedInput = new JTextField();
        JTextField visionRangeInput = new JTextField();
        JTextField reproductionRateInput = new JTextField();
        JTextField caloriesInput = new JTextField();
        JTextField numberOfBeing = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.add(type);
        panel.add(typeBox);
        panel.add(name);
        panel.add(nameInput);
        panel.add(energy);
        panel.add(energyInput);
        panel.add(size);
        panel.add(sizeInput);
        panel.add(speed);
        panel.add(speedInput);
        panel.add(visionRange);
        panel.add(visionRangeInput);
        panel.add(reproductionRate);
        panel.add(reproductionRateInput);
        panel.add(color);
        panel.add(colorBox);
        panel.add(numberOf);
        panel.add(numberOfBeing);
        panel.add(calories);
        panel.add(caloriesInput);

        setVisible(true);

        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = "";
                float energy = 0;
                int size = 0;
                float speed = 0;
                float visionRange = 0;
                float reproductionRate = 0;
                float calories = 0;
                int numberOf = 0;
                Color color = Color.RED;

                try {
                    name = nameInput.getText();
                    energy = Float.parseFloat(energyInput.getText());
                    size = Integer.parseInt(sizeInput.getText());
                    speed = Float.parseFloat(speedInput.getText());
                    visionRange = Float.parseFloat(visionRangeInput.getText());
                    reproductionRate = Float.parseFloat(reproductionRateInput.getText());
                    numberOf = Integer.parseInt(numberOfBeing.getText());
                    switch (colorBox.getSelectedIndex()) {
                    case 0:
                        color = Color.RED;
                        break;
                    case 1:
                        color = Color.GREEN;
                        break;
                    case 2:
                        color = Color.BLUE;
                        break;
                    case 3:
                        color = Color.YELLOW;
                        break;
                    case 4:
                        color = Color.ORANGE;
                        break;
                    case 5:
                        color = Color.MAGENTA;
                        break;
                    case 6:
                        color = Color.PINK;
                        break;
                    case 7:
                        color = Color.WHITE;
                        break;
                    case 8:
                        color = Color.GRAY;
                        break;
                    }

                    if (typeBox.getSelectedItem().equals("Prey"))
                        calories = Float.parseFloat(caloriesInput.getText());

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (typeBox.getSelectedItem().equals("Carnivorous")) {
                    for (int i = 0; i < numberOf; i++) {
                        Carnivorous instance = new Carnivorous(name, energy, size, speed, visionRange, reproductionRate, color);
                        simulation.addInstance(instance);
                    }
                } else if (typeBox.getSelectedItem().equals("Prey")) {
                    for (int i = 0; i < numberOf; i++) {
                        Prey instance = new Prey(name, energy, size, speed, visionRange, reproductionRate, calories, color);
                        simulation.addInstance(instance);
                    }
                }
            }
        });

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                JDialog.setDefaultLookAndFeelDecorated(true);
                int response = JOptionPane.showConfirmDialog(null, "Would you like to run the simulation with graphics?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                simulation.start(response == JOptionPane.YES_OPTION);
            }
        });

        add(panel, BorderLayout.CENTER);
        add(add, BorderLayout.EAST);
        add(start, BorderLayout.SOUTH);

    }

}
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import Simulation.Simulation;
import Instances.*;

public class CustomSetup extends JFrame{

    Simulation simulation;

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
        JLabel calories = new JLabel("Calories (Prey only)");

        //Create combo box: carnivorous, omnivore, prey
        //Create input boxes for name, energy, size, speed, vision range, and reproduction rate

        JComboBox<String> typeBox = new JComboBox<String>();
        typeBox.addItem("Carnivorous");
        typeBox.addItem("Omnivore");
        typeBox.addItem("Prey");

        //Create input boxes for name, energy, size, speed, vision range, and reproduction rate
        JTextField nameInput = new JTextField();
        JTextField energyInput = new JTextField();
        JTextField sizeInput = new JTextField();
        JTextField speedInput = new JTextField();
        JTextField visionRangeInput = new JTextField();
        JTextField reproductionRateInput = new JTextField();
        JTextField caloriesInput = new JTextField();


        //Create panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 2));
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
        panel.add(calories);
        panel.add(caloriesInput);

        setVisible(true);

        //Create button to add to simulation
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
                //Get values from input boxes
                try{
                name = nameInput.getText();
                energy = Float.parseFloat(energyInput.getText());
                size = Integer.parseInt(sizeInput.getText());
                speed = Float.parseFloat(speedInput.getText());
                visionRange = Float.parseFloat(visionRangeInput.getText());
                reproductionRate = Float.parseFloat(reproductionRateInput.getText());
                if(typeBox.getSelectedItem().equals("Prey"))
                    calories = Float.parseFloat(caloriesInput.getText());
                    
                } catch (Exception ex) {
                    //Error dialog
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
                //Create instance based on type
                if (typeBox.getSelectedItem().equals("Carnivorous")) {
                    Carnivorous instance = new Carnivorous(name, energy, size, speed, visionRange, reproductionRate);
                    simulation.addInstance(instance);
                } else if (typeBox.getSelectedItem().equals("Omnivore")) {
                    Omnivore instance = new Omnivore(name, energy, size, speed, visionRange, reproductionRate);
                    simulation.addInstance(instance);
                } else if (typeBox.getSelectedItem().equals("Prey")) {
                    
                    Prey instance = new Prey(name, energy, size, speed, visionRange, reproductionRate, calories);
                    simulation.addInstance(instance);
                }
            }
        });

        //Start button
        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Start simulation
                simulation.start();
            }
        });

        //Add panel and buttons to frame
        add(panel, BorderLayout.CENTER);
        add(add, BorderLayout.EAST);
        add(start, BorderLayout.SOUTH);




    }
    
    
}

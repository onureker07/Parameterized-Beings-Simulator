package GUI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Simulation.Simulation;
public class CreateSimulation extends JFrame{
    
    private Simulation simulation;

    public CreateSimulation() {
        super("Create Simulation");
        simulation = new Simulation();
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create labels and input boxes for food density, food calories, and food color, and world size
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        JLabel foodDensityLabel = new JLabel("Food Density");
        JLabel foodCaloriesLabel = new JLabel("Food Calories");
        JLabel foodColorLabel = new JLabel("Food Color");
        JLabel worldSizeLabel = new JLabel("World Size");
        //Restrict input to numbers except for food color. Make food color a combo box with options for red, green, blue, and yellow.
        //default values are variables at the top of the class
        JTextField foodDensityInput = new JTextField(Float.toString(simulation.foodDensity));
        JTextField foodCaloriesInput = new JTextField(Float.toString(simulation.foodCalories));
        String[] colors = {"Red", "Green", "Blue", "Yellow"};
        JComboBox<String> foodColorInput = new JComboBox<String>(colors);
        JTextField worldSizeInput = new JTextField(Integer.toString(simulation.worldSize));
        
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
                    simulation.worldSize = Integer.parseInt(worldSizeInput.getText());
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
                }

                else {
                    JOptionPane.showMessageDialog(null, "Please enter valid inputs");
                }
            }
        });

    }

    public boolean checkInput(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

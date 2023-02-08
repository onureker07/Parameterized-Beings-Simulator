package Simulation;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Instances.*;
/**
    * InfoScreen class is a JFrame that shows the information of the simulation.
    * It is used to show the information of the simulation in a table.
    * It is updated every time the simulation is updated.
    * It is also used to write the information in a file for later use.
 */
public class InfoScreen extends JFrame {
    /*
     * index is the time step of the simulation.
     */
    int index;
    /*
     * simulation is the simulation that is being shown.
     */
    Simulation simulation;
    /*
     * file is the file where the information is written.
     */
    File file;
    /**
        * Constructor of the InfoScreen class.
        * @param simulation is the simulation that is being shown.
     */
    public InfoScreen(Simulation simulation) {
        super("Info");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.simulation = simulation;

        index = 0;

        File folder = new File("Results");
        File[] listOfFiles = folder.listFiles();
        int numberOfFiles = 0;
        for (File f: listOfFiles) {
            if (f.isFile()) {
                numberOfFiles++;
            }
        }
        String fileName = "Results\\simulationInfo" + numberOfFiles + ".txt";
        file = new File(fileName);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("Index, Number of instances, Number of prey, Number of carnivorous, Average prey production, Average prey eaten, Average carnivorous production, Average carnivorous eaten");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /*
     * update() is the method that updates the information of the simulation.
     * It removes all the components from the JFrame and creates a new JPanel with GridLayout(5, 2).
     * It then creates JLabels for the information of the simulation.
     * It then adds the JLabels to the JPanel.
     * It then adds the JPanel to the JFrame.
     * It then writes the information in the file.
     */

    public void update() {

        getContentPane().removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel instances = new JLabel("Number of instances: " + simulation.beings.size());

        int carnivorous = 0;
        int prey = 0;
        int preyTotalProduction = 0;
        int carnivorousTotalProduction = 0;
        int preyTotalEaten = 0;
        int carnivorousTotalEaten = 0;
        for (Being b: simulation.beings) {
            if (b instanceof Carnivorous) {
                carnivorous++;
                carnivorousTotalProduction += b.numberOfProduction;
                carnivorousTotalEaten += b.numberOfEaten;
            } else if (b instanceof Prey) {
                prey++;
                preyTotalProduction += b.numberOfProduction;
                preyTotalEaten += b.numberOfEaten;
            }
        }

        double preyAverageProduction = (double) preyTotalProduction / (double) prey;
        double preyAverageEaten = (double) preyTotalEaten / (double) prey;
        double carnivorousAverageProduction = (double) carnivorousTotalProduction / (double) carnivorous;
        double carnivorousAverageEaten = (double) carnivorousTotalEaten / (double) carnivorous;
        preyAverageProduction = Math.round(preyAverageProduction * 100.0) / 100.0;
        preyAverageEaten = Math.round(preyAverageEaten * 100.0) / 100.0;
        carnivorousAverageProduction = Math.round(carnivorousAverageProduction * 100.0) / 100.0;
        carnivorousAverageEaten = Math.round(carnivorousAverageEaten * 100.0) / 100.0;

        JLabel carnivorousLabel = new JLabel("Number of carn.: " + carnivorous);
        JLabel preyLabel = new JLabel("Number of prey: " + prey);
        JLabel preyProductionLabel = new JLabel("Av. prey prod.: " + preyAverageProduction);
        JLabel carnivorousProductionLabel = new JLabel("Av. carn. prod.: " + carnivorousAverageProduction);
        JLabel preyEatenLabel = new JLabel("Av. prey eaten: " + preyAverageEaten);
        JLabel carnivorousEatenLabel = new JLabel("Av. carn. eaten: " + carnivorousAverageEaten);

        JLabel timeStepLabel = new JLabel("Time step: " + Simulation.timestep);
        panel.add(instances);
        panel.add(carnivorousLabel);
        panel.add(preyLabel);
        panel.add(preyProductionLabel);
        panel.add(carnivorousProductionLabel);
        panel.add(preyEatenLabel);
        panel.add(carnivorousEatenLabel);
        panel.add(timeStepLabel);
        add(panel, BorderLayout.CENTER);
        setVisible(true);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(index + ", " + simulation.beings.size() + ", " + prey + ", " + carnivorous + ", " + preyAverageProduction + ", " + preyAverageEaten + ", " + carnivorousAverageProduction + ", " + carnivorousAverageEaten);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        index++;

    }

}
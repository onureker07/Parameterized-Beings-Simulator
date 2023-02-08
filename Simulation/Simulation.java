package Simulation;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import Instances.*;
/**
    * Simulation class is the main class of the simulation.
    * It is used to create the simulation and run it.
    * It is also used to add and remove instances from the simulation.
 */
public class Simulation extends JFrame {
    /*
     * foodDensity is the density of the food in the simulation.
     */
    public float foodDensity = 0.04f;
    /*
     * foodCalories is the amount of calories that the food has.
     */
    public float foodCalories = 45000;
    /*
     * foodColor is the color of the food.
     */ 
    public Color foodColor = Color.GREEN;
    /*
     * worldSize is the size of the world.
     */
    public static int worldSize = 250;
    /*
     * beings is the list of all the beings in the simulation.
     */
    public ArrayList < Being > beings = new ArrayList < Being > ();
    /*
     * foods is the list of all the food in the simulation.
     */
    public ArrayList < Food > foods = new ArrayList < Food > ();
    /*
     * adder is the list of all the beings that are to be added to the simulation.
     */
    public static ArrayList < Being > adder = new ArrayList < Being > ();
    /*
     * remover is the list of all the beings that are to be removed from the simulation.
     */
    public static ArrayList < Being > remover = new ArrayList < Being > ();
    /*
     * panel is the panel that the simulation is drawn on.
     */
    public JPanel panel;
    /*
     * g is the graphics object that is used to draw the simulation.
     */
    public Graphics g;
    /*
     * running is a boolean that is used to check if the simulation is running.
     */
    public static boolean running = false;
    /*
     * timer is the timer that is used to run the simulation.
     */
    private Timer timer;
    /*
     * bufferStrategy is the buffer strategy that is used to draw the simulation.
     */
    private BufferStrategy bufferStrategy;
    /*
     * info is the info screen that is used to show the information of the simulation.
     */
    private InfoScreen info;
    /*
     * showInfo is a boolean that is used to check if the info screen is shown.
     */
    private boolean showInfo = false;
    /*
     * timestep is the timestep of the simulation.
     */
    public static int timestep;
    /*
     * FPS is the frames per second of the simulation.
     */
    public final static int FPS = 120;

    /**
        * addInstance is used to add an instance to the simulation. It adds a being to the beings arraylist.
     */
    public void addInstance(Being being) {
        beings.add(being);
    }

    /**
        * The constructor of the simulation class.
        * It sets the title of the simulation.
        * It also sets the timestep to 0.
     */
    public Simulation() {
        super("Simulation");
        panel = new JPanel();
        timestep = 0;
    }

    /*
     * start is used to start the simulation.
     * It creates the info screen.
     * It sets the size of the simulation.
     * It sets the default close operation.
     * It sets the simulation to be visible.
     * It creates a buffer strategy.
     * It sets the background color of the simulation to black.
     * It sets the buffer strategy.
     * It creates a timer.
     * It adds the panel to the simulation.
     * It creates the foods.
     * It starts the timer and sets running to true.
     */

    public void start(boolean showInfo) {

        info = new InfoScreen(this);
        setSize(worldSize, worldSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);

        setVisible(true);
        createBufferStrategy(2);
        setBackground(Color.BLACK);
        bufferStrategy = getBufferStrategy();
        timer = new Timer(1000 / FPS, new Runner());

        this.showInfo = showInfo;
        add(panel);

        for (int i = 0; i < worldSize; i++) {
            for (int j = 0; j < worldSize; j++) {
                if (Math.random() < foodDensity) {
                    Food food = new Food(foodCalories, foodColor, this);
                    foods.add(food);
                }
            }
        }

        timer.start();
        running = true;
    }
    /*
     * Runner is a class that is used to run the simulation.
     * It implements the ActionListener interface.
     * It has a method called actionPerformed that is called when the timer ticks.
     */
    private class Runner implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            run();
        }
    }

    /**
        * numberOfPrey is used for getting the number of prey in the simulation.
        * @return the number of prey in the simulation.
     */
    public int numberOfPrey() {
        int count = 0;
        for (Being being: beings) {
            if (being instanceof Prey) {
                count++;
            }
        }
        return count;
    }

    /**
        * numberOfCarnivorous is used for getting the number of carnivorous beings in the simulation.
        * @return the number of carnivorous beings in the simulation.
     */
    public int numberOfCarnivorous() {
        int count = 0;
        for (Being being: beings) {
            if (being instanceof Carnivorous) {
                count++;
            }
        }
        return count;
    }

    /**
     * run method is used with the timer to run the simulation.
     * It updates the simulation.
     * It draws the simulation.
     * It checks if the simulation is over.
     * If the simulation is over it stops the timer and disposes of the simulation.
     */
    public void run() {
        timestep++;
        update();
        if (showInfo) {
            draw();
        }
        if (numberOfPrey() == 0 || numberOfCarnivorous() == 0) {
            timer.stop();
            dispose();
            info.dispose();
            running = false;

        }
    }
    /*
     * update is used to update the simulation.
     * It updates all the beings.
     * It adds all the beings in the adder arraylist to the beings arraylist.
     * It removes all the beings in the remover arraylist from the beings arraylist.
     * It creates new food if there is not enough food according to the food density.
     * It clears the adder and remover arraylists.
     * It updates the info screen.
     */
    public void update() {
        for (Being being: beings) {
            being.update(beings, foods, worldSize);
        }
        for (Being being: adder) {
            beings.add(being);
        }
        for (Being being: remover) {
            beings.remove(being);
        }

        while (foods.size() < foodDensity * worldSize * worldSize) {
            Food food = new Food(foodCalories, foodColor, this);
            foods.add(food);
        }

        adder.clear();
        remover.clear();
        info.update();
    }

    /**
     * draw is used to draw the simulation.
     * It clears the screen.
     * It draws all the food.
     * It draws all the beings.
     * It disposes of the graphics.
     * It shows the buffer strategy.
     */
    public void draw() {
        g = bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        for (Food food: foods) {
            food.draw(g);
        }
        for (Being being: beings) {
            being.draw(g);
        }

        g.dispose();
        bufferStrategy.show();
    }
}
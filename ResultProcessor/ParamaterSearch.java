package ResultProcessor;

import java.awt.Color;

import Instances.Carnivorous;
import Instances.Prey;
import Simulation.Simulation;
/*
 * ParamaterSearch class is the class that is used to search for the best paramaters for the simulation.
 */
public class ParamaterSearch extends Thread {
    /*
     * In the main method, the min and max bounds of the paramaters are defined.
     * Then, parameters are randomly generated within the bounds.
     * The simulation is then run with the generated parameters.
     */
    public static void main(String[] args) {
        float min_carn_energy = 100000.0f;
        float max_carn_energy = 500000.0f;

        float min_prey_energy = 100000.0f;
        float max_prey_energy = 500000.0f;

        float min_carn_speed = 6.1f;
        float max_carn_speed = 10.0f;

        float min_prey_speed = 3.0f;
        float max_prey_speed = 6.0f;

        int min_carn_size = 7;
        int max_carn_size = 9;

        int min_prey_size = 5;
        int max_prey_size = 7;

        float min_carn_vision = 1.0f;
        float max_carn_vision = 100.0f;

        float min_prey_vision = 1.0f;
        float max_prey_vision = 100.0f;

        float min_carn_reproduction = 0.0002f;
        float max_carn_reproduction = 0.001f;

        float min_prey_reproduction = 0.003f;
        float max_prey_reproduction = 0.005f;

        int min_carn_number = 50;
        int max_carn_number = 100;

        int min_prey_number = 100;
        int max_prey_number = 200;

        float min_prey_calories = 40000.0f;
        float max_prey_calories = 80000.0f;

        float min_food_calories = 30000.0f;
        float max_food_calories = 60000.0f;

        float min_food_density = 0.01f;
        float max_food_density = 0.05f;

        while (true) {
            Simulation sim = new Simulation();

            sim.foodCalories = (float)(Math.random() * (max_food_calories - min_food_calories) + min_food_calories);

            sim.foodDensity = (float)(Math.random() * (max_food_density - min_food_density) + min_food_density);

            int prey_number = (int)(Math.random() * (max_prey_number - min_prey_number) + min_prey_number);

            int carn_number = (int)(Math.random() * (max_carn_number - min_carn_number) + min_carn_number);

            float prey_calories = (float)(Math.random() * (max_prey_calories - min_prey_calories) + min_prey_calories);

            float carn_energy = (float)(Math.random() * (max_carn_energy - min_carn_energy) + min_carn_energy);

            float prey_energy = (float)(Math.random() * (max_prey_energy - min_prey_energy) + min_prey_energy);

            float carn_speed = (float)(Math.random() * (max_carn_speed - min_carn_speed) + min_carn_speed);

            float prey_speed = (float)(Math.random() * (max_prey_speed - min_prey_speed) + min_prey_speed);

            int carn_size = (int)(Math.random() * (max_carn_size - min_carn_size) + min_carn_size);

            int prey_size = (int)(Math.random() * (max_prey_size - min_prey_size) + min_prey_size);

            float carn_vision = (float)(Math.random() * (max_carn_vision - min_carn_vision) + min_carn_vision);

            float prey_vision = (float)(Math.random() * (max_prey_vision - min_prey_vision) + min_prey_vision);

            float carn_reproduction = (float)(Math.random() * (max_carn_reproduction - min_carn_reproduction) + min_carn_reproduction);

            float prey_reproduction = (float)(Math.random() * (max_prey_reproduction - min_prey_reproduction) + min_prey_reproduction);

            for (int i = 0; i < prey_number; i++) {
                sim.addInstance(new Prey("Prey", prey_energy, prey_size, prey_speed, prey_vision, prey_reproduction, prey_calories, Color.MAGENTA));
            }

            for (int i = 0; i < carn_number; i++) {
                sim.addInstance(new Carnivorous("Carn", carn_energy, carn_size, carn_speed, carn_vision, carn_reproduction, Color.RED));
            }

            System.out.println("\n\n\nFood Calories: " + sim.foodCalories);
            System.out.println("Food Density: " + sim.foodDensity);
            System.out.println("Prey Number: " + prey_number);
            System.out.println("Carn Number: " + carn_number);
            System.out.println("Prey Calories: " + prey_calories);
            System.out.println("Carn Energy: " + carn_energy);
            System.out.println("Prey Energy: " + prey_energy);
            System.out.println("Carn Speed: " + carn_speed);
            System.out.println("Prey Speed: " + prey_speed);
            System.out.println("Carn Size: " + carn_size);
            System.out.println("Prey Size: " + prey_size);
            System.out.println("Carn Vision: " + carn_vision);
            System.out.println("Prey Vision: " + prey_vision);
            System.out.println("Carn Reproduction: " + carn_reproduction);
            System.out.println("Prey Reproduction: " + prey_reproduction);

            sim.start(true);

            while (Simulation.running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
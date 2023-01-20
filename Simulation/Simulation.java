package Simulation;
import java.util.ArrayList;
import java.awt.Color;
import Instances.*;
public class Simulation {
    public float foodDensity = 0.5f;
    public float foodCalories = 100;
    public Color foodColor = Color.GREEN;
    public int worldSize = 100;
    public ArrayList<Being> beings = new ArrayList<Being>();

    public void addInstance(Being being) {
        beings.add(being);
    }

    public void start() {
        //Start simulation
    }
}

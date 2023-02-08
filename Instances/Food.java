package Instances;
import java.awt.Color;
import java.awt.Graphics;
import Simulation.Simulation;
import java.awt.*;

/**
 * Food class is a class that represents food in the simulation. Foods are eaten by Preys.
 * It implements Nutrition interface.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class Food implements Nutrition {
    /**
     * The amount of calories that the food has.
     */
    private float calories;
    /**
     * The color of the food.
     */
    private Color color;
    /**
     * The x coordinate of the food.
     */
    private int x;
    /**
     * The y coordinate of the food.
     */
    private int y;
    /**
     * The rectangle that represents the food.
     */
    private Rectangle rectangle;

    /**
     * The constructor of the Food class.
     * It takes the amount of calories and the color of the food as parameters.
     * It then randomly generates a coordinate for the food and creates a rectangle with the coordinate.
     * It then checks if the rectangle intersects with any other food's rectangle.
     * If it does, it generates a new coordinate and creates a new rectangle.
     * It does this until it finds a coordinate that does not intersect with any other food's rectangle.
     * @param calories The amount of calories that the food has.
     * @param color The color of the food.
     * @param simulation The simulation object that the food will be added to.
     * @see Simulation
     */
    public Food(float calories, Color color, Simulation simulation) {
        this.calories = calories;
        this.color = color;

        do {
            this.x = (int)(Math.random() * Simulation.worldSize);
            this.y = (int)(Math.random() * Simulation.worldSize);
            rectangle = new Rectangle(x, y, 1, 1);
        } while (simulation.foods.stream().anyMatch(food -> food.getRectangle().intersects(rectangle)));
    }

    /**
     * A getter method for the amount of calories that the food has.
     * @return The amount of calories that the food has.
     */
    public float getCalories() {
        return calories;
    }

    /**
     * A getter method for the rectangle that represents the food.
     * @return The rectangle that represents the food.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * A getter method for the color of the food.
     * @return The color of the food.
     */
    public Color getColor() {
        return color;
    }

    /**
     * A getter method for the x coordinate of the food.
     * @return The x coordinate of the food.
     */
    public int getX() {
        return x;
    }

    /**
     * A getter method for the y coordinate of the food.
     * @return The y coordinate of the food.
     */
    public int getY() {
        return y;
    }

    /**
     * A method that checks if the food can be eaten by the being that is passed as a parameter. 
     * It returns true if the being is a Prey, false otherwise.
     * @param being The being that the food will be checked if it can be eaten by.
     * @return True if the food can be eaten by the being, false otherwise.
     */
    public boolean canBeEatenBy(Being being) {
        if (being instanceof Prey) {
            return true;
        }
        return false;
    }

    /**
     * A method that draws the food on the screen with the color that it has. Size of the food is 1x1.
     * @param graphic The graphic object that the food will be drawn on.
     */
    public void draw(Graphics graphic) {

        graphic.setColor(color);
        graphic.fillRect(x, y, 1, 1);
    }

}
package Instances;
import  java.awt.Color;
import  java.awt.*;

/**
 * Nutrition interface is an interface that represents the nutrition of the beings.
 * It has 4 methods: getCalories, canBeEatenBy, getColor and getRectangle.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public interface Nutrition {
    /**
     * The method that returns the amount of calories that the nutrition has.
     * @return The amount of calories that the nutrition has.
     */
    public float getCalories();
    /**
     * The method that checks if the nutrition can be eaten by the being.
     * @param being The being that will eat the nutrition.
     * @return True if the nutrition can be eaten by the being, false otherwise.
     */
    public boolean canBeEatenBy(Being being);
    /**
     * The method that returns the color of the nutrition.
     * @return The color of the nutrition.
     */
    public Color getColor();
    /**
     * The method that returns the rectangle of the nutrition.
     * @return The rectangle of the nutrition.
     */
    public Rectangle getRectangle();
}

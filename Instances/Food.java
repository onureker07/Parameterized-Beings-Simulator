package Instances;
import java.awt.Color;

public class Food implements Nutrition {
    private float calories;
    private Color color;
    
    public Food(float calories, Color color) {
        this.calories = calories;
        this.color = color;
    }
    
    public float getCalories() {
        return calories;
    }

    public Color getColor() {
        return color;
    }
    
    public boolean canBeEatenBy(Being being) {
        if (being instanceof Omnivore || being instanceof Prey) {
            return true;
        }
        return false;
    }
    
}
    

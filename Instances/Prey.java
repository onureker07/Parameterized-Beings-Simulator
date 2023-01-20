package Instances;
public class Prey extends Being implements Nutrition {
    
    private float calories;

    public Prey(String name , float energy, int size, float speed, float visionRange, float reproductionRate, float calories) {
        super(name, energy, size, speed, visionRange, reproductionRate);
        this.calories = calories;
    }
    
    public float getCalories() {
        return calories;
    }
    
    public boolean canBeEatenBy(Being being) {
        if (being instanceof Omnivore || being instanceof Carnivorous) {
            return true;
        }
        return false;
    }
}

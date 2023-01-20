package Instances;
public class Being {
    private String name;
    private float energy;

    private int size;
    private float speed;
    private float visionRange;
    private float reproductionRate;

    public Being(String name, float energy, int size, float speed, float visionRange, float reproductionRate) {
        this.name = name;
        this.energy = energy;
        this.size = size;
        this.speed = speed;
        this.visionRange = visionRange;
        this.reproductionRate = reproductionRate;
    }


    public String getName() {
        return name;
    }

    public float getEnergy() {
        return energy;
    }

    public int getSize() {
        return size;
    }

    public float getSpeed() {
        return speed;
    }
    
    public float getVisionRange() {
        return visionRange;
    }

    public float getReproductionRate() {
        return reproductionRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setVisionRange(float visionRange) {
        this.visionRange = visionRange;
    }

    public void setReproductionRate(float reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    public boolean eat(Nutrition nutrition) {
        if (nutrition.canBeEatenBy(this)) {
            this.energy += nutrition.getCalories();
            return true;
        }
        return false;
    }

    public void move() {
        // this is going to be implemented in the subclasses
    }
}
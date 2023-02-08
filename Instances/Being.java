package Instances;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.*;

import Simulation.Simulation;

/**
 * Being class is the superclass of all beings in the simulation.
 * It contains all the common properties of beings.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class Being {
    /**
     * The name of the being.
    */
    private String name;
    /**
     * The energy of the being.
    */
    private float energy;
    /**
     * The number of beings that the being has produced. By default, it is 0.
    */
    public int numberOfProduction = 0;
    /**
     * The number of beings that the being has eaten. By default, it is 0.
    */
    public int numberOfEaten = 0;
    /**
     * The size of the being.
    */
    private int size;
    /**
     * The speed of the being.
    */
    private float speed;
    /**
     * The vision range of the being.
    */
    private float visionRange;
    /**
     * The reproduction rate of the being.
    */
    private float reproductionRate;
    /**
     * The color of the being.
    */
    private Color color;
    /**
     * The x coordinate of the being.
    */
    private int x;
    /**
     * The y coordinate of the being.
    */
    private int y;
    /**
     * The boolean that determines whether the being can move or not. By default, it is true.
    */
    private boolean canMove = true;
    /**
     * The frame counter that determines how many frames the being has to wait before moving again. By default, it is 0.
    */
    private int frameCounterToMove = 0;
    /**
     * The rectangle that is used to check collisions.
    */
    private Rectangle rectangle;
    /**
     * The direction that the being is moving in. By default, it is a random direction.
    */
    private Direction direction = Direction.values()[(int)(Math.random() * Direction.values().length)];

    /**
     * The constructor of the Being class.
     * @param name The name of the being.
     * @param energy The energy of the being.
     * @param size The size of the being.
     * @param speed The speed of the being.
     * @param visionRange The vision range of the being.
     * @param reproductionRate The reproduction rate of the being.
     * @param color The color of the being.
    */
    public Being(String name, float energy, int size, float speed, float visionRange, float reproductionRate, Color color) {
        this.name = name;
        this.energy = energy;
        this.size = size;
        this.speed = speed;
        this.visionRange = visionRange;
        this.reproductionRate = reproductionRate;
        this.color = color;

        this.x = (int)(Math.random() * Simulation.worldSize);
        this.y = (int)(Math.random() * Simulation.worldSize);
        this.rectangle = new Rectangle(x, y, size, size);
    }

    /**
     * A getter method for the rectangle.
     * @return The rectangle of the being.
    */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * A setter method for the rectangle.
     * @param rectangle The rectangle of the being.
    */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * A setter method for the canMove variable.
     * @param canMove The boolean that determines whether the being can move or not.
    */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    /**
     * A getter method for the frameCounterToMove variable.
     * @return The frame counter that determines how many frames the being has to wait before moving again.
    */
    public int getFrameCounterToMove() {
        return frameCounterToMove;
    }

    /**
     * A setter method for the frameCounterToMove variable.
     * @param frameCounterToMove The frame counter that determines how many frames the being has to wait before moving again.
    */
    public void setFrameCounterToMove(int frameCounterToMove) {
        this.frameCounterToMove = frameCounterToMove;
    }

    /**
     * A getter method for the canMove variable.
     * @return The boolean that determines whether the being can move or not.
    */
    public boolean canMove() {
        return canMove;
    }

    /**
     * A setter method for the direction variable.
     * @param direction The direction that the being is moving in.
    */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * A getter method for the direction variable.
     * @return The direction that the being is moving in.
    */
    public Direction getDirection() {
        return direction;
    }

    /**
     * A getter method for the X coordinate of the being.
     * @return The X coordinate of the being.
    */
    public int getX() {
        return x;
    }

    /**
     * A getter method for the Y coordinate of the being.
     * @return The Y coordinate of the being.
    */
    public int getY() {
        return y;
    }

    /**
     * A setter method for the X coordinate of the being. It also updates the rectangle.
     * @param x The X coordinate of the being.
    */
    public void setX(int x) {
        this.x = x;

        rectangle = new Rectangle(x, y, size, size);
    }

    /**
     * A setter method for the Y coordinate of the being. It also updates the rectangle.
     * @param y The Y coordinate of the being.
    */
    public void setY(int y) {
        this.y = y;

        rectangle = new Rectangle(x, y, size, size);
    }

    /**
     * A getter method for the name of the being.
     * @return The name of the being.
    */
    public String getName() {
        return name;
    }

    /**
     * A getter method for the energy of the being.
     * @return The energy of the being.
    */
    public float getEnergy() {
        return energy;
    }

    /**
     * A getter method for the size of the being.
     * @return The size of the being.
    */
    public int getSize() {
        return size;
    }

    /**
     * A getter method for the speed of the being.
     * @return The speed of the being.
    */
    public float getSpeed() {
        return speed;
    }

    /**
     * A getter method for the vision range of the being.
     * @return The vision range of the being.
    */
    public float getVisionRange() {
        return visionRange;
    }

    /**
     * A getter method for the reproduction rate of the being.
     * @return The reproduction rate of the being.
    */
    public float getReproductionRate() {
        return reproductionRate;
    }

    /**
     * A getter method for the color of the being.
     * @return The color of the being.
    */
    public Color getColor() {
        return color;
    }

    /**
     * A setter method for the name of the being.
     * @param name The name of the being.
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A setter method for the energy of the being.
     * @param energy The energy of the being.
    */
    public void setEnergy(float energy) {
        this.energy = energy;
    }

    /**
     * A setter method for the size of the being.
     * @param size The size of the being.
    */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * A setter method for the speed of the being.
     * @param speed The speed of the being.
    */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * A setter method for the vision range of the being.
     * @param visionRange The vision range of the being.
    */
    public void setVisionRange(float visionRange) {
        this.visionRange = visionRange;
    }

    /**
     * A setter method for the reproduction rate of the being.
     * @param reproductionRate The reproduction rate of the being.
    */
    public void setReproductionRate(float reproductionRate) {
        this.reproductionRate = reproductionRate;
    }

    /**
     * This method is used for the being to eat a piece of Nutrition. 
     * It checks if the being can eat the nutrition, and if it can, it adds the nutrition's calories to the being's energy.
     * @param nutrition The nutrition that the being is trying to eat.
     * @return A boolean that determines whether the being was able to eat the nutrition or not.
    */
    public boolean eat(Nutrition nutrition) {
        if (nutrition.canBeEatenBy(this)) {
            this.energy += nutrition.getCalories();
            this.numberOfEaten++;
            return true;
        }

        return false;
    }

    /**
     * This method is used for the update Beings during the simulation. This method is implemented in the subclasses.
     * @param beings The beings in the world.
     * @param foods The foods in the world.
     * @param worldSize The size of the world.
     */
    public void update(ArrayList < Being > beings, ArrayList < Food > foods, int worldSize) {

    }

    /**
     * This method is used for the being to move. This method is implemented in the subclasses.
     * @param beings The beings in the world.
     * @param foods The foods in the world.
     * @param worldSize The size of the world.
     * @return A boolean that determines whether the being was able to move or not.
    */
    public boolean move(ArrayList < Being > beings, ArrayList < Food > foods, int worldSize) {

        return false;
    }

    /**
     * This method is used for the being to reproduce. This method is implemented in the subclasses.
     * @param beings The beings in the world.
    */
    public void reproduce(ArrayList < Being > beings) {

    }

    /**
     * This method is used for drawing the being. It draws a rectangle with the being's color and size.
     * @param g The graphics object coming from the Simulation class.
    */
    public void draw(Graphics g) {

        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    /**
     * Direction enum class is used for the being to move in a certain direction.
     * It has four directions: UP, DOWN, LEFT, RIGHT.
     * It is used in the move method in the subclasses.
     * @see Carnivorous#move(ArrayList, ArrayList, int)
     * @see Prey#move(ArrayList, ArrayList, int)
     * 
     * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
     * @version 1.0
     * @since 2023-02-05
     */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

}
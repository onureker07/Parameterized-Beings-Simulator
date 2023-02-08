package Instances;
import java.awt.Color;
import java.util.ArrayList;

import Simulation.Simulation;
import java.awt.Rectangle;
/**
 * Prey class is a subclass of Being class and implements Nutrition interface. 
 * It is a being that is eaten by Carnivorous and eats Food.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class Prey extends Being implements Nutrition {

    /**
     * The amount of calories that prey gives when eaten.
     */
    private float calories;

    /**
     * The constructor of the Prey class.
     * It calls the constructor of the Being class with the given parameters.
     * It then sets the calories of the Prey.
     * @param name The name of the Prey.
     * @param energy The energy of the Prey.
     * @param size The size of the Prey.
     * @param speed The speed of the Prey.
     * @param visionRange The vision range of the Prey.
     * @param reproductionRate The reproduction rate of the Prey.
     * @param calories The amount of calories that the Prey gives when eaten.
     * @param color The color of the Prey.
     * @see Being
     */
    public Prey(String name, float energy, int size, float speed, float visionRange, float reproductionRate, float calories, Color color) {
        super(name, energy, size, speed, visionRange, reproductionRate, color);
        this.calories = calories;
    }

    /**
     * A getter method for the calories of the Prey.
     * @return The amount of calories that the Prey gives when eaten.
     */
    public float getCalories() {
        return calories;
    }

    /**
     * A method that checks if the prey can be eaten by the being that is passed as a parameter. 
     * It returns true if the being is a Carnivorous, false otherwise.
     * @param being The being that the food will be checked if it can be eaten by.
     * @return True if the food can be eaten by the being, false otherwise.
     */
    public boolean canBeEatenBy(Being being) {
        if (being instanceof Carnivorous) {
            return true;
        }
        return false;
    }

    /**
     * The update method of the Prey class.
     * It checks if it can move and updates energy accordingly.
     * It then checks if it intersects with any food and eats them if it does.
     * It then checks if it has any energy left. If it doesn't, it dies and returns.
     * It then checks if it call reproduce method.
     * @param beings The beings arraylist.
     * @param foods The foods arraylist.
     * @param worldSize The size of the world.
     * @see Being
     */
    public void update(ArrayList < Being > beings, ArrayList < Food > foods, int worldSize) {
        if (!move(beings, foods, worldSize))
            setEnergy(getEnergy() - getSize() * getSize());

        for (Food food: foods) {

            if (this.getRectangle().intersects(food.getRectangle())) {
                if (eat(food)) {
                    foods.remove(food);
                    break;
                }
            }
        }
        if (getEnergy() <= 0) {
            Simulation.remover.add(this);
            return;
        }
        reproduce(beings);
    }

    /**
     * The move method of the prey class.
     * It checks if it is time to move. If it isn't, it returns false.
     * It then checks if there is any carnivorous in its vision range. If there is, sets the direction away from the carnivorous.
     * If there isn't, checks if there is any food in its vision range. If there is, sets the direction towards the food.
     * If there isn't, sets the direction randomly.
     * It then checks if it can move to the direction it is facing. If it can, it moves.
     * 
     * @param beings The beings arraylist.
     * @param foods The foods arraylist.
     * @param worldSize The size of the world.
     * @return True if it moved, false if it didn't.
     * @see Being
     */
    public boolean move(ArrayList < Being > beings, ArrayList < Food > foods, int worldSize) {
        if (!canMove()) {
            setFrameCounterToMove(getFrameCounterToMove() - 1);
            if (getFrameCounterToMove() == 0) {
                setCanMove(true);
            }
            return false;
        }

        boolean isThereAttacker = false;
        float closestAttackerDistance = Float.MAX_VALUE;
        for (Being being: beings) {
            float distance = (float) Math.sqrt(Math.pow(this.getX() - being.getX(), 2) + Math.pow(this.getY() - being.getY(), 2));
            if (closestAttackerDistance < distance)
                continue;
            if (this.canBeEatenBy(being)) {

                Rectangle temp = new Rectangle(this.getX() - (int) this.getVisionRange(), this.getY() - (int) this.getVisionRange(), this.getSize() + (int) this.getVisionRange() * 2, this.getSize() + (int) this.getVisionRange() * 2);
                if (temp.intersects(being.getRectangle())) {

                    if (being.getY() > this.getY()) {
                        this.setDirection(Direction.UP);
                    } else if (being.getY() < this.getY()) {
                        this.setDirection(Direction.DOWN);
                    } else if (being.getX() > this.getX()) {
                        this.setDirection(Direction.LEFT);
                    } else if (being.getX() < this.getX()) {
                        this.setDirection(Direction.RIGHT);
                    }
                    closestAttackerDistance = distance;
                    isThereAttacker = true;
                }
            }
        }

        if (!isThereAttacker) {

            boolean isThereFood = false;
            float closestFoodDistance = Float.MAX_VALUE;

            for (Food food: foods) {
                float distance = (float) Math.sqrt(Math.pow(this.getX() - food.getX(), 2) + Math.pow(this.getY() - food.getY(), 2));
                if (closestFoodDistance < distance)
                    continue;

                Rectangle temp = new Rectangle(this.getX() - (int) this.getVisionRange(), this.getY() - (int) this.getVisionRange(), this.getSize() + (int) this.getVisionRange() * 2, this.getSize() + (int) this.getVisionRange() * 2);
                if (temp.intersects(food.getRectangle())) {

                    if (food.getY() > this.getY()) {
                        this.setDirection(Direction.DOWN);
                    } else if (food.getY() < this.getY()) {
                        this.setDirection(Direction.UP);
                    } else if (food.getX() > this.getX()) {
                        this.setDirection(Direction.RIGHT);
                    } else if (food.getX() < this.getX()) {
                        this.setDirection(Direction.LEFT);
                    }
                    isThereFood = true;
                    closestFoodDistance = distance;
                }
            }
            if (!isThereFood) {

                int random = (int)(Math.random() * 4);
                switch (random) {
                case 0:
                    this.setDirection(Direction.UP);
                    break;
                case 1:
                    this.setDirection(Direction.DOWN);
                    break;
                case 2:
                    this.setDirection(Direction.LEFT);
                    break;
                case 3:
                    this.setDirection(Direction.RIGHT);
                    break;
                }
            }
        }

        switch (this.getDirection()) {
        case UP:

            if (this.getY() > 0) {

                for (Being being: beings) {
                    Rectangle temp = new Rectangle(this.getX(), (this.getY() - 1), this.getSize(), this.getSize());
                    if (being != this && temp.intersects(being.getRectangle())) {
                        return false;
                    }
                }
                this.setY(this.getY() - 1);
            } else return false;
            break;
        case DOWN:

            if (this.getY() < worldSize - 1) {

                for (Being being: beings) {
                    Rectangle temp = new Rectangle(this.getX(), (this.getY() + 1), this.getSize(), this.getSize());
                    if (being != this && temp.intersects(being.getRectangle())) {

                        return false;
                    }
                }
                this.setY(this.getY() + 1);
            } else return false;
            break;
        case LEFT:

            if (this.getX() > 0) {

                for (Being being: beings) {
                    Rectangle temp = new Rectangle(this.getX() - 1, this.getY(), this.getSize(), this.getSize());
                    if (being != this && temp.intersects(being.getRectangle())) {

                        return false;
                    }
                }
                this.setX(this.getX() - 1);
            } else return false;
            break;
        case RIGHT:

            if (this.getX() < worldSize - 1) {

                for (Being being: beings) {
                    Rectangle temp = new Rectangle(this.getX() + 1, this.getY(), this.getSize(), this.getSize());
                    if (being != this && temp.intersects(being.getRectangle())) {

                        return false;
                    }
                }
                this.setX(this.getX() + 1);
            } else return false;
            break;

        }

        setCanMove(false);
        setFrameCounterToMove(Math.round(Simulation.FPS / this.getSpeed()));

        this.setEnergy(this.getEnergy() - 0.5f * this.getSize() * this.getSize() * this.getSpeed() * this.getSpeed());
        return true;
    }

    /**
     * The reproduce method of the Prey class.
     * It checks if the Prey has enough energy to reproduce, and if it does, it creates a new Prey with half of the energy of the Prey.
     * It searches for an empty space around the Prey and creates a new Prey there.
     * If it can't find an empty space, it doesn't reproduce.
     * It also halves the energy of the Prey.
     * 
     * @param beings The beings arraylist.
     * @see Being
     */
    public void reproduce(ArrayList < Being > beings) {

        if (this.getEnergy() >= this.getSize() * this.getSize() * this.getSpeed() * this.getSpeed() * 2) {

            if (Math.random() < this.getReproductionRate()) {

                Prey newPrey = new Prey(this.getName(), this.getEnergy() / 2, this.getSize(), this.getSpeed(), this.getVisionRange(), this.getReproductionRate(), getCalories(), this.getColor());

                boolean found = false;
                if (this.getX() < Simulation.worldSize - getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() + getSize(), this.getY(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() + getSize());
                        newPrey.setY(this.getY());
                        found = true;
                    }
                }

                if (!found && this.getX() > getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() - getSize(), this.getY(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() - getSize());
                        newPrey.setY(this.getY());
                        found = true;
                    }
                }

                if (!found && this.getY() > getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX(), this.getY() - getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX());
                        newPrey.setY(this.getY() - getSize());
                        found = true;
                    }
                }

                if (!found && this.getY() < Simulation.worldSize - getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX(), this.getY() + getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX());
                        newPrey.setY(this.getY() + getSize());
                        found = true;
                    }
                }

                if (!found && this.getX() < Simulation.worldSize - getSize() && this.getY() > getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() + getSize(), this.getY() - getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() + getSize());
                        newPrey.setY(this.getY() - getSize());
                        found = true;
                    }
                }

                if (!found && this.getX() > getSize() && this.getY() > getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() - getSize(), this.getY() - getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() - getSize());
                        newPrey.setY(this.getY() - getSize());
                        found = true;
                    }
                }

                if (!found && this.getX() < Simulation.worldSize - getSize() && this.getY() < Simulation.worldSize - getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() + getSize(), this.getY() + getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() + getSize());
                        newPrey.setY(this.getY() + getSize());
                        found = true;
                    }
                }

                if (!found && this.getX() > getSize() && this.getY() < Simulation.worldSize - getSize()) {

                    boolean empty = true;
                    for (Being being: beings) {
                        Rectangle temp = new Rectangle(this.getX() - getSize(), this.getY() + getSize(), this.getSize(), this.getSize());
                        if (being != this && temp.intersects(being.getRectangle())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        newPrey.setX(this.getX() - getSize());
                        newPrey.setY(this.getY() + getSize());
                        found = true;
                    }
                }

                if (found) {
                    Simulation.adder.add(newPrey);

                    this.setEnergy(this.getEnergy() / 2);
                    numberOfProduction++;
                }
            }
        }
    }
}
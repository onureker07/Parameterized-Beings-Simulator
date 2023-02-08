package Instances;
import java.awt.Color;
import java.util.ArrayList;

import Simulation.Simulation;
import java.awt.*;

/**
 * Carnivorous (i.e Predator) class is a subclass of Being class. It is a being that eats preys.
 * 
 * @author Onur Eker & Osman Utku Özbudak & Özlem Yalta
 * @version 1.0
 * @since 2023-02-05
 */
public class Carnivorous extends Being {

    /**
     * The constructor of the Carnivorous class.
     * It calls the constructor of the Being class with the given parameters.
     * @param name The name of the Carnivorous.
     * @param energy The energy of the Carnivorous.
     * @param size The size of the Carnivorous.
     * @param speed The speed of the Carnivorous.
     * @param visionRange The vision range of the Carnivorous.
     * @param reproductionRate The reproduction rate of the Carnivorous.
     * @param color The color of the Carnivorous.
     * @see Being
     */
    public Carnivorous(String name, float energy, int size, float speed, float visionRange, float reproductionRate, Color color) {
        super(name, energy, size, speed, visionRange, reproductionRate, color);
    }

    /**
     * The update method of the Carnivorous class.
     * It checks if it can move and updates energy accordingly.
     * It then checks if it intersects with any preys and eats them if it does.
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

        for (Being being: beings) {
            if (being instanceof Prey && being.getRectangle().intersects(this.getRectangle()) && this.eat((Nutrition) being)) {
                Simulation.remover.add(being);
            }
        }
        if (getEnergy() <= 0) {
            Simulation.remover.add(this);
            return;
        }
        reproduce(beings);
    }

    /**
     * The move method of the Carnivorous class.
     * It checks if it is time to move. If it isn't, it returns false.
     * It then checks if there is any prey in its vision range. If there is, sets the direction towards the prey.
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

        boolean isTherePrey = false;
        float closestPreyDistance = Float.MAX_VALUE;
        for (Being being: beings) {
            if (!(being instanceof Prey)) {
                continue;
            }
            float distance = (float) Math.sqrt(Math.pow(this.getX() - being.getX(), 2) + Math.pow(this.getY() - being.getY(), 2));
            if (closestPreyDistance < distance) {
                continue;
            }

            Rectangle temp = new Rectangle(this.getX() - (int) this.getVisionRange(), this.getY() - (int) this.getVisionRange(), this.getSize() + (int) this.getVisionRange() * 2, this.getSize() + (int) this.getVisionRange() * 2);
            if (temp.intersects(being.getRectangle())) {

                if (being.getY() > this.getY()) {
                    this.setDirection(Direction.DOWN);
                } else if (being.getY() < this.getY()) {
                    this.setDirection(Direction.UP);
                } else if (being.getX() > this.getX()) {
                    this.setDirection(Direction.RIGHT);
                } else if (being.getX() < this.getX()) {
                    this.setDirection(Direction.LEFT);
                }
                isTherePrey = true;
                closestPreyDistance = distance;
            }
        }
        if (!isTherePrey) {

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

        switch (this.getDirection()) {
        case UP:

            if (this.getY() > 0) {

                for (Being being: beings) {
                    if (being instanceof Prey) {
                        continue;
                    }
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
                    if (being instanceof Prey) {
                        continue;
                    }
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
                    if (being instanceof Prey) {
                        continue;
                    }
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
                    if (being instanceof Prey) {
                        continue;
                    }
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
     * The reproduce method of the Carnivorous class.
     * It checks if the Carnivorous has enough energy to reproduce, and if it does, it creates a new Carnivorous.
     * It searches for an empty space around the Carnivorous and creates a new Carnivorous there.
     * If it can't find an empty space, it doesn't reproduce.
     * It also halves the energy of the Carnivorous.
     * 
     * @param beings The beings arraylist.
     * @see Being
     */
    public void reproduce(ArrayList < Being > beings) {

        if (this.getEnergy() >= this.getSize() * this.getSize() * this.getSpeed() * this.getSpeed() * 2) {

            if (Math.random() < this.getReproductionRate()) {

                Carnivorous newCarnivorous = new Carnivorous(this.getName(), this.getEnergy() / 2, this.getSize(), this.getSpeed(), this.getVisionRange(), this.getReproductionRate(), this.getColor());

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
                        newCarnivorous.setX(this.getX() + getSize());
                        newCarnivorous.setY(this.getY());
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
                        newCarnivorous.setX(this.getX() - getSize());
                        newCarnivorous.setY(this.getY());
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
                        newCarnivorous.setX(this.getX());
                        newCarnivorous.setY(this.getY() - getSize());
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
                        newCarnivorous.setX(this.getX());
                        newCarnivorous.setY(this.getY() + getSize());
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
                        newCarnivorous.setX(this.getX() + getSize());
                        newCarnivorous.setY(this.getY() - getSize());
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
                        newCarnivorous.setX(this.getX() - getSize());
                        newCarnivorous.setY(this.getY() - getSize());
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
                        newCarnivorous.setX(this.getX() + getSize());
                        newCarnivorous.setY(this.getY() + getSize());
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
                        newCarnivorous.setX(this.getX() - getSize());
                        newCarnivorous.setY(this.getY() + getSize());
                        found = true;
                    }
                }

                if (found) {
                    Simulation.adder.add(newCarnivorous);

                    this.setEnergy(this.getEnergy() / 2);
                    numberOfProduction++;
                }
            }
        }
    }
}
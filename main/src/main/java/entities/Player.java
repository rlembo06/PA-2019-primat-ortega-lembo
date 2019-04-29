package entities;

import javafx.scene.Node;

import java.lang.reflect.InvocationTargetException;

public class Player {
    private int id;
    private String name = "Joueur";
    private Weapon weapon = new Weapon();
    private Movement movement = new Movement();
    private int life = 100;

    public Player(int id) {
        this.id = id;
    }

    public Player(int id, Weapon weapon, Movement movement) {
        this.id = id;
        this.weapon = weapon;
        this.movement = movement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name + " " + id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public void runMovementSelected(Node shape) {
        String selected = movement.getLabel();
        switch (selected) {
            case "movements.Random": {
                try {
                    movement.randomPath(shape).play();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString(){
        return name + " " + id;
    }
}

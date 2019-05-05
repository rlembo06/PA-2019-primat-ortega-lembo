package entities.players;

import constants.Damage;
import entities.gui.GameBoard;
import entities.weapons.Weapon;
import entities.movements.Movement;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

import java.lang.reflect.InvocationTargetException;

public class Player {
    private int id;
    private String name = "Joueur";
    private Weapon weapon = new Weapon();
    private Movement movement = new Movement();
    private ShapePlayer shape = new ShapePlayer();
    private int life = 100;

    public Player() {
    }

    public Player(int id) {
        this.id = id;
    }

    public Player(int id, Weapon weapon, Movement movement) {
        this.id = id;
        this.weapon = weapon;
        this.movement = movement;
    }

    public Shape getBoundingShape() {
        return null;
    };

    public void render(GraphicsContext gc, Paint color) {};

    public void handleCollision() {
        if(life > 0) {
            setLife(life - Damage.COLLISION);
            System.out.println("[Player][ID: " + id + "] Life: " + life);
        }
    };

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

    public ShapePlayer getShape() {
        return shape;
    }

    public void setShape(ShapePlayer shape) {
        this.shape = shape;
    }

    public void runMovementSelected(double time, GameBoard b) {
        String selected = movement.getLabel();
        switch (selected) {
            case "movements.Random": {
                try {
                    movement.randomMove(time, b);
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

package entities.movements;

import annotations.movements.RandomPath;
import entities.gui.GameBoard;
import entities.players.Player;
import entities.weapons.Weapons;
import javafx.animation.PathTransition;
import javafx.scene.Scene;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Movement {
    private String id;
    private String label;

    public Movement(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public Movement(String label) {
        this.label = label;
    }

    public Movement() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public PathTransition randomMove(double time, GameBoard b) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> random = Movements.getPlugin().getClassByName("movements.Random");
        return (PathTransition) Movements.getPlugin().getMethodsByAnnotation(random, RandomPath.class, time, b);
    }

    public void keyboardMoveInit(Scene scene, Player player) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> kbMove = Movements.getPlugin().getClassByName("movements.KeyboardMovements");
        Method method = Weapons.getPlugin().getMethodsByAnnotation(kbMove, annotations.movements.KeyboardInit.class);
        method.invoke(kbMove.newInstance(), scene, player);
    }
    public void keyboardMove(Scene scene, Player player) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> kbMove = Movements.getPlugin().getClassByName("movements.KeyboardMovements");
        Method method = Weapons.getPlugin().getMethodsByAnnotation(kbMove, annotations.movements.KeyboardMoves.class);
        method.invoke(kbMove.newInstance(), scene, player);
    }

    @Override
    public String toString(){
        return label;
    }
}
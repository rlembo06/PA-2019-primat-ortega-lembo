package entities;

import annotations.movements.RandomPath;
import javafx.animation.PathTransition;

import java.lang.reflect.InvocationTargetException;

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

    /*public PathTransition randomPath(Node node) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> random = Movements.getPlugin().getClassByName("movements.Random");
        return (PathTransition) Movements.getPlugin().getMethodsByAnnotation(random, RandomPath.class, node);
    }*/

    public PathTransition randomMove(double time, GameBoard b) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> random = Movements.getPlugin().getClassByName("movements.Random");
        return (PathTransition) Movements.getPlugin().getMethodsByAnnotation(random, RandomPath.class, time, b);
    }

    @Override
    public String toString(){
        return label;
    }
}
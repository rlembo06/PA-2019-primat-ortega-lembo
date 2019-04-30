package entities;

import annotations.shapes.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class ShapePlayer {

    private String label;
    private int w =20;
    private int h = 20;
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private Paint color;
    private Random r = new Random();

    public ShapePlayer() {
    }

    public ShapePlayer(String label) {
        this.label = label;
    }

    public ShapePlayer(Paint color, double x, double y, double speedX, double speedY) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public ShapePlayer(String label, Paint color, double x, double y, double speedX, double speedY) {
        this.label = label;
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void update(double time, GameBoard b) {
        x += speedX * time;
        y += speedY * time;
        if ((this.x+w)>b.getWidth() || this.x < 0) {
            this.speedX=-this.speedX;
        }
        if ((this.y+h)>b.getWidth() || this.y < 0) {
            this.speedY=-this.speedY;
        }
    }

    public void render(GraphicsContext gc) {
        switch (label) {
            case "shapes.Square": {
                try {
                    renderSquare(gc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            case "shapes.Circle": {
                try {
                    renderCircle(gc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            case "shapes.Star": {
                try {
                    renderStar();
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

    /*public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(color);
        gc.fillRect(x, y, w, h);
        gc.setFill(save);
    }*/

    /*public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(color);
        gc.fillOval(x, y, w, h);
        gc.setFill(save);
    }*/

    public void handleCollision(GameBoard b, ShapePlayer p) {
        //System.out.println("COLLISION !!!!!!!!!!!!!!");
    }

    /*public Shape getBoundingShape() {
        return new Rectangle(x,y,w,h);
    }*/

    public Shape getBoundingShape() {
        return new Circle(w);
    }

    /*public void renderSquare(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> square = Shapes.getPlugin().getClassByName("shapes.Square");
        Shapes.getPlugin().getMethodsByAnnotation(square, Square.class, gc, color, x, y, w, h);
    }*/

    public void renderSquare(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> square = Shapes.getPlugin().getClassByName("shapes.Square");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(square, Square.class);
        method.invoke(square.newInstance(), gc, color, x, y, w, h);
    }

    /*public void renderCircle(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> circle = Shapes.getPlugin().getClassByName("shapes.Circle");
        Shapes.getPlugin().getMethodsByAnnotation(circle, annotations.shapes.Circle.class, gc, color, x, y, w, h);
    }*/

    public void renderCircle(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> circle = Shapes.getPlugin().getClassByName("shapes.Circle");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(circle, annotations.shapes.Circle.class);
        method.invoke(circle.newInstance(), gc, color, x, y, w, h);
    }

    public void renderStar() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> star = Shapes.getPlugin().getClassByName("shapes.Star");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(star, annotations.shapes.Star.class);
        method.invoke(star.newInstance(), x, y, w, h);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return label;
    }
}

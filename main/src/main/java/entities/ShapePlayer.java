package entities;

import annotations.shapes.*;
import constants.Damage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShapePlayer {

    private int idPlayer;
    private String label;
    private int w =20;
    private int h = 20;
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private Paint color;

    public ShapePlayer() {
    }

    public ShapePlayer(String label) {
        this.label = label;
    }

    public ShapePlayer(int idPlayer, String label, Paint color, double x, double y, double speedX, double speedY) {
        this.idPlayer = idPlayer;
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
                    renderStar(gc);
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

    public void handleCollision() {
        for (Player player : Players.getList()) {
            if(player.getId() == idPlayer && player.getLife() > 0) {
                player.setLife(player.getLife() - Damage.COLLISION);
                System.out.println("[Player][ID: " + player.getId() + "] Life: " + player.getLife());
            }
        }
    }

    public Shape getBoundingShape() {
        switch (label) {
            case "shapes.Square": {
                return new Rectangle(x,y,w,h);
            }
            case "shapes.Circle": {
                return new Rectangle(x,y,w,w);
            }
            case "shapes.Star": {
                return new Polygon(x,y,w,h);
            }
            default:
                return new Rectangle(x,y,w,h);
        }
    }

    public void renderSquare(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> square = Shapes.getPlugin().getClassByName("shapes.Square");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(square, Square.class);
        method.invoke(square.newInstance(), gc, color, x, y, w, h);
    }

    public void renderCircle(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> circle = Shapes.getPlugin().getClassByName("shapes.Circle");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(circle, annotations.shapes.Circle.class);
        method.invoke(circle.newInstance(), gc, color, x, y, w, h);
    }

    public void renderStar(GraphicsContext gc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> star = Shapes.getPlugin().getClassByName("shapes.Star");
        Method method = Shapes.getPlugin().getMethodsByAnnotation(star, annotations.shapes.Star.class);
        method.invoke(star.newInstance(), gc, color, x, y, w, h);
    }

    public Paint getColor() {
        return color;
    }

    public void setColor(Paint color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }


    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public String toString(){
        return label;
    }
}

package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class ShapePlayer {

    private int w =20;
    private int h = 20;
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private Paint color;
    private Random r = new Random();

    public ShapePlayer(Paint color, double x, double y, double speedX, double speedY) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
    }

    public void update(double time, GameBoard b) {
        if ((this.x+w)>b.getWidth() || this.x < 0) {
            this.speedX=-this.speedX;
        }
        if ((this.y+h)>b.getWidth() || this.y < 0) {
            this.speedY=-this.speedY;
        }
    }

    public void render(GraphicsContext gc) {
        Paint save = gc.getFill();
        System.out.println(" colors " + color );
        gc.setFill(color);
        gc.fillRect(x, y, w, h);

        gc.setFill(save);
    }

    public void handleCollision(GameBoard b, Player p) {
        System.out.println("COLLISION !!!!!!!!!!!!!!");
    }

    public Shape getBoundingShape() {
        return new Rectangle(x,y,w,h);

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

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public Random getR() {
        return r;
    }

    public void setR(Random r) {
        this.r = r;
    }
}

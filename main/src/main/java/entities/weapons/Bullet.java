package entities.weapons;

import entities.gui.GameBoard;

public class Bullet {

    private double x, y, speedX, speedY;
    private int w = 3;
    private int h = 3;
    private int damage;
    private boolean visible;

    public Bullet(int damage, double startX, double startY, double speedX, double speedY){
        this.damage = damage;
        this.x = startX;
        this.y = startY;
        this.speedX = speedX;
        this.speedY = speedY;
        this.visible = true;
    }

    public Bullet(double startX, double startY){
        x = startX;
        y = startY;
        speedX = 100;
        speedY = 100;
        visible = true;
    }

    public void update() {
        x += speedX;
        if (x > 800){
            visible = false;
        }
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

    public double getY() {
        return y;
    }

    public double getSpeedX() {
        return speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

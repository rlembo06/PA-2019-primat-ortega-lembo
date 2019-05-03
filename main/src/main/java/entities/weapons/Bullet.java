package entities.weapons;

public class Bullet {

    private double x, y, speedX;
    private boolean visible;

    public Bullet(double startX, double startY){
        x = startX;
        y = startY;
        speedX = 7;
        visible = true;
    }

    public void update(){
        x += speedX;
        if (x > 800){
            visible = false;
        }
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

}

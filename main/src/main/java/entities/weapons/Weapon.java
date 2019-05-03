package entities.weapons;

import java.util.ArrayList;
import java.util.List;

public class Weapon {
    private String id;
    private String label;
    private List<Bullet> bullets = new ArrayList<>();

    public Weapon(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public Weapon(String label) {
        this.label = label;
    }

    public Weapon() {
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

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void shoot(int x, int y) {
        Bullet bullet = new Bullet(x + 50, y - 25);
        bullets.add(bullet);
    }


    @Override
    public String toString(){
        return label;
    }
}

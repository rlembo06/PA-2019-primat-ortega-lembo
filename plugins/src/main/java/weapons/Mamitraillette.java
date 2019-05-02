package weapons;

import annotations.weapons.Powerful;
import annotations.weapons.Weapon;
import entities.Player;
import entities.Players;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


@Weapon
public class Mamitraillette {

    private static final double speed = 80.0;
    public int damage = 25;
    public int maxAmmunition = 10;
    private ArrayList<Bullet> bullets;

    public void keyboardBullets(Scene scene, Player player){
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.SPACE){
                bullets.add(new Bullet(player.getLocation().getX(), player.getLocation().getY()));
            }
        });
    }

    public void drawBullet(GraphicsContext gc){
        for (Bullet bullet : bullets) {
            double x = bullet.getX();
            double y = bullet.getY();

            gc.setFill(Color.ORANGE);
            gc.fillRect(x, y, 20, 10);
        }
    }

    public void shotBullets(Player player, Players[] players){
        for (Bullet bullet : bullets) {
            bullet.setX(bullet.getX() + speed);
            bullet.setY(bullet.getY());
        }
    }

    @Powerful
    public int shot(int bullets) {
        if(bullets == 0){
            reload(bullets);
        }else {
            System.out.println("Shot!!!");
            return damage;
        }
        return 1;
    }

    public int reload(int bullets){
        System.out.println("Reloading....");
        return bullets = maxAmmunition;
    }
}

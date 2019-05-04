package weapons;

import annotations.weapons.Weapon;
import entities.players.ShapePlayer;
import helpers.RenderWeapon;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

@Weapon
public class Shotgun {

    private RenderWeapon renderWeapon = new RenderWeapon();
    private int DAMAGE = 8;
    private double SPEED_X = 30;
    private double SPEED_Y = 30;
    private Paint COLOR = Color.RED;
    private int WIDTH = 3;
    private int HEIGHT = 1;

    /*@annotations.weapons.Shotgun
    public void render(GraphicsContext graphicsContext, ShapePlayer shape) {
        renderWeapon.draw(graphicsContext, shape, COLOR, WIDTH, HEIGHT, DAMAGE, SPEED_X, SPEED_Y);
    }*/
}

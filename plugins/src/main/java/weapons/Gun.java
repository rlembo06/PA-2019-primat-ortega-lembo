package weapons;

import annotations.weapons.Weapon;
import entities.players.ShapePlayer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import helpers.RenderWeapon;

@Weapon
public class Gun {

    private RenderWeapon renderWeapon = new RenderWeapon();
    private int DAMAGE = 5;
    private double SPEED_X = 20;
    private double SPEED_Y = 20;
    private Paint COLOR = Color.ORANGE;
    private int WIDTH = 3;
    private int HEIGHT = 3;

    @annotations.weapons.Gun
    public void render(GraphicsContext graphicsContext, ShapePlayer shape) {
        renderWeapon.draw(graphicsContext, shape, COLOR, WIDTH, HEIGHT, DAMAGE, SPEED_X, SPEED_Y);
    }
}

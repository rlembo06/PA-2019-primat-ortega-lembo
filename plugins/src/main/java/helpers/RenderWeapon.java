package helpers;

import entities.players.ShapePlayer;
import entities.weapons.Bullet;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class RenderWeapon {

    public void draw(
            GraphicsContext graphicsContext,
            ShapePlayer shape,
            Paint color,
            int width,
            int height,
            int damage,
            double speedX,
            double speedY
    ) {
        shape
                .getPlayer()
                .getWeapon()
                .getBullets()
                .add(new Bullet(damage, shape.getX(), shape.getY(), speedX, speedY));

        for (Bullet bullet : shape.getPlayer().getWeapon().getBullets()) {
            bullet.update();
            Paint save = graphicsContext.getFill();
            graphicsContext.setFill(color);
            graphicsContext.fillOval(bullet.getX(), bullet.getY(), width, height);
            graphicsContext.setFill(save);
        }
    }
}

package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Square {

    public void render(GraphicsContext gc, Paint color, int x, int y, int w, int h) {
        Paint save = gc.getFill();
        gc.setFill(color);
        gc.fillRect(x, y, w, h);
        gc.setFill(save);
    }
}

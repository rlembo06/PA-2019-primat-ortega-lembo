package shapes;

import annotations.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

@Shape
public class Square {

    @annotations.shapes.Square
    public void render(GraphicsContext gc, Paint color, double x, double y, int w, int h) {
        Paint save = gc.getFill();
        gc.setFill(color);
        gc.fillRect(x, y, w, h);
        gc.setFill(save);
    }
}

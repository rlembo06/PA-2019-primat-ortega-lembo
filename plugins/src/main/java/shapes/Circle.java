package shapes;

import annotations.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

@Shape
public class Circle {

    @annotations.shapes.Circle
    public void render(GraphicsContext gc, Paint color, int x, int y, int w, int h) {
        Paint save = gc.getFill();
        gc.setFill(color);
        gc.fillOval(x, y, w, h);
        gc.setFill(save);
    }
}

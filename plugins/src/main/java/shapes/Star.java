package shapes;

import annotations.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

@Shape
public class Star {

    @annotations.shapes.Star
    public void render(GraphicsContext gc, Paint color, double x, double y, int w, int h) {

        double[] xPoints = {
                x,
                x + w / 3,
                x + w / 2,
                x + 2 * (w / 3),
                x + w,

                x + 3 * (w / 4),
                x + 8.25 * (w / 10),
                x + w / 2,
                x + 1.75 * (w / 10),
                x + (w / 4)
        };

        double[] yPoints = {
                y + h / 3,
                y + h / 4,
                y,
                y + h / 4,
                y + h / 3,


                y + 3 * (h / 5),
                y + h,
                y + 4 * (h / 5),
                y + h,
                y + 3 * (h / 5)
        };

        gc.setFill(color);
        gc.fillPolygon(xPoints, yPoints, xPoints.length);
    }

}

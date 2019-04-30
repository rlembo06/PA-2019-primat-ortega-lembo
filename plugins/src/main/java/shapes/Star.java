package shapes;

import annotations.shapes.Shape;

@Shape
public class Star {

    @annotations.shapes.Star
    public void render(double x, double y, int w, int h) {
        System.out.println("STAR : x %s, y %s, w %s, h %s" + x + y + w + h);
    }

}

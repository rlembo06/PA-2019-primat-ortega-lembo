import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import movements.Random;

public class Test {

    public static void main(String args[]) {
        Random random = new Random();

        System.out.println(
                "Movement - Random: " +
                random.createPathPlayer(
                        new Circle(50.0f, Color.GREEN)
                ).toString()
        );
    }
}

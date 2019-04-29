package controllers.arena;

import entities.GameBoard;
import entities.Player;
import entities.Players;
import entities.ShapePlayer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    @FXML
    private AnchorPane arena;

    private final Canvas canvas = new Canvas(600, 400);
    private final GraphicsContext gripGraphicsContext = canvas.getGraphicsContext2D();
    private final GameBoard board = new GameBoard(600, 400);

    private long lastUpdateNanoTime;
    private Color[] colors = {Color.GREEN, Color.RED, Color.BLUE};

    private Random ran = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arena.getChildren().add(canvas);
        initGame();
    }

    private void initGame() {
        generateShapePlayers();
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                double timeGame = (currentNanoTime - lastUpdateNanoTime) / 1000000000.0;
                generateGame(timeGame);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }
    
    private void generateShapePlayers() {
        for (Player player : Players.getList()) {
            player.setShape(new ShapePlayer(
                    colors[player.getId()],
                    ran.nextInt(600),
                    ran.nextInt(400),
                    30,
                    30)
            );
            board.addShapePlayer(player.getShape());
        }
    }

    private void generateGame(double timeGame) {
        Iterator<ShapePlayer> shapes = board.shapePlayerIterator();
        while (shapes.hasNext()) {
            ShapePlayer shape = shapes.next();
            shape.update(timeGame, board);
            shape.render(gripGraphicsContext);
        }
    }
}

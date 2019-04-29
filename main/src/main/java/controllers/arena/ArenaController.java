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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    @FXML
    private AnchorPane arena;

    @FXML
    private GridPane playersLifeGridPane;

    private final Canvas canvas = new Canvas(600, 400);
    private final GraphicsContext gripGraphicsContext = canvas.getGraphicsContext2D();
    private static GameBoard board = new GameBoard(350, 250);

    private long lastUpdateNanoTime;
    private Color[] colors = {Color.GREEN, Color.RED, Color.BLUE};

    private Random ran = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arena.getChildren().add(canvas);
        generateLifePlayers();
        generateShapePlayers();
        initGame();
    }

    private void initGame() {
        lastUpdateNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                double timeGame = (currentNanoTime - lastUpdateNanoTime) / 300000000.0;
                generateGame(timeGame);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }

    private void generateLifePlayers() {
        for (Player player : Players.getList()) {
            Label playerNameLabel = new Label(player.toString());
            Label playerLifeLabel = new Label(String.valueOf(player.getLife()));
            playerNameLabel.setTextFill(colors[player.getId()]);

            playersLifeGridPane.add(playerNameLabel, 0, player.getId() + 1);
            playersLifeGridPane.add(playerLifeLabel, 1, player.getId() + 1);
        }
    }

    private void generateShapePlayers() {
        for (Player player : Players.getList()) {
            player.setShape(
                new ShapePlayer(
                    player.getMovement(),
                    colors[player.getId()],
                    ran.nextInt(350),
                    ran.nextInt(250),
                    30,
                    30)
            );
            board.addShapePlayer(player.getShape());
        }
    }

    public void generateGame(double timeGame) {
        Iterator<ShapePlayer> shapes = board.shapePlayerIterator();
        while (shapes.hasNext()) {
            ShapePlayer shape = shapes.next();
            shape.update(timeGame, board);
            ArenaController.checkForCollision(shape, board.shapePlayerIterator());
            shape.render(gripGraphicsContext);
        }
    }

    public static void checkForCollision(ShapePlayer currentShape, Iterator<ShapePlayer> it) {
        while (it.hasNext()) {
            ShapePlayer shape = it.next();
            if (shape != currentShape) {
                if (currentShape.getBoundingShape().getBoundsInParent().intersects(shape.getBoundingShape().getBoundsInParent())) {
                    System.out.println(" it's a crash !!!");
                    currentShape.handleCollision(board, shape);
                }
            }
        }
    }
}

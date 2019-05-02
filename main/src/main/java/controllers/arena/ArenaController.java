package controllers.arena;

import entities.GameBoard;
import entities.Player;
import entities.Players;
import entities.ShapePlayer;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    private int LIFE_ROW_SIZE = 100;
    private int LIFE_COL_SIZE = 30;

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
                generateLifePlayers(gripGraphicsContext, Players.getList(), 10, 100, 30);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
    }

    private void generateLifePlayers(GraphicsContext gripGraphicsContext, List<Player> players, int heightBarLife, int rowSize, int colSize) {
        for (Player player : players) {
            double maxWidth = rowSize;
            double height = heightBarLife;

            double hp = player.getLife();

            double percentage = hp/rowSize;
            double width = percentage * maxWidth;

            double x = rowSize;
            double y = colSize * (player.getId() + 1) + 10;

            gripGraphicsContext.setStroke(Color.BLACK);

            // Player name
            gripGraphicsContext.setTextBaseline(VPos.CENTER);
            gripGraphicsContext.fillText(player.toString(), x - 90, y);

            // Lifebar background
            gripGraphicsContext.setFill(Color.GREY);
            gripGraphicsContext.fillRect(x, y, maxWidth, height);

            // Lifebar content
            gripGraphicsContext.setFill(player.getShape().getColor());
            gripGraphicsContext.fillRect(x, y, width, height);
        }
    }

    private void generateShapePlayers() {
        for (Player player : Players.getList()) {
            player.setShape(
                new ShapePlayer(
                    player.getId(),
                    player.getShape().getLabel(),
                    colors[player.getId()],
                    ran.nextInt(350),
                    ran.nextInt(250),
                    30,
                    30
                )
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
                    //System.out.println(" it's a crash !!!");
                    new Thread(() -> {
                        try {
                            Thread.sleep(500);
                            currentShape.handleCollision();
                        }
                        catch (Exception e){
                            System.err.println(e);
                        }
                    }).start();
                    currentShape.handleCollision();
                }
            }
        }
    }
}

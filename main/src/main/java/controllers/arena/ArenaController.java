package controllers.arena;

import entities.gui.GUI;
import entities.gui.GameBoard;
import entities.players.Player;
import entities.players.Players;
import entities.players.ShapePlayer;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;

public class ArenaController implements Initializable {

    private int LIFE_ROW_SIZE = 100;
    private int LIFE_COL_SIZE = 30;

    @FXML
    private AnchorPane arena;

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
                GUI.renderLife(gripGraphicsContext, Players.getList(), 10, LIFE_ROW_SIZE, LIFE_COL_SIZE);
                lastUpdateNanoTime = currentNanoTime;
            }
        }.start();
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
            ArenaController.checkCollisions();
            shape.render(gripGraphicsContext);
        }
    }

    private static void checkCollisions() {
        Player[] players = Players.getList().toArray(new Player[Players.getList().size()]);
        for (int i = 0; i < players.length; i++) {
            for (int j = i+1; j < players.length; j++) {
                Player p1 = players[i];
                Player p2 = players[j];

                double x1 = p1.getShape().getX();
                double x2 = p2.getShape().getX();

                double y1 = p1.getShape().getY();
                double y2 = p2.getShape().getY();

                Rectangle r1 = new Rectangle(x1, y1, p1.getShape().getW(), p1.getShape().getH());
                Rectangle r2 = new Rectangle(x2, y2, p2.getShape().getW(), p2.getShape().getH());

                if (r1.intersects(r2.getLayoutBounds())) {
                    p1.handleCollision();
                    p2.handleCollision();
                }
            }
        }
    }
}

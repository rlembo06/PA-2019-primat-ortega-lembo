package controllers.arena;

import entities.gui.GUI;
import entities.gui.GameBoard;
import entities.players.Player;
import entities.players.Players;
import entities.players.ShapePlayer;
import entities.weapons.Bullet;
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
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.paint.Paint;

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

    private void generateShoot(GraphicsContext graphicsContext, ShapePlayer shape) {

        shape
                .getPlayer()
                .getWeapon()
                .getBullets()
                .add(new Bullet(shape.getX(), shape.getY()));

        for (Bullet bullet : shape.getPlayer().getWeapon().getBullets()) {
            Paint save = graphicsContext.getFill();
            graphicsContext.setFill(Color.ORANGE);
            graphicsContext.fillOval(bullet.getX(), bullet.getY(), 3, 3);
            graphicsContext.setFill(save);
            bullet.update();
        }
    }

    private void generateShapePlayers() {
        for (Player player : Players.getList()) {
            player.setShape(
                new ShapePlayer(
                    player,
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
            generateShoot(gripGraphicsContext, shape);
            ArenaController.checkAttackWeapon();
        }
    }

    /*private static void checkAttackWeapon() {
        Player[] players = Players.getList().toArray(new Player[Players.getList().size()]);
        for (int i = 0; i < players.length; i++) {
            for (int j = i+1; j < players.length; j++) {
                Player p1 = players[i];
                Player p2 = players[j];

                List<Bullet> bulletsP1 =  p1.getShape().getPlayer().getWeapon().getBullets();
                List<Bullet> bulletsP2 =  p2.getShape().getPlayer().getWeapon().getBullets();
                Bullet bulletP1 = bulletsP1.get(bulletsP1.size()-1);
                Bullet bulletP2 = bulletsP2.get(bulletsP2.size()-1);

                double x1 = bulletP1.getX();
                double x2 = bulletP2.getX();

                double y1 = bulletP1.getY();
                double y2 = bulletP2.getY();

                Rectangle r1 = new Rectangle(x1, y1, p1.getShape().getW(), p1.getShape().getH());
                Rectangle r2 = new Rectangle(x2, y2, p2.getShape().getW(), p2.getShape().getH());

                if (r1.intersects(r2.getLayoutBounds())) {
                    System.out.println("TOUCH !!");
                }
            }
        }
    }*/

    private static void checkAttackWeapon() {
        Player[] players = Players.getList().toArray(new Player[Players.getList().size()]);
        for (int i = 0; i < players.length; i++) {
            for (int j = i+1; j < players.length; j++) {
                Player p1 = players[i];
                Player p2 = players[j];

                Bullet bullet =  p1.getShape().getPlayer().getWeapon().getBullets().get(0);

                double x1 = bullet.getX();
                double x2 = p2.getShape().getX();

                double y1 = bullet.getY();
                double y2 = p2.getShape().getY();

                Rectangle r1 = new Rectangle(x1, y1, p1.getShape().getW(), p1.getShape().getH());
                Rectangle r2 = new Rectangle(x2, y2, 3, 3);

                if (r1.intersects(r2.getLayoutBounds())) {
                    System.out.println("TOUCH !!");
                }
            }
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
                    //p1.handleCollision();
                    //p2.handleCollision();
                }
            }
        }
    }
}

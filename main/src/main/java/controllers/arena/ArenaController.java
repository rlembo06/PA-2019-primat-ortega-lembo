package controllers.arena;

import controllers.menu.MenuController;
import entities.gui.GUI;
import entities.gui.GameBoard;
import entities.players.Player;
import entities.players.Players;
import entities.players.ShapePlayer;
import entities.weapons.Bullet;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public final class ArenaController implements Initializable {

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
    private static AnimationTimer animationTimer = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arena.getChildren().add(canvas);
        generateShapePlayers();
        initGame();
    }

    private void initGame() {
        lastUpdateNanoTime = System.nanoTime();
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                double timeGame = (currentNanoTime - lastUpdateNanoTime) / 300000000.0;
                generateGame(timeGame, animationTimer);
                GUI.renderLife(gripGraphicsContext, Players.getList(), 10, LIFE_ROW_SIZE, LIFE_COL_SIZE);
                lastUpdateNanoTime = currentNanoTime;
                //checkWinner();
            }
        };
        animationTimer.start();
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
                    20,
                    20
                )
            );
            board.addShapePlayer(player.getShape());
        }
    }

    private void generateGame(double timeGame, AnimationTimer animationTimer) {
        Iterator<ShapePlayer> shapes = board.shapePlayerIterator();
        while (shapes.hasNext()) {
            ShapePlayer shape = shapes.next();
            shape.update(timeGame, board);
            if(MenuController.isWithCollision()) checkCollisions(animationTimer);
            shape.render(gripGraphicsContext);
            shape.renderWeapon(gripGraphicsContext);
            checkAttackWeapon(shape.getPlayer(), animationTimer);
            //checkWinner();
        }
    }

    private static void checkWinner(AnimationTimer animationTimer) {
        Alert alertWinner = new Alert(Alert.AlertType.INFORMATION);

        int countLooser = 0;
        Player winner = null;
        for (Player player : Players.getList()) {
            if(player.getLife() == 0) {
                countLooser += 1;
                //Players.getList().remove(player);
            }
            else winner = player;
        }
        if(Players.getList().size() - countLooser == 1) {
            animationTimer.stop();
            System.out.println("Le joueur " + winner.getName() + " a gagné !");

            alertWinner.setTitle("Information");
            alertWinner.setContentText("Le joueur " + winner.getName() + " a gagné !");
            alertWinner.setOnHidden(evt -> Platform.exit());
            alertWinner.show();
        }
    }

    private static void checkAttackWeapon(Player currentPlayer, AnimationTimer animationTimer) {
        Player[] players = Players.getList().toArray(new Player[Players.getList().size()]);
        for (int i = 0; i < players.length; i++) {

            List<Bullet> bullets = players[i].getWeapon().getBullets();
            for (int j = i+1; j < bullets.size(); j++) {

                Player player = players[i];

                if(player.equals(currentPlayer)) continue;
                Bullet bullet = bullets.get(j);

                double x1 = bullet.getX();
                double x2 = player.getShape().getX();

                double y1 = bullet.getY();
                double y2 = player.getShape().getY();

                Rectangle r1 = new Rectangle(x1, y1, bullet.getW(), bullet.getH());
                Rectangle r2 = new Rectangle(x2, y2, player.getShape().getW(), player.getShape().getH());

                if (r1.intersects(r2.getLayoutBounds())) {
                    player.setLife(player.getLife() - bullet.getDamage());
                    checkWinner(animationTimer);
                }
            }
        }
    }

    private static void checkCollisions(AnimationTimer animationTimer) {
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
                    checkWinner(animationTimer);
                }
            }
        }
    }
}

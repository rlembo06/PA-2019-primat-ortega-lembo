package gui;

import annotations.gui.*;
import entities.Player;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

@GUI
public class Life {

    @annotations.gui.Life
    public void render(GraphicsContext gripGraphicsContext, List<Player> players, int heightBarLife, int rowSize, int colSize) {
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
}

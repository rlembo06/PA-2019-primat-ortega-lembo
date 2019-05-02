package movements;

import annotations.movements.Movement;
import entities.Player;
import entities.Players;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

@Movement
public class KeyboardMovements {

    private static final double keyBoardSpeed = 40;
    private double velocityX;
    private double velocityY;

    public KeyboardMovements(){
        velocityX = 0;
        velocityY = 0;
    }

    public void keyboardListener(Scene scene, Player player){
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.UP){
                velocityY -= keyBoardSpeed;
            }else if(event.getCode() == KeyCode.DOWN){
                velocityY = keyBoardSpeed;
            }else if(event.getCode() == KeyCode.LEFT){
                velocityX -= keyBoardSpeed;
            }else if(event.getCode() == KeyCode.RIGHT){
                velocityX = keyBoardSpeed;
            }
        });
        scene.setOnKeyReleased(event -> {
            if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN){
                velocityY = 0;
            }else if(event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT){
                velocityX = 0;
            }
        });
    }

    public void keyboardActiveMoves(Player player){
        double posX = player.getLocation().getX();
        double posY = player.getLocation().getX();

        if(posX >= 0 && velocityX < 0) {
            player.getLocation().setX(posX + velocityX);
        }
        if(posY >= 0 && velocityY < 0) {
            player.getLocation().setX(velocityY + posY);
        }



    }
    

}

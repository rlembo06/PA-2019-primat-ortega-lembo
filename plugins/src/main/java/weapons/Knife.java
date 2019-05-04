package weapons;

import annotations.weapons.Powerful;
import annotations.weapons.Weapon;
import entities.Player;
import entities.Players;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.awt.event.KeyEvent;
import java.util.ArrayList;


@Weapon
public class Knife {

    public int damage = 5;


    @Powerful
    public int stab() {
            System.out.println("Stab !!!");
            return damage;
    }

}

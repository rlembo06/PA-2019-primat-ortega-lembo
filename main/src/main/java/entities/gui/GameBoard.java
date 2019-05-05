package entities.gui;

import entities.players.ShapePlayer;

import java.util.ArrayList;
import java.util.Iterator;

public class GameBoard {

    private int width;
    private int height;

    private ArrayList<ShapePlayer> list = new ArrayList<>();

    public GameBoard(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }


    public void addShapePlayer(ShapePlayer p) {
        this.list.add(p);
    }

    public Iterator<ShapePlayer> shapePlayerIterator() {
        return list.iterator();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<ShapePlayer> getList() {
        return list;
    }
}

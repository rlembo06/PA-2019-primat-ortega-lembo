package entities;

import javafx.scene.canvas.GraphicsContext;
import plugins.GUIPlugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public final class GUI {

    private static GUIPlugin plugin;

    static {
        try {
            plugin = new GUIPlugin();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Movement> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    private GUI() throws IOException, ClassNotFoundException {
        plugin = new GUIPlugin();
    }

    public static void renderLife(GraphicsContext gripGraphicsContext, List<Player> players, int heightBarLife, int rowSize, int colSize) {
        Class<?> life = Shapes.getPlugin().getClassByName("gui.Life");
        Method method = null;
        try {
            method = Shapes.getPlugin().getMethodsByAnnotation(life, annotations.gui.Life.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        try {
            method.invoke(life.newInstance(), gripGraphicsContext, players, heightBarLife, rowSize, colSize);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void loadListName() {
        for (Class<?> item : plugin.getClasses()) {
            listName.add(item.getName());
        }
    }

    public static List<String> getListName() {
        return listName;
    }

    public static void loadList() {
        for (Class<?> item : plugin.getClasses()) {
            list.add(new Movement(item.getName()));
        }
    }

    public static GUIPlugin getPlugin() {
        return plugin;
    }

    public static List<Movement> getList() {
        return list;
    }
}
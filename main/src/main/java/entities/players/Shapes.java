package entities.players;

import entities.players.ShapePlayer;
import plugins.ShapePlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Shapes {
    private static ShapePlugin plugin;

    static {
        try {
            plugin = new ShapePlugin();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<ShapePlayer> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    private Shapes() throws IOException, ClassNotFoundException {
        plugin = new ShapePlugin();
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
            list.add(new ShapePlayer(item.getName()));
        }
    }

    public static ShapePlugin getPlugin() {
        return plugin;
    }

    public static List<ShapePlayer> getList() {
        return list;
    }
}

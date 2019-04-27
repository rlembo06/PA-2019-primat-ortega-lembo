package entities;

import plugins.MovementPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Movements {
    private static MovementPlugin plugin;

    static {
        try {
            plugin = new MovementPlugin();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Movement> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    private Movements() throws IOException, ClassNotFoundException {
        plugin = new MovementPlugin();
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

    public static List<Movement> getList() {
        return list;
    }

}
package entities;

import plugins.MovementPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Movements {
    private MovementPlugin plugin;
    private List<Movement> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();

    public Movements() throws IOException, ClassNotFoundException {
        plugin = new MovementPlugin();
        getList();
        getListName();
    }

    public List<String> getListName() {
        for (Class<?> item : plugin.getClasses()) {
            listName.add(item.getName());
        }
        return listName;
    }

    public List<Movement> getList() {
        for (Class<?> item : plugin.getClasses()) {
            list.add(new Movement(item.getName()));
        }
        return list;
    }

}
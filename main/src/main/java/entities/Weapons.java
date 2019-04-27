package entities;

import plugins.WeaponPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class Weapons {
    private static WeaponPlugin plugin;

    static {
        try {
            plugin = new WeaponPlugin();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<Weapon> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    public static void loadListName() {
        for (Class<?> item : plugin.getClasses()) {
            listName.add(item.getName());
        }
    }

    public static void loadList() {
        for (Class<?> item : plugin.getClasses()) {
            list.add(new Weapon(item.getName()));
        }
    }

    public static List<String> getListName() {
        return listName;
    }

    public static List<Weapon> getList() {
        return list;
    }

}

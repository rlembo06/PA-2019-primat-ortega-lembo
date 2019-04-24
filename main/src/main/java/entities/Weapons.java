package entities;

import plugins.WeaponPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Weapons {
    private WeaponPlugin plugin;
    private List<Weapon> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();

    public Weapons() throws IOException, ClassNotFoundException {
        plugin = new WeaponPlugin();
        getList();
        getListName();
    }

    public List<String> getListName() {
        for (Class<?> item : plugin.getClasses()) {
            listName.add(item.getName());
        }
        return listName;
    }

    public List<Weapon> getList() {
        for (Class<?> item : plugin.getClasses()) {
            list.add(new Weapon(item.getName()));
        }
        return list;
    }

}

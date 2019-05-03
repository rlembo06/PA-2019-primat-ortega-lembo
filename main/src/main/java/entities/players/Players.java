package entities.players;

import java.util.ArrayList;
import java.util.List;

public final class Players {
    private static List<Player> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    private Players() {}

    public static List<Player> getList() {
        return list;
    }

    public static void setList(List<Player> list) {
        Players.list = list;
    }

    public static List<String> getListName() {
        return listName;
    }

    public static void setListName(List<String> listName) {
        Players.listName = listName;
    }
}

package entities;

import java.util.ArrayList;
import java.util.List;

public final class Users {
    private static List<User> list = new ArrayList<>();
    private static List<String> listName = new ArrayList<>();

    private Users() {}

    public static List<User> getList() {
        return list;
    }

    public static void setList(List<User> list) {
        Users.list = list;
    }

    public static List<String> getListName() {
        return listName;
    }

    public static void setListName(List<String> listName) {
        Users.listName = listName;
    }
}

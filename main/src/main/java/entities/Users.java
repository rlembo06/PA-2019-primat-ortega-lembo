package entities;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<User> list = new ArrayList<>();
    private List<String> listName = new ArrayList<>();

    public Users() {}

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public List<String> getListName() {
        return listName;
    }

    public void setListName(List<String> listName) {
        this.listName = listName;
    }
}

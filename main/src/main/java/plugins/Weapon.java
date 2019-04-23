package plugins;

import java.io.IOException;
import java.util.List;

public class Weapon extends PluginLoader {

    private List<Class<?>> classes;

    public Weapon() throws IOException, ClassNotFoundException {
        classes = loadPlugin("weapons");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}

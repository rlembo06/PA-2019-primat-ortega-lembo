package plugins;

import java.io.IOException;
import java.util.List;

public class MovementPlugin extends PluginLoader {

    private List<Class<?>> classes;

    public MovementPlugin() throws IOException, ClassNotFoundException {
        classes = loadPlugin("movements");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}
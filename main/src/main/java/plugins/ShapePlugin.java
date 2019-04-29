package plugins;

import java.io.IOException;
import java.util.List;

public class ShapePlugin extends PluginLoader {

    private List<Class<?>> classes;

    public ShapePlugin() throws IOException, ClassNotFoundException {
        classes = loadPlugin("shapes");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}

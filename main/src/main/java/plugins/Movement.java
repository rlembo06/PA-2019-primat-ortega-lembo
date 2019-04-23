package plugins;

import java.io.IOException;
import java.util.List;

public class Movement extends PluginLoader {

    private List<Class<?>> classes;

    public Movement() throws IOException, ClassNotFoundException {
        classes = loadPlugin("movements");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}
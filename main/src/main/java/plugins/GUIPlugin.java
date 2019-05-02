package plugins;

import java.io.IOException;
import java.util.List;

public class GUIPlugin extends PluginLoader {

    private List<Class<?>> classes;

    public GUIPlugin() throws IOException, ClassNotFoundException {
        classes = loadPlugin("gui");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}

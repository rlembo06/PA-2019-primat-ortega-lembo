package plugins;

import java.io.IOException;
import java.util.List;

public class WeaponPlugin extends PluginLoader {

    private List<Class<?>> classes;

    public WeaponPlugin() throws IOException, ClassNotFoundException {
        classes = loadPlugin("weapons");
    }

    @Override
    public List<Class<?>> getClasses() {
        return classes;
    }
}

package plugins;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginLoader {

    private List<Class<?>> classes = new ArrayList<Class<?>>();
    private String folderPath = "";

    public PluginLoader() {
        setFolderPath();
    }

    protected void setFolderPath() {
        folderPath = new File(".").getAbsolutePath();
        folderPath = folderPath.substring(0, folderPath.length() - 1);
        folderPath += "plugins/target/plugins-1.0-SNAPSHOT.jar";
    }

    public List<Class<?>> getClasses() {
        return classes;
    }

    public List<Class<?>> loadPlugin() throws IOException, ClassNotFoundException {
        return loadPlugin("");
    }

    protected List<Class<?>> loadPlugin(String pluginNameQuery) throws IOException, ClassNotFoundException {

        JarFile jarFile = new JarFile(folderPath);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + folderPath +"!/") };
        URLClassLoader classLoader = URLClassLoader.newInstance(urls);

        while (e.hasMoreElements()) {
            JarEntry je = e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")){
                continue;
            }
            // -6 because of .class
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            Class cl = classLoader.loadClass(className);
            classes.add(cl);
        }
        if(pluginNameQuery.equals("")) {
            return classes;
        } else {
            return searchPlugin(pluginNameQuery, classes);
        }
    }

    public List<Class<?>> searchPlugin(String pluginNameQuery, List<Class<?>> classes) {

        List<Class<?>> classesOfQuery = new ArrayList<Class<?>>();
        for (Class<?> cl: classes) {
            String pluginName = cl.getName();
            pluginName = pluginName.split("\\.")[0];
            if(pluginNameQuery.equals(pluginName)) {
                classesOfQuery.add(cl);
            }
        }
        return classesOfQuery;
    }

    // 1 ANNOTATION PAR METHODE
    public Object getMethodsByAnnotation (Class <?> cl, Class <? extends Annotation> annotation, Object... parameters) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                return method.invoke(cl.newInstance(), parameters);
            }
        }
        return null;
    }

    public List<Class<?>> getClassesByAnnotation (List<Class<?>> classes, Class <? extends Annotation> annotation) {
        List<Class<?>> classesAnnoted = new ArrayList<>();
        for (Class<?> cl : classes) {
            if (cl.isAnnotationPresent(annotation)) {
                classesAnnoted.add(cl);
            }
        }
        return classesAnnoted;
    }

    public Class<?> getClassByName(String className) {
        for (Class<?> cl : classes) {
            if(cl.getName() == className) {
                return cl;
            }
        }
        return null;
    }

}

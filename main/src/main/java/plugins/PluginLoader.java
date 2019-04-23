package plugins;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
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

    public List<Method> getMethodsByAnnotation (Class <?> cl, Class <? extends Annotation> annotation) {
        Method[] methods = cl.getMethods();
        List<Method> methodsAnnoted = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                methodsAnnoted.add(method);
            }
        }
        return methodsAnnoted;
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

    /* protected Object getMethodClassPlugin(Class<?> cl, String method) {
        Object result = null;
        try {
            try {
                result = cl.getMethod(method).invoke(cl.newInstance());
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    protected Object getMethodClassPlugin(Class<?> cl, String method, int parameters) {
        Object result = null;
        try {
            try {
                result = cl.getMethod(method, int.class).invoke(cl.newInstance(), parameters);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    protected Object getMethodClassPlugin(Class<?> cl, String method, String parameters) {
        Object result = null;
        try {
            try {
                result = cl.getMethod(method, String.class).invoke(cl.newInstance(), parameters);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    protected Object getMethodClassPlugin(Class<?> cl, String method, Object parameters) {
        Object result = null;
        try {
            try {
                result = cl.getMethod(method, Object.class).invoke(cl.newInstance(), parameters);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(PluginLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    } */

    /* protected List<Annotation> getAnnotationsClassPlugin(Class<?> cl) {
        Field[] fields = cl.getFields();
        for (Field field : fields) {
            Annotation[] ann = field.getAnnotations();
            for (Annotation annotation : ann) {
                System.out.println(annotation);
            }

        }
        return null;
    } */

    /* private void classesInJAR(String jar) throws IOException, ClassNotFoundException {

        JarFile jarFile = new JarFile(jar);
        Enumeration<JarEntry> e = jarFile.entries();

        URL[] urls = { new URL("jar:file:" + jar +"!/") };
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
        System.out.println("classesInJAR classes: " + classes);
    } */

}

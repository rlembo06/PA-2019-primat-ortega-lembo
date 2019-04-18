package com.miage;

import com.miage.plugins.PluginsLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String weaponPluginJar = "WeaponPlugin-TP-Jeu.jar";
    
    public static void main(String args[]) throws MalformedURLException, ClassNotFoundException, IOException {
        PluginsLoader pluginLoader = new PluginsLoader();
        String basePath = pluginLoader.getBasePath();
        String urlplugin = "file://" + basePath + weaponPluginJar;
        
        displayFieldsFromClass(urlplugin, "com.miage.Weapon");
    }
    
    public static void displayFieldsFromClass(String urlPlugin, String className) throws MalformedURLException, ClassNotFoundException {
        URL classUrl = new URL(urlPlugin);
        URL[] urls = { classUrl };
        URLClassLoader ucl = new URLClassLoader(urls);
        Class cl = ucl.loadClass(className);
        
        System.out.println("CLASSE : "+ cl.getName() + "\n");
        for(Field f: cl.getDeclaredFields()) {
            System.out.println("- Field name : " + f.getName());
        }
    }
}

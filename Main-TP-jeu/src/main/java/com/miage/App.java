/*
 * RUN project : mvn package && java -cp target/Main-TP-jeu-1.0-SNAPSHOT.jar com.miage.App
 */

package com.miage;

import com.miage.plugins.Plugin;
import com.miage.plugins.PluginsLoader;
import com.miage.plugins.WeaponPlugin;
import com.miage.plugins.MouvementPlugin;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    private static String weaponPluginJar = "WeaponPlugin-TP-Jeu-1.0-SNAPSHOT.jar";
    private static String mouvementPluginJar = "MouvementPlugin-TP-Jeu-1.0-SNAPSHOT.jar";
    
    public static void main(String args[]) throws MalformedURLException, ClassNotFoundException, IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PluginsLoader pluginsLoader = new PluginsLoader();
        Plugin plugins = new Plugin();
        
        System.err.println("\n\n--- PluginsLoader --- \n");
        pluginsLoader.displayFieldsFromClass(weaponPluginJar, "com.miage.Weapon");
        pluginsLoader.displayFieldsFromClass(mouvementPluginJar, "com.miage.Mouvement");
        
        System.err.println("\n\n--- Plugins --- \n");
        plugins.load();
        
        Class<?> cl = plugins.getClassByName("com.miage.Weapon");
        System.err.println("Weapon : " + cl.getName());
        System.err.println("Weapon - getLabel [getMethodClassPlugin] : " + plugins.getMethodClassPlugin(cl, "getLabel"));
        
        System.err.println("\n\n--- WeaponPlugin --- \n");
        WeaponPlugin weapon = new WeaponPlugin();
        System.err.println("Weapon - getId : " +  weapon.getWeaponMethod("getId"));
        
        System.err.println("\n\n--- MouvementPlugin --- \n");
        MouvementPlugin mouvement = new MouvementPlugin();
        System.err.println("Mouvement - getDirection : " +  mouvement.getMouvementMethod("getDirection"));
    }
}

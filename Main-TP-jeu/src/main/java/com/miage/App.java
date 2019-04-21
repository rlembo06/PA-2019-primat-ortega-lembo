/*
 * RUN project : mvn package && java -cp target/Main-TP-jeu-1.0-SNAPSHOT.jar com.miage.App
 */

package com.miage;

import com.miage.plugins.Plugins;
import com.miage.plugins.PluginsLoader;
import java.io.IOException;
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
    
    public static void main(String args[]) throws MalformedURLException, ClassNotFoundException, IOException {
        PluginsLoader pluginsLoader = new PluginsLoader();
        Plugins plugins = new Plugins();
        
        System.err.println("\n\n--- PluginsLoader --- \n");
        pluginsLoader.displayFieldsFromClass(weaponPluginJar, "com.miage.Weapon");
        pluginsLoader.displayFieldsFromClass(mouvementPluginJar, "com.miage.Mouvement");
        
        System.err.println("\n\n--- Plugins --- \n");
        plugins.load();
        
        Class<?> cl = plugins.getClassByName("com.miage.Mouvement");
        System.err.println("getClassByName : " + cl.getName());
        //Récupérer méthode de la class Mouvement ? : cl.getDirection();
    }
}

package com.miage;

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
    //private static String urlClass = "file:///home/romain/Documents/Cours/Cours-Master-MIAGE/Master-1/Semestre-2/UE-Composants_logiciels_Entreprise/Programmation_avancee/TP/TPs-OI-Prog_avancee-M1-MIAGE/annexes/build/classes/";
    private static String urlClass = "file:///home/romain/Documents/Cours/Cours-Master-MIAGE/Master-1/Semestre-2/UE-Composants_logiciels_Entreprise/Programmation_avancee/TP/TP-OI-Prog_avancee-Jeu-M1-MIAGE/plugins/WeaponPlugin-TP-Jeu/dist/WeaponPlugin-TP-Jeu.jar";

    public static void main(String args[]) throws MalformedURLException, ClassNotFoundException {
        //displayFieldsFromClass(urlClass, "Test");
        displayFieldsFromClass(urlClass, "com.miage.Weapon");
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

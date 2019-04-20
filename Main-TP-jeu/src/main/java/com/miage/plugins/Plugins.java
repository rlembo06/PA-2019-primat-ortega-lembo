/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.plugins;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
/*import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;*/

/**
 *
 * @author romain
 */
public class Plugins {
    
    private PluginsLoader pluginLoader = new PluginsLoader();
    private List<Class<?>> classes = new ArrayList<Class<?>>();
    //private final ClassPool classPool = ClassPool.getDefault();
    private String folderPath = "";
    
    public Plugins() {
        setFolderPath();
    }
    
    private void setFolderPath() {
        folderPath = new File(".").getAbsolutePath();
        folderPath = folderPath.substring(0, folderPath.length() - 1);
        folderPath += "plugins/";
    }
    
    public void load() throws IOException, MalformedURLException, ClassNotFoundException  {
        pluginLoader.getPath().add(new File(folderPath));
        browse();
    }
    
    private void browse() throws IOException, MalformedURLException, ClassNotFoundException{
        File folder = new File(folderPath);
        File[] filesList = folder.listFiles();

        for (File file : filesList) {
            if (file.isFile()) {
                
                if(file.getName().endsWith(".jar")) {
                    classesInJAR(folderPath + file.getName());
                    //setClassPool(folderPath + file.getName());
                }
              } else if (file.isDirectory()) {
                System.out.println("- Directory : " + file.getName());
              }
        }
    }
    
    /* private void setClassPool(String jar)  {
        try {
            classPool.insertClassPath(jar);
        } catch (NotFoundException ex) {
            Logger.getLogger(Plugins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public CtClass getCtClass(String className) {
        CtClass ctClass = null;
        try {
            ctClass = classPool.get(className);
            System.out.println("setClassPool / " + className +" : " + ctClass);
        } catch (NotFoundException ex) {
            Logger.getLogger(Plugins.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctClass;
    } */

    public List<Class<?>> getClasses() {
        return classes;
    }
    
    public Class<?> getClassByName(String className) {
        for (Class<?> cl : classes) {
            if(cl.getName() == className) {
                return cl;
            }
        }
        return null;
    }
    
    private void classesInJAR(String jar) throws MalformedURLException, IOException, ClassNotFoundException {
        
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
    }

}

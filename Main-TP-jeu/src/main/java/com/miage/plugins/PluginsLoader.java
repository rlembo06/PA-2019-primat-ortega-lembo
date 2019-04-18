/*
 * RUN project : mvn package && java -cp target/Main-TP-jeu-1.0-SNAPSHOT.jar com.miage.App
 */
package com.miage.plugins;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author romain
 */
public class PluginsLoader extends SecureClassLoader {
    
    private ArrayList<File> path = new ArrayList<File>();
    
    private String basePath;

    public PluginsLoader(ClassLoader parent) {
        super(parent);
    }
    
    public PluginsLoader(ArrayList<File> path) {
        setBasePath();
        this.path = path;
    }
    
    public PluginsLoader() {
        setBasePath();
    }
    
    public void setBasePath() {
        basePath = new File(".").getAbsolutePath();
        basePath = basePath.substring(0, basePath.length() - 1);
        basePath += "plugins/";
    }

    public String getBasePath() {
        return this.basePath;
    }
    
    @Override  
    protected Class<?> findClass(String name) throws ClassNotFoundException {  
        byte[] b = loadClassData(name);  
        return super.defineClass(name, b, 0, b.length);  
    }
    
    private byte[] loadClassData(String name) throws ClassNotFoundException {
        try {
            return loadClassRepertoire(name);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }
    
    private byte[] loadClassRepertoire(String name) throws ClassNotFoundException, IOException {
        String pathclass = name.replace('.', '/') + ".class";

        for (File file : path) {
            return loadClassJar(file, pathclass);
        }
        throw new ClassNotFoundException();
    }
    
    private byte[] loadClassJar(File file, String pathclass) throws ClassNotFoundException, IOException {
        byte[] result = null;
        JarFile jf = new JarFile(file.getPath());
        JarEntry je = jf.getJarEntry(pathclass);
        
        InputStream is = jf.getInputStream(je);
        result = getBytesFromInputStream(is);
        
        jf.close();
        return result;
    }
    
    private static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        long length = is.available();

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file ");
        }

        is.close();
        return bytes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage.plugins;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romain
 */
public class MouvementPlugin extends Plugin {
    
    private Class<?> mouvement;
    private Class<?> iMouvement;
    private List<Annotation> annotations = new ArrayList<Annotation>();

    public MouvementPlugin() throws IOException, MalformedURLException, ClassNotFoundException {
        load();
        mouvement = getClassByName("com.miage.Mouvement");
        iMouvement = getClassByName("com.miage.IMouvement");
        
        // TODO :
        annotations = getAnnotationsClassPlugin(iMouvement);
    }   
    
    public Object getMouvementMethod(String method) {
        return getMethodClassPlugin(mouvement, method);
    }
    
}

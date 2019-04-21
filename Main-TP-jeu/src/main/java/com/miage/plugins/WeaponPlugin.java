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
public class WeaponPlugin extends Plugin {
    
    private Class<?> weapon;
    private Class<?> iWeapon = null;
    private List<Annotation> annotations = new ArrayList<Annotation>();
    
    public WeaponPlugin() throws IOException, MalformedURLException, ClassNotFoundException {
        load();
        weapon = getClassByName("com.miage.Weapon");
        iWeapon = getClassByName("com.miage.IWeapon");
        
        // TODO :
        annotations = getAnnotationsClassPlugin(iWeapon);
    }
    
    public Object getWeaponMethod(String method) {
        return getMethodClassPlugin(weapon, method);
    } 

    public List<Annotation> getAnnotations() {
        return annotations;
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

import java.lang.annotation.Annotation;

/**
 *
 * @author romain
 */
public class Weapon implements IWeapon {

    @Override
    public String getId() {
        return "ID-TEST";
    }

    @Override
    public String getLabel() {
        return "Label TEST";
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

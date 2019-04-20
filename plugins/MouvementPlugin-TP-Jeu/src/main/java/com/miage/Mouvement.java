/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miage;

/**
 *
 * @author romain
 */
public class Mouvement {

    public Mouvement(String direction) {
        this.direction = direction;
    }

    public Mouvement() {
    }
        
    private String direction = "left";

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    
}

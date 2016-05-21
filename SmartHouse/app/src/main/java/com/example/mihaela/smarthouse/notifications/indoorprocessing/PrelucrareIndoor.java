/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mihaela.smarthouse.notifications.indoorprocessing;

/**
 *
 * @author RGI3
 */
public class PrelucrareIndoor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DataAnalyser dataAn= new DataAnalyser();
        dataAn.loop(60000); //1 min timer
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import irrgarten.Controller.Controller;
import irrgarten.Game;
import irrgarten.UI.InterfazUI;

/**
 *
 * @author isabe
 */
public class mainInterfaz {
    
    public static void main (String []args){
        
        int njugadores = 2;
        
        InterfazUI vista = new InterfazUI();
        Game game = new Game(njugadores);
        Controller controller = new Controller (game, vista);
        controller.play();
    
    }
}

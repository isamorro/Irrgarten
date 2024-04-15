
package main;

import irrgarten.Controller.Controller;
import irrgarten.Game;
import irrgarten.UI.TextUI;

/**
 *
 * @author Omnia Infosys
 */
public class mainP3 {

    public static void main (String []args){
        
        int njugadores = 2;
        
        TextUI vista = new TextUI();
        Game game = new Game(njugadores);
        Controller controller = new Controller (game, vista);
        controller.play();
    
    }
}

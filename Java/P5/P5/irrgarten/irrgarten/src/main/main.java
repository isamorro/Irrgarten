    
package main;

import irrgarten.Controller.Controller;
import irrgarten.Game;
import irrgarten.UI.TextUI;

/**
 * @brief   Funcion principal para jugar en forma de texto
 * @author  Isabel Morro Tabares
 */
public class main {

    public static void main (String []args){
        
        int njugadores = 2;
        
        TextUI vista = new TextUI();
        Game game = new Game(njugadores);
        Controller controller = new Controller (game, vista);
        controller.play();
    
    }
}


package main;

import irrgarten.Controller.Controller;
import irrgarten.Game;
import irrgarten.UI.InterfazUI;

/**
 * @brief   Funcion principal para jugar con interfaz gráfica
 * @author  Isabel Morro Tabares
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

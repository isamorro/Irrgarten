
package irrgarten.UI;

/**
 * @brief   Interfaz que representa las diferentes formas de jugar
 * @author  Isabel Morro Tabares
 */

import irrgarten.Directions;
import irrgarten.GameState;

/**
 *
 * @author isabe
 */
public interface UI {
    
    public Directions nextMove();
    public void showGame(GameState gameState); 
}

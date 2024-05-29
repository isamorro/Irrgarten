
package irrgarten;

/**
 * @brief Representa la baraja de cartas de tipo Shield del juego
 * @author isabel
 */
public class ShieldCardDeck extends CardDeck<Shield> {
    
    @Override
    protected void addCards(){
        int n_cartas = 20;
        for (int i=0; i < n_cartas; i++){
            addCard(new Shield(Dice.shieldPower(),
                    Dice.usesLeft()));   
        }   
    }
    
    
}


package irrgarten;

/**
 * @brief   Representa la baraja de cartas de tipo Weapon del juego
 * @author  Isabel Morro Tabares
 */
 
public class WeaponCardDeck extends CardDeck<Weapon> {
    
    @Override
    protected void addCards(){
        int n_cartas = 20;
        for (int i=0; i < n_cartas; i++){
            addCard(new Weapon(Dice.weaponPower(),
                    Dice.usesLeft()));   
        }   
    }
    
    
}
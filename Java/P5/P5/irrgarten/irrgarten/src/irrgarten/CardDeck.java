
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @brief Representa la baraja de cartas del juego
 * @author Isabel Morro Tabares
 */
abstract class CardDeck <T> {
    
    private ArrayList<T> cardDeck;
    
    public CardDeck(){
        cardDeck = new ArrayList<>();
    }
    
    abstract void addCards();
    
    protected void addCard (T card){
        cardDeck.add(card);
    }
    
    public T nextCard(){
        if (cardDeck.isEmpty()){
            addCards();
            Collections.shuffle(cardDeck);
        }
        return cardDeck.remove(0);
    }
    
}

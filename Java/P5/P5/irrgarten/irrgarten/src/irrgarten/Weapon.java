
package irrgarten;

/**
 * @brief   Armas que utiliza el jugador en los ataques durante los combates
 * @author  Isabel Morro Tabares
 */
public class Weapon extends CombatElement {

    // Constructor
    
    public Weapon (float _power, int _uses){
       super(_power, _uses);
    }
    
    // Métodos públicos
    
    public float attack (){
       return produceEffect();
    }

    // To string
    @Override
    public String toString(){
        String cadena = "W[";
        cadena += super.toString();
        return cadena;
    }
}

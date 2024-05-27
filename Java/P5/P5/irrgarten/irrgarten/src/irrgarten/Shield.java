
package irrgarten;

/**
 * @brief   Representa los escudos que utiliza el juagdor cuando se defiende 
 *          de un ataque de un monstruo
 * @author  Isabel Morro Tabares
 */

public class Shield extends CombatElement {

    // Constructor 
    
    public Shield (float _protection, int _uses){
       super (_protection, _uses);
    }
    
    // Métodos públicos
    
    public float protect () {
       return produceEffect();
    }
     
    // To string
    @Override
    public String toString(){
        String cadena = "S[";
        cadena += super.toString();
        return cadena;
    }
}

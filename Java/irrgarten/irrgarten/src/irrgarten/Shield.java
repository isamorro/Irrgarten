
package irrgarten;

/**
 * @brief   Representa los escudos que utiliza el juagdor cuando se defiende 
 *          de un ataque de un monstruo
 * @author  Isabel Morro Tabares
 */

public class Shield {
    
    // Variables privadas
    
    private float protection;
    private int uses;
    
    // Constructor 
    
    public Shield (float _protection, int _uses){
        protection = _protection;
        uses = _uses;
    }
    
    // Métodos públicos
    
    public float protect () {
    
        float result = 0;
        if (uses > 0){
            uses--;
            result = protection;
        }
        return result;
    }
    
    /**
     * @brief   Toma decisión de si un arma es descacartada
     * @return  true si descartado o false en caso contrario
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    // To String
    
    @Override
    public String toString (){
        String cadena = "S[" + Float.toString(protection) + ", " + 
                        Integer.toString(uses) + "]";
        return cadena;
    }
}

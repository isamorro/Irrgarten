
package irrgarten;

/**
 * @brief   Armas que utiliza el jugador en los ataques durante los combates
 * @author  Isabel Morro Tabares
 */
public class Weapon {

    // Variables privadas
    
    private float power;
    private int uses;
    Dice dice; //???????????????
    
    // Constructor
    
    public Weapon (float _power, int _uses){
        
        power = _power;
        uses = _uses;
        dice = new Dice();
    }
    
    // Métodos públicos

    public float attack (){
        
        float result = 0;
        
        if (uses > 0){
            uses--;
            result = power;
        }
        
        return result;
    }
    
    /**
     * @brief   Toma decisión de si un arma es descacartada
     * @return  true si descartado o false en caso contrario
     */
    public boolean discard(){
        return dice.discardElement(uses);
    }
    
    // To string
    @Override
    public String toString(){
        String cadena = "W[" + Float.toString(power) + ", " + 
                        Integer.toString(uses) + "]";
        return cadena;
    }
}

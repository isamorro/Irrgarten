
package irrgarten;

/**
 *
 * @author isabel
 */
abstract class CombatElement {
    
    private float effect;
    private int uses;
    
    public CombatElement(float effect, int uses){
        this.effect = effect;
        this.uses = uses;
    }
    
    protected float produceEffect(){
        float result = 0;
        
        if (uses > 0){
            uses--;
            result = effect;
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
    
    @Override
    public String toString(){
        String cadena = Float.toString(effect) + ", " + 
                        Integer.toString(uses) + "]";
        return cadena;    
    };
}

package irrgarten;

/**
 * @brief   Funcionalidades de los monstruos
 * @author  Isabel Morro Tabares
 */

public class Monster extends LabyrinthCharacter {
    
    // Variables privadas
    
    private static final int INITIAL_HEALTH = 5;
    
    // Constructor
    
    public Monster (String name, float intelligence, float strength){
        super(name, intelligence, strength, INITIAL_HEALTH);
        setPos(-1, -1);
    }
 
    // Métodos públicos
       
    /**
     * @brief Genera el resultado del combate
     * @return La fuerza del monstruo
     */
    @Override
    public float attack(){
        return Dice.intensity(getStrength());
    }
    
    @Override
    public boolean defend (float receivedAttack){
        boolean isDead = dead();
        if (!isDead){
            float defensiveEnergy = Dice.intensity(getIntelligence());
            if (defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
    
    @Override
    public String toString(){
        return super.toString() + "]";
    }
}

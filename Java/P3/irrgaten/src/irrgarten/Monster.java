
/**
 * @brief   Funcionalidades de los monstruos
 * @author  Isabel Morro Tabares
 */

package irrgarten;

public class Monster {
    
    // Variables privadas
    
    private static final int INITIAL_HEALTH = 5;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    // Constructor
    
    public Monster (String name, float intelligence, float strength){
        
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;  
        row = 0;
        col = 0;
    }
    
    // Métodos privados

    /**
     * @brief Decrementa en una unidad la salud del monstruo
     */
    public void gotWounded(){
        if (health > 0)
            health--;
    }
    
    // Métodos públicos
    
    /**
     * @brief Devuelve true si el monstruo está muerto
     * @return True si muerto, false en caso contrario
     */
    public boolean dead(){
        return health <= 0;
    }
    
    /**
     * @brief Genera el resultado del combate
     * @return La fuerza del monstruo
     */
    public float attack(){
        return Dice.intensity(strength);
    }
    
    public boolean defend (float receivedAttack){
        boolean isDead = dead();
        if (!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            if (defensiveEnergy < receivedAttack){
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }

    /**
     * @brief Modificador de atributos row y col
     * @param row fila
     * @param col columna
     */
    public void setPos(int row, int col){
        this.col = col;
        this.row = row;
    }
    
    /**
     * @brief Representación en cadena de caracteres del monstruo
     * @return cadena de caracteres
     */
    @Override
    public String toString(){
        String cadena;
        cadena = "[ Nombre: " + name + "\n"
                 + " Inteligencia: " + intelligence + "\n"
                 + " Fuerza: " + strength +"\n"
                 + " Salud: " + health + "\n"
                 + " Posicion: (" + row + ", " + col + ") ]\n";
        return cadena;
    }
    
}

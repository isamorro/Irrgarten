
package irrgarten;
import java.util.Random;

/**
 * @brief   Toma las decisiones del azar del juego
 * @author  Isabel Morro Tabares
 */
public class Dice {
    
    // Atributos de clase privados
    
    private final int MAX_USES = 5;
    private final float MAX_INTELLIGENCE = 10f;
    private final float MAX_STRENGTH = 10f;
    private final float RESURRECT_PROB = 0.3f;
    private final int WEAPONS_REWARD= 2;
    private final int SHIELDS_REWARD = 3;
    private final int HEALTH_REWARD = 5;
    private final int MAX_ATTACK = 3;
    private final int MAX_SHIELD = 2;
    
    private Random generator = new Random();
    // private Orientation orientation;
    
    
    // Métodos públicos
    
    /**
     * @brief   Devuelve un número de fila o columna aleatoria
     * @param   max número de filas o columnas del tablero
     * @return  número aleatorio entre 0 y max - 1
     */
    public int randomPos (int max){
        return generator.nextInt(max);
    }
    
    /**
     * @brief   Devuelve el índice del juagdor que comenzará la partida
     * @param   nplayers número de jugadores en la partida
     * @return  número aleatorio entre 0 y nplayers - 1
     */
    public int whoStarts (int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * @brief   Devuelve valor aleatorio de inteligencia
     * @return  Valor entre [0, MAX_INTELLIGENCE[
     */
    public float randomIntelligence(){
        return generator.nextFloat(MAX_INTELLIGENCE);
    } 
    
    /**
     * @brief   Devuelve valor aleatorio de fuerza
     * @return  Valor entre [0, MAX_STREGTH[
     */
    public float randomStrength(){
        return generator.nextFloat(MAX_STRENGTH);
    }
    
    /**
     * @brief   Indica si jugador muerto debe ser resucitado o no
     * @return  true si resucita, false en caso contrario
     */
    public boolean resurrectPlayer(){
        return generator.nextFloat() < RESURRECT_PROB;
    }
    
    /**
     * @bried   Indica cantidad de armas que recibirá jugador por ganar el combate
     * @return  Número aleatorio entre 0 y WEAPONS_REWARD (incluido)
     */
    public int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    /**
     * @brief   Indica cantidad de escudos que recibirá jugador por ganar el combate
     * @return  Número aleatorio entre 0 y SHIELDS_REWARD (incluido)
     */
    public int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    /**
     * @brief   Indica cantidad de unidades de salud que recibirá el jugador 
     *          por ganar el combate  
     * @return  Número aleatorio entre 0 y HEALTH_REWARD (incluido)
     */
    public int healthReward(){
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    /**
     * @brief   Indica potencia de las armas
     * @return  Número aleatorio entre 0 y MAX_ATTACK - 1
     */
    public float weaponPower(){
        return generator.nextInt(MAX_ATTACK);
    }

    /**
     * @brief   Indica potencia de los escudos
     * @return  Número aleatorio entre 0 y MAX_SHIELD - 1
     */
    public float shieldPower(){
        return generator.nextInt(MAX_SHIELD);
    }
 
    /**
     * @brief   Indica número de usos que se asigna a una arma o escudo
     * @return  Número aleatorio entre 0 y MAX_USES
     */
    public int usesLeft(){
        return generator.nextInt(MAX_USES + 1);
    }
    
    /**
     * @brief   Devuelve la cantidad de competencia aplicada
     * @param   competence competencia
     * @return  Valor aleatorio entre 0 y competence - 1
     */
    public float intensity (float competence){
        return generator.nextFloat(competence);
    }
        
    /**
     * @brief   Devuelve true con una probabilidad inversamente proporcional a 
     *          lo cercano que esté el parámetro del número máximo de usos que 
     *          puede tener un arma o escudo
     * @param   usesLeft
     * @return  true si es 0 y false si el número de usos es el máximo
     */
    public boolean discardElement(int usesLeft){
        return generator.nextInt(MAX_USES) >= usesLeft;
    }
    
}


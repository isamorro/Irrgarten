
package irrgarten;
import java.util.Random;
import java.util.ArrayList;

/**
 * @brief   Toma las decisiones del azar del juego
 * @author  Isabel Morro Tabares
 */
public class Dice {
    
    // Atributos de clase privados
    
    private static final int MAX_USES = 5;
    private static final float MAX_INTELLIGENCE = 10f;
    private static final float MAX_STRENGTH = 10f;
    private static final float RESURRECT_PROB = 0.3f;
    private static final int WEAPONS_REWARD= 2;
    private static final int SHIELDS_REWARD = 3;
    private static final int HEALTH_REWARD = 5;
    private static final int MAX_ATTACK = 3;
    private static final int MAX_SHIELD = 2;
    
    private static Random generator = new Random();
    
    // Métodos públicos
    
    /**
     * @brief   Devuelve un número de fila o columna aleatoria
     * @param   max número de filas o columnas del tablero
     * @return  número aleatorio entre 0 y max - 1
     */
    public static int randomPos (int max){
        return generator.nextInt(max);
    }
    
    /**
     * @brief   Devuelve el índice del juagdor que comenzará la partida
     * @param   nplayers número de jugadores en la partida
     * @return  número aleatorio entre 0 y nplayers - 1
     */
    public static int whoStarts (int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * @brief   Devuelve valor aleatorio de inteligencia
     * @return  Valor entre [0, MAX_INTELLIGENCE[
     */
    public static float randomIntelligence(){
        return generator.nextFloat()*MAX_INTELLIGENCE;
    } 
    
    /**
     * @brief   Devuelve valor aleatorio de fuerza
     * @return  Valor entre [0, MAX_STREGTH[
     */
    public static float randomStrength(){
        return generator.nextFloat()*MAX_STRENGTH;
    }
    
    /**
     * @brief   Indica si jugador muerto debe ser resucitado o no
     * @return  true si resucita, false en caso contrario
     */
    public static boolean resurrectPlayer(){
        return generator.nextFloat() < RESURRECT_PROB;
    }
    
    /**
     * @bried   Indica cantidad de armas que recibirá jugador por ganar el combate
     * @return  Número aleatorio entre 0 y WEAPONS_REWARD (incluido)
     */
    public static int weaponsReward(){
        return generator.nextInt(WEAPONS_REWARD + 1);
    }
    
    /**
     * @brief   Indica cantidad de escudos que recibirá jugador por ganar el combate
     * @return  Número aleatorio entre 0 y SHIELDS_REWARD (incluido)
     */
    public static int shieldsReward(){
        return generator.nextInt(SHIELDS_REWARD + 1);
    }
    
    /**
     * @brief   Indica cantidad de unidades de salud que recibirá el jugador 
     *          por ganar el combate  
     * @return  Número aleatorio entre 0 y HEALTH_REWARD (incluido)
     */
    public static int healthReward(){
        return generator.nextInt(HEALTH_REWARD + 1);
    }
    
    /**
     * @brief   Indica potencia de las armas
     * @return  Número aleatorio entre 0 y MAX_ATTACK - 1
     */
    public static float weaponPower(){
        return generator.nextFloat()*MAX_ATTACK;
    }

    /**
     * @brief   Indica potencia de los escudos
     * @return  Número aleatorio entre 0 y MAX_SHIELD - 1
     */
    public static float shieldPower(){
        return generator.nextFloat()*MAX_SHIELD;
    }
 
    /**
     * @brief   Indica número de usos que se asigna a una arma o escudo
     * @return  Número aleatorio entre 0 y MAX_USES
     */
    public static int usesLeft(){
        return generator.nextInt(MAX_USES + 1);
    }
    
    /**
     * @brief   Devuelve la cantidad de competencia aplicada
     * @param   competence competencia
     * @return  Valor aleatorio entre 0 y competence - 1
     */
    public static float intensity (float competence){
        return generator.nextFloat()*competence;
    }
        
    /**
     * @brief   Devuelve true con una probabilidad inversamente proporcional a 
     *          lo cercano que esté el parámetro del número máximo de usos que 
     *          puede tener un arma o escudo
     * @param   usesLeft
     * @return  true si es 0 y false si el número de usos es el máximo
     */
    public static boolean discardElement(int usesLeft){
        return generator.nextInt(MAX_USES) >= usesLeft;
    }
    
    /**
     * @brief Devuelve  la dirección del moviemiento preferente con una
     *        probabilidad proporcional al valor de inteligencia suministrado.
     *        En caso de no generar como resultado la dirección indicada por el 
     *        primer parámetro, se elige una al azar entrre las válidas
     * @param preference Dirección de movimiento preferente
     * @param validMoves Direcciones válidas
     * @param intelligence valor de inteligencia
     * @return direccion
     */
    public static Directions nextStep(Directions preference, 
                                       ArrayList<Directions> validMoves,
                                       float intelligence){
        Directions direccion = validMoves.get(generator.nextInt(validMoves.size()));
        if (generator.nextFloat() < (intelligence / MAX_INTELLIGENCE))
           direccion = preference; 
        return direccion;
    }
}



package irrgarten;
import java.util.ArrayList;

/**
 * @brief   Funcionalidades de los jugadores
 * @author  Isabel Morro Tabares
 */
public class Player {
   
    // Declaración de atributos
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    // private Orientation orientation;
    
    // Constructor
    
    public Player (char number, float intelligence, float strength){
        this.number = number;
        this.name = "Player #" + number;
        this.intelligence = intelligence;
        this.strength = strength;
        
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
        
        // DUDA
        row = 0;
        col = 0;
    }
    
    // Métodos privados
    
    // P3
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    // P3
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
    }
    
    private Weapon newWeapon() {
        
        Dice dice = new Dice();
        float power = dice.weaponPower();
        int uses = dice.usesLeft();
        Weapon w = new Weapon(power, uses);
  
        return w;
    }
    
    private Shield newShield(){
        
        Dice dice = new Dice();
        float protection = dice.shieldPower();
        int uses = dice.usesLeft();
        Shield s = new Shield(protection, uses);
        
        return s;
    }
    
    private float sumWeapons(){
        float suma = 0f;
        for (int i=0; i < weapons.size(); i++)
            suma += weapons.get(i).attack();
        return suma;
    }
    
    /**
     * @brief Suma la fuerza de las armas
     * @return suma de fuerzas
     */
    private float sumShields(){
        float suma = 0f;
        for(int i=0; i < shields.size(); i++)
            suma += shields.get(i).protect();
        return suma;
    }
    
    private float defensiveEnergy(){
        return sumShields() + intelligence;
    }
    
    // P3
    private boolean manageHit (float receivedAttack){
        throw new UnsupportedOperationException();
    }
    
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    private void gotWounded(){
        if (health > 0)
            health--;
    }
    
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    
    // Métodos públicos
    
    /**
     * @brief Realiza la tarea de resurrección
     */
    public void resurrect (){
        weapons.clear(); // Vaciamos weapons
        shields.clear(); // Vaciamos shields
        health = INITIAL_HEALTH;
        resetHits();
    }
    
    // Getters
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public char getNumber(){
        return number;
    }
    
    /*
    public String getName(){
        return name;
    }
    */
    
    /**
     * @brief Modifica la posición del jugador
     * @param row fila
     * @param col columna
     */
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    /**
     * @brief Devuelve true si está muerto 
     * @return true si muerto, false en caso contrario
     */
    public boolean dead(){
        return health == 0;
    }
    
    // P3
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        throw new UnsupportedOperationException();
    }
    
    /**
     * @brief Calcula la suma de la fuerza del jugador y la suma de lo aportado 
     *        por sus armas 
     * @return 
     */
    public float attack(){
        return sumWeapons() + strength;
    }
    
    public boolean defend (float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    // P3
    public void receivedReward(){
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString(){
        String cadena = "[Nombre: " + name + "\n"
                        + " Numero: " + number + "\n"
                        + " Inteligencia: " + intelligence + "\n"
                        + " Fuerza: " + strength + "\n"
                        + " Salud: " + health + "\n"
                        + " Posicion: (" + row + ", " + col + ") " + "\n"
                        + " Impactos consecutivos: " + consecutiveHits + "]";
        return cadena;
    }
   
}

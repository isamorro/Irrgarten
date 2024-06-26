
package irrgarten;
import java.util.ArrayList;
import java.util.Iterator;

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
        
        row = -1;
        col = -1;
    }
    
    // Métodos privados
    
    private void receiveWeapon(Weapon w){
        
        int tam_ini = weapons.size();
        
        for (int i=tam_ini-1; i >= 0; i--){
            if (weapons.get(i).discard())
                weapons.remove(i);
        }
        
        if (weapons.size() < MAX_WEAPONS) weapons.add(w);
        
    }
    
    private void receiveShield(Shield s){
        
        int tam_ini = shields.size();
        
        for (int i=tam_ini-1; i >= 0; i--){
            if (shields.get(i).discard())
                shields.remove(i);
        }
       
        if (shields.size() < MAX_SHIELDS) shields.add(s);
    }
    
    private Weapon newWeapon() {
        
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        Weapon w = new Weapon(power, uses);
        return w;
    }
    
    private Shield newShield(){
        
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        Shield s = new Shield(protection, uses);
        return s;
    }
    
    private float sumWeapons(){
        float suma = 0f;
        for (int i=0; i < weapons.size(); i++)
            suma += weapons.get(i).attack();
        return suma;
    }
    
    private float sumShields(){
        
        float suma = 0f;
        for(int i=0; i < shields.size(); i++)
            suma += shields.get(i).protect();
        return suma;
    }
    
    private float defensiveEnergy(){
        return sumShields() + intelligence;
    }
    
    private boolean manageHit (float receivedAttack){
        
        float defense = defensiveEnergy();
        boolean lose;
        
        if (defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }
        else resetHits();
        
        if ( consecutiveHits == HITS2LOSE || dead()){
            resetHits();
            lose = true;
        }
        else lose = false;
        
        return lose;
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
    
    public void resurrect (){
        weapons.clear(); 
        shields.clear();
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
    
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean dead(){
        return health <= 0;
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        Directions result;
        
        if (size>0 && !contained)
            result = validMoves.get(0);
        else
            result = direction;
        
        return direction;
    }
    
    public float attack(){
        return sumWeapons() + strength;
    }
    
    public boolean defend (float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    public void receiveReward(){
        
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for (int i=0; i < wReward; i++){
            Weapon wnew = newWeapon();
            receiveWeapon(wnew);
        }
        
        for (int i=0; i < sReward; i++){
            Shield snew = newShield();
            receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        health += extraHealth;
    }
    
    @Override
    public String toString(){
        String cadena = "[ Nombre: " + name + ","
                        + " Numero: " + number + ","
                        + " Inteligencia: " + intelligence + ","
                        + " Fuerza: " + strength + ","
                        + " Salud: " + health + ","
                        + " Posicion: (" + row + ", " + col + ") " + ","
                        + " Impactos consecutivos: " + consecutiveHits + "\n" 
                        + " Armas: ";
        
        if (!weapons.isEmpty()){
            for (Weapon wi : weapons) cadena += wi.toString() + " ";
        }
        else cadena += "No dispone de armas";
        cadena += "\n";
        cadena += " Shields: ";
        if (!shields.isEmpty()){
            for (Shield si : shields) cadena += si.toString() + " ";
        }
        else cadena += "No dispone de escudos";
        cadena += "]";
        
        return cadena;
    }
   
}

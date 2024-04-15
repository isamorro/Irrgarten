
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
        
        boolean discard;
        
        System.out.print(weapons.size());
        
        for (Weapon wi : weapons){
            discard = wi.discard();
            if(discard)
                weapons.remove(wi);
        }
        
        int size = weapons.size();
        
        if (size < MAX_WEAPONS) weapons.add(w);
        
    }
    
    private void receiveShield(Shield s){
        
        boolean discard;
        
        System.out.print(shields.size());
        
        for (Shield si : shields){
            discard = si.discard();
            if (discard)
                shields.remove(si);
        }
        
        int size = shields.size();
        
        if (size < MAX_SHIELDS) shields.add(s);
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
    
    public void receivedReward(){
        
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
        String cadena = "[ Nombre: " + name + "\n"
                        + " Numero: " + number + "\n"
                        + " Inteligencia: " + intelligence + "\n"
                        + " Fuerza: " + strength + "\n"
                        + " Salud: " + health + "\n"
                        + " Posicion: (" + row + ", " + col + ") " + "\n"
                        + " Impactos consecutivos: " + consecutiveHits + "]\n";
        return cadena;
    }
   
}

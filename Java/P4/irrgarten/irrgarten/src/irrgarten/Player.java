
package irrgarten;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @brief   Funcionalidades de los jugadores
 * @author  Isabel Morro Tabares
 */
public class Player extends LabyrinthCharacter {
   
    // Declaración de atributos
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    private char number;
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    private ShieldCardDeck shieldCardDeck;
    private WeaponCardDeck weaponCardDeck;
    
    // Constructor
    
    public Player (char number, float intelligence, float strength){
        super ("Player #" + number, intelligence, strength);
        this.number = number;
        
        weapons = new ArrayList<>();
        shields = new ArrayList<>();
        shieldCardDeck = new ShieldCardDeck();
        weaponCardDeck = new WeaponCardDeck();

        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
        setPos(-1, -1);
    }
    
    public Player (Player other){
        super (other);
        this.number = other.getNumber();
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
        return weaponCardDeck.nextCard();
    }
    
    private Shield newShield(){
        return shieldCardDeck.nextCard();
    }
    
    protected float sumWeapons(){
        float suma = 0f;
        if (weapons != null){
            for (int i=0; i < weapons.size(); i++)
                suma += weapons.get(i).attack();
        }
        return suma;
    }
    
    protected float sumShields(){
        
        float suma = 0f;
        if (shields != null){
            for(int i=0; i < shields.size(); i++)
                suma += shields.get(i).protect();
        }
        return suma;
    }
    
    protected float defensiveEnergy(){
        return sumShields() + getIntelligence();
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

    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    // Métodos públicos
    
    public void resurrect (){
        weapons.clear(); 
        shields.clear();
        setHealth (INITIAL_HEALTH);
        resetHits();
    }
    

    public char getNumber(){
        return number;
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
    
    @Override
    public float attack(){
        return sumWeapons() + getStrength();
    }
    
    
    @Override
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
        setHealth(getHealth() + extraHealth);
    }
    
    @Override
    public String toString(){
        String cadena = super.toString();
        cadena += " Numero: " + number + ","
                + " Impactos consecutivos: " 
                + consecutiveHits + "\n" 
                + " Armas: ";
        
        if (weapons != null){
            if (!weapons.isEmpty()){
                for (Weapon wi : weapons) cadena += wi.toString() + " ";
            }
        }
        else cadena += "No dispone de armas";
        cadena += "\n";
        cadena += " Shields: ";
        if (shields != null){
            if (!shields.isEmpty()){
                for (Shield si : shields) cadena += si.toString() + " ";
            }
        }
        else cadena += "No dispone de escudos";
        cadena += "]";
        
        return cadena;
    }
   
}

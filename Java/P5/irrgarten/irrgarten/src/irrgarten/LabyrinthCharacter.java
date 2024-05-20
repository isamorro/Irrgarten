
package irrgarten;

/**
 *
 * @author isabel
 */

abstract class LabyrinthCharacter {
    
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public LabyrinthCharacter (String name, float intelligence, float strength){
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        this (other.name, other.intelligence, other.strength);
        this.health = other.health;
        setPos(other.row, other.col);
    }
    
    public boolean dead(){
        return health <= 0;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    protected float getIntelligence(){
        return intelligence;
    }
    
    protected float getStrength(){
        return strength;
    }
    
    protected float getHealth(){
        return health;
    }
    
    protected void setHealth(float health){
        this.health = health;
    }
    
    public void setPos (int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        String cadena;
        cadena = "[ Nombre: " + name + ","
                 + " Inteligencia: " + intelligence + ","
                 + " Fuerza: " + strength + ","
                 + " Salud: " + health + ","
                 + " Posicion: (" + row + ", " + col + ")";
        return cadena;
    }
    
    protected void gotWounded(){
        if (health > 0)
            health--;
    }
    
    public abstract float attack();
    public abstract boolean defend (float attack);
    
}

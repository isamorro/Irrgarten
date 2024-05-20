/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author isabel
 */
public class FuzzyPlayer extends Player {
    
    public FuzzyPlayer (Player other){
        super(other);
        setName("Fuzzy " + other.getName());
    }
    
    @Override
    public Directions move (Directions direction, ArrayList<Directions>  validMoves){
        Directions result = super.move(direction, validMoves);
        return Dice.nextStep(result, validMoves, getIntelligence());
    }
    
    @Override
    public float attack(){
        return sumWeapons() + Dice.intensity(getStrength());
        
    }
    
    @Override
    protected float defensiveEnergy (){
        return sumShields()+ Dice.intensity(getIntelligence());
    }
}

        
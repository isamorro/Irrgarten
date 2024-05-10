/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author isabel
 */
public class WeaponCardDeck extends CardDeck<Weapon> {
    
    @Override
    protected void addCards(){
        int n_cartas = 20;
        for (int i=0; i < n_cartas; i++){
            addCard(new Weapon(Dice.weaponPower(),
                    Dice.usesLeft()));   
        }   
    }
    
    
}
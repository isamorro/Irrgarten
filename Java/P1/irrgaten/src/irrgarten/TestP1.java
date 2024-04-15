

package irrgarten;

/**
 * @brief   Función Main de Prueba de la Práctica 1
 * @author  Isabel Morro Tabares
 */
public class TestP1 {
    public static void main (String []args){
        
        // Probando clase Weapon
        
        Weapon w = new Weapon (2,6);
        
        System.out.println(w.toString());
        System.out.printf("%f" , w.attack());
        System.out.println(w.toString());
        System.out.println(w.discard());

        // Probando clase Shield
        System.out.println("********************");
        
        Shield s = new Shield(0,0);
        System.out.println(s.toString());
        System.out.printf("%f" , s.protect());
        System.out.println(s.toString());
        System.out.println(s.discard());
        
        // Probando clase Dice

        Dice d = new Dice();
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 4");
        for (int i=0; i <10; i++)
           System.out.println(d.randomPos(5));
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 9");
        for (int i=0; i <10; i++)
           System.out.println(d.whoStarts(10));
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 9.9999");
        for (int i=0; i <10; i++)
           System.out.println(d.randomIntelligence());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 9.9999");
        for (int i=0; i <10; i++)
           System.out.println(d.randomStrength());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de true o false, en general más false que true");
        for (int i=0; i <10; i++)
           System.out.println(d.resurrectPlayer());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 2");
        for (int i=0; i <10; i++)
           System.out.println(d.weaponsReward());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 3");
        for (int i=0; i <10; i++)
           System.out.println(d.shieldsReward());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 6");
        for (int i=0; i <10; i++)
           System.out.println(d.healthReward());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 2");
        for (int i=0; i <10; i++)
           System.out.println(d.weaponPower());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 1");
        for (int i=0; i <10; i++)
           System.out.println(d.shieldPower());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 5");
        for (int i=0; i <10; i++)
           System.out.println(d.usesLeft());
        
        System.out.println("********************");
        System.out.println("Debe salir un número de 0 a 5");
        for (int i=0; i <10; i++)
           System.out.println(d.intensity(6));
        
        System.out.println("********************");
        for (int i=0; i <10; i++)
           System.out.println(d.discardElement(10));
        
        // Probando clase GameState
        
        GameState gmst = new GameState("Laberinto", "Isabel", "Monstruo",
                                        5, true, "log");
        
        System.out.println("********************");
        System.out.println("ELEMENTOS DE GAMESTATE");

        System.out.println(gmst.getLabyrinthv());
        System.out.println(gmst.getPlayers());
        System.out.println(gmst.getMonsters());
        System.out.println(gmst.getCurrentPlayer());
        System.out.println(gmst.getWinner());
        System.out.println(gmst.getLog());
        
        // Probando EnumClass
        
        System.out.println("********************");
        System.out.println("PROBANDO ENUMCLASS");
        
        System.out.println(Directions.LEFT);
        System.out.println(Orientation.VERTICAL);
        System.out.println(GameCharacter.PLAYER);
    }
}

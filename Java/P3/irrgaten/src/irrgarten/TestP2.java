
package irrgarten;

/**
 * @brief   Función Main de Prueba de la Práctica 2
 * @author  Isabel Morro Tabares
 */
public class TestP2 {
 
    public static void main (String []args){
        
        System.out.println("********************");
        // Prueba clase MONSTER
        System.out.println("PRUEBA CLASE MONSTER");

            Monster m = new Monster ("Monstruo 1", 2.0f, 5.0f);

            // Prueba método DEAD
            System.out.print(m.toString());
            if (m.dead())
                System.out.println("\nMONSTRUO 1 MUERTO");
            else 
                System.out.println("\nMONSTRUO 1 VIVO");

            // Prueba método ATTACK
            System.out.printf("%f\n", m.attack());
            
            // Prueba método SETPOS
            System.out.println("Cambiamos monstruo a posición (-1,-1)\n");
            m.setPos(-1, -1);
            
            System.out.print(m.toString());
        
        System.out.println("\n********************");
        
        
        
        // Prueba clase PLAYER
        System.out.println("PRUEBA CLASE PLAYER");
        
            // Prueba de crear jugador
            Player p = new Player ('1', 5.0f, 5.0f);
            System.out.print(p.toString());
        
        System.out.println("\n********************");
       
        // Prueba clase LABERINTO
        System.out.println("PRUEBA CLASE LABYRINTH");
        
            Labyrinth l = new Labyrinth (5, 6, -1, 0);
            System.out.print(l.toString());

            // Prueba añadir un monstrruo al laberinto
                l.addMonster(1, 1, m); // ---> No se añade
                // No debe dar fallo
                l.addMonster(-3, 3, m);
            
            System.out.print(l.toString());
                
        System.out.println("********************");
        
        Labyrinth l1 = new Labyrinth (3, 6, 0, 0);
        System.out.print(l1.toString());
        
        // Prueba clase GAME --> NO SE PUEDE FALTAN MUCHOS MÉTODOS
  
    }

}
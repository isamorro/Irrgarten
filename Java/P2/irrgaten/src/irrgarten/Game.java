
    package irrgarten;
    import java.util.ArrayList;

    /**
     * @brief   Funciones del Juego
     * @author  Isabel Morro Tabares
     */

    public class Game {

       // Atributos de clase privados

       private static final int MAX_ROUNDS = 10;

        // Atributos privados de instancia

       private int currentplayerIndex;
       private String log;

       private ArrayList<Monster> monsters;
       private Labyrinth labyrinth;
       private Player currentPlayer;
       private ArrayList<Player> players;

       // Constructor 

       public Game (int nplayers){

           log = "";
           Dice dice = new Dice();
           
           // Creación de jugadores
           players = new ArrayList<>();
           for (int i=0; i < nplayers; i++){
               players.add(new Player(Character.forDigit(i, 10),
                       dice.randomIntelligence(),
                       dice.randomStrength()));
           }

           // Determinar quien empieza
           currentplayerIndex = dice.whoStarts(nplayers);
           currentPlayer = players.get(currentplayerIndex);

           monsters = new ArrayList<>();
           labyrinth = new Labyrinth(5, 6, 0, 1); // Valores a escoger

           // Configura el laberinto y reparte jugadores
           configureLabyrinth();
           labyrinth.spreadPlayers(players);
       }
   
    // Métodos privados
   
    /**
    * @brief Configura el laberinto. Es totalmente nuestra elección de laberinto.
    */
    private void configureLabyrinth(){
       
        Dice dice = new Dice();
        int n_bloques = 7;
        int n_monstruos = 3;
       
        // Colocación de muros
        
        for (int i = 0; i < n_bloques; i++){
           labyrinth.addBlock(Orientation.HORIZONTAL, 
    dice.randomPos(labyrinth.getNCols()), dice.randomPos(labyrinth.getNRows()));
        }
        
        // Colocación de monstruos
        for (int i = 0; i < n_monstruos; i++){
           Monster m = new Monster ("Monstruo #" + i, dice.randomIntelligence(),
                                             dice.randomStrength());
           labyrinth.addMonster (dice.randomPos(labyrinth.getNCols()), 
                                dice.randomPos(labyrinth.getNRows()),
                                m);
           monsters.add(m);
        }
    }
   
    /**
     * @brief actualiza los atributos del jugador de turno
     */
    private void nextPlayer(){
        
        Dice dice = new Dice();
        int anterior = currentplayerIndex;        
        do{
            currentplayerIndex = dice.whoStarts(players.size());
        } while (currentplayerIndex == anterior);
        
        currentPlayer = players.get(currentplayerIndex);
    }
   
    // P3
    private Directions actualDirection (Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    // P3
    private GameCharacter combat (Monster monster){
        throw new UnsupportedOperationException();
    }

    // P3
    private void manageReward (GameCharacter winner){
        throw new UnsupportedOperationException();
    }

    // P3
    private void manageResurrection(){
        throw new UnsupportedOperationException();
    }

    private void logPlayerWon(){
        log += " El JUGADOR HA GANADO EL COMBATE\n";
    }

    private void logMonsterWon(){
        log += " El MONSTRUO HA GANADO EL COMBATE\n";
    }

    private void logResurrected(){
        log += " El JUGADOR HA RESUCITADO\n";
    }

    private void logPlayerSkipTurn(){
        log += " El JUGADOR ESTÁ MUERTO, HA PERDIDO EL TURNO\n";
    }

    private void logPlayerNoOrders(){
        log += " El JUGADOR NO HA SEGUIDO LAS INSTRUCCIONES\n";
    }
    
    private void logNoMonster(){
        log += " El JUGADOR SE HA MOVIDO A UNA CELDA VACIA O NO HA PODIDO"
                + " MOVERSE \n";
    }

    private void logRounds(int round, int max){
        log += " SE HA ALCANZADO EL MÁXIMO DE RONDAS\n";
    }
   // Metodos públicos
   
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
   
   // P3
  public boolean nextStep (Directions preferredDirection){
      throw new UnsupportedOperationException();
  }
  
  /**
   * @brief Genera una instancia de GameState integrando toda la información
   *        del estado del juego
   * @return Objeto GameState
   */
  public GameState getGameState(){
   
    String nombres_jugadores = "[ ";
    for (int i=0; i < players.size(); i++)
        nombres_jugadores += players.get(i).toString() + "\n";
    nombres_jugadores += "]";

    String nombres_monstruos = "";
    for (int i=0; i < monsters.size(); i++)
        nombres_monstruos += monsters.get(i).toString() + "\n";
    nombres_monstruos += "]";
    
    return new GameState ("Laberinto ISA", nombres_jugadores, nombres_monstruos,
                        currentplayerIndex,
                        finished(), log);
  }  
}

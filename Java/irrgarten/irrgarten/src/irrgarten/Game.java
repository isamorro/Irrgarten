
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

       private int currentPlayerIndex;
       private String log;

       private Labyrinth labyrinth;
       private Player currentPlayer;
       private ArrayList<Monster> monsters;
       private ArrayList<Player> players;

       // Constructor 

       public Game (int nplayers){

           log = "";
           
           // Creación de jugadores
           players = new ArrayList<>();
           for (int i=0; i < nplayers; i++){
               players.add(new Player(Character.forDigit(i, nplayers),
                Dice.randomIntelligence(),
                   Dice.randomStrength()));
           }

           // Determinar quien empieza
           currentPlayerIndex = Dice.whoStarts(nplayers);
           currentPlayer = players.get(currentPlayerIndex);

           monsters =  new ArrayList<>();
           labyrinth = new Labyrinth(10, 10, 0, 1); // Valores a escoger

           // Configura el laberinto y reparte jugadores
           configureLabyrinth();
           labyrinth.spreadPlayers(players);
       }
   
    // Métodos privados
   
    /**
    * @brief Configura el laberinto. Es totalmente nuestra elección de laberinto.
    */
    private void configureLabyrinth(){
        
        labyrinth.addBlock(Orientation.HORIZONTAL, 2, 3, 1);
        
        Monster m1 = new Monster ("Monstruo #" + 1, 10f,10f);
        Monster m2 = new Monster ("Monstruo #" + 2, Dice.randomIntelligence(),
                                             Dice.randomStrength());
        Monster m3 = new Monster ("Monstruo #" + 3, Dice.randomIntelligence(),
                                             Dice.randomStrength());
        
        labyrinth.addMonster(5, 5, m1);
        labyrinth.addMonster(3, 7, m2);
        labyrinth.addMonster(1, 4, m3);
        
        monsters.add(m1);
        monsters.add(m2);
        monsters.add(m3);
       
    }
   
    /**
     * @brief actualiza los atributos del jugador de turno
     */
    private void nextPlayer(){
        
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size(); 
        currentPlayer = players.get(currentPlayerIndex);
    }

    private Directions actualDirection (Directions preferredDirection){
        
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        return output;
    }
    
    private GameCharacter combat (Monster monster){
        
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while ((!lose) && (rounds < MAX_ROUNDS)){
            
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            
            if (!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
            
        }
        
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }

    private void manageReward (GameCharacter winner){
        
        if (winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else {
            logMonsterWon();
        }
    }

    private void manageResurrection(){
     
        boolean resurrect = Dice.resurrectPlayer();
     
        if (resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }
        else {
            logPlayerSkipTurn();
        }
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
        log += " El JUGADOR ESTA MUERTO, HA PERDIDO EL TURNO\n";
    }

    private void logPlayerNoOrders(){
        log += " El JUGADOR NO HA SEGUIDO LAS INSTRUCCIONES\n";
    }
    
    private void logNoMonster(){
        log += " El JUGADOR SE HA MOVIDO A UNA CELDA VACIA O NO HA PODIDO"
                + " MOVERSE \n";
    }

    private void logRounds(int round, int max){
        log += " SE HA ALCANZADO EL MAXIMO DE RONDAS\n";
    }
    
    
   // Metodos públicos
   
    public boolean finished(){
        return labyrinth.haveAWinner();
    }
   
    public boolean nextStep (Directions preferredDirection){
        
        log = "";
        boolean dead = currentPlayer.dead();
        
        if (!dead){
            
            Directions direction = actualDirection(preferredDirection);
            
            if (direction != preferredDirection)
                logPlayerNoOrders();
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if (monster == null) logNoMonster();
            else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else 
            manageResurrection();
        
        boolean endGame = finished();
        
        if (!endGame)
            nextPlayer();
        
        return endGame;
    }
  
  /**
   * @brief Genera una instancia de GameState integrando toda la información
   *        del estado del juego
   * @return Objeto GameState
   */
  public GameState getGameState(){
   
    String jugadores = "";
    for (int i=0; i < players.size(); i++)
        jugadores += players.get(i).toString() + "\n";

    String monstruos = "";
    for (int i=0; i < monsters.size(); i++)
        monstruos += monsters.get(i).toString() + "\n";
    
    return new GameState (labyrinth.toString(), jugadores, monstruos,
                        currentPlayerIndex,
                        finished(), log);
  }  
}


package irrgarten;

/**
 * @brief   Permite almacenar una representación del estado completo del juego
 * @author  Isabel Morro Tabares
 */

public class GameState {
    
    // Atributos de instancia privados
    
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;         // Guarda información relevante de la ronda anterior
    
    // Constructor
    
    public GameState (String _labyrinth, String _players, String _monsters,
                      int _currentPlayer, boolean _winner, String _log){
        
        labyrinth = _labyrinth;
        players = _players;
        monsters = _monsters;
        currentPlayer = _currentPlayer;
        winner = _winner;
        log = _log;
        
    }
    
    // Consultores
    
    public String getLabyrinth (){
        return labyrinth;
    }
    
    public String getPlayers (){
        return players;
    }
    
    public String getMonsters(){
        return monsters;
    }
    
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public boolean isWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
   
    @Override
    public String toString(){
        String cadena = "";
       
        cadena += labyrinth + "\n";
        cadena += players + "\n";
        cadena += monsters + "\n";
        cadena += "TURNO: JUGADOR " + currentPlayer + "\n";
        
        if (winner) cadena += "HAY GANADOR!!!!" + "\n" + "FELICIDADES JUGADOR " 
                    + currentPlayer + "\n";
        
        if (!winner) cadena += log + "\n";
        
        return cadena;
   }
}

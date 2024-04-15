
package irrgarten;

/**
 * @brief   Permite almacenar una representación del estado completo del juego
 * @author  Isabel Morro Tabares
 */

public class GameState {
    
    // Atributos de instancia privados
    
    private String labyrinthv;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;         // Guarda información relevante de la ronda anterior
    
    // Constructor
    
    public GameState (String _labyrinthv, String _players, String _monsters,
                      int _currentPlayer, boolean _winner, String _log){
        
        labyrinthv = _labyrinthv;
        players = _players;
        monsters = _monsters;
        currentPlayer = _currentPlayer;
        winner = _winner;
        log = _log;
        
    }
    
    // Consultores
    
    public String getLabyrinthv (){
        return labyrinthv;
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
    
    public boolean getWinner(){
        return winner;
    }
    
    public String getLog(){
        return log;
    }
}

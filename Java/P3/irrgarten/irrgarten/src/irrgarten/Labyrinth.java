
package irrgarten;
import java.util.ArrayList;

/**
 * @brief   Funciones del Laberinto                                                                       
 * @author  Isabel Morro Tabares
 */
 

public class Labyrinth {
    
    // Declaración de atributos
    
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT_CHAR = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

   
    private Monster[][] monsters;
    private char[][] labyrinth;
    private Player[][] players;
 
    
    // Constructor
    
    public Labyrinth (int nRows, int nCols, int exitRow, int exitCol){
        
        this.nCols = nCols;
        this.nRows = nRows;
        this.exitCol = exitCol;
        this.exitRow = exitRow;
        
        monsters = new Monster [nRows][nCols];
        labyrinth = new char [nRows][nCols];
        players = new Player[nRows][nCols];
        
        for (int i = 0; i < nRows ; i++){
            for (int j = 0; j < nCols; j++){
                
                if (i == 0 || i == nRows-1 || j == 0 || j == nCols-1){
                    labyrinth[i][j] = BLOCK_CHAR; 
                }
                else labyrinth[i][j] = EMPTY_CHAR; 
              
                monsters[i][j] = null;
                players[i][j] = null;
            }
        }
        
        labyrinth [this.exitRow][this.exitCol] = EXIT_CHAR;
    }
    
    // Métodos privados
    
    private boolean posOK (int row, int col){
        return row < nRows && col < nCols && row >= 0 && col >= 0;
    }
    
    private boolean emptyPos (int row, int col){
        return labyrinth[row][col] == EMPTY_CHAR;
    }
    
    private boolean monsterPos (int row, int col){
        return labyrinth[row][col] == MONSTER_CHAR;
    }
    
    private boolean exitPos (int row, int col){
        return labyrinth[row][col] == EXIT_CHAR;
    }
    
    private boolean combatPos (int row, int col){
        return labyrinth[row][col] == COMBAT_CHAR;
    }
    
    private boolean canStepOn (int row, int col){
        return posOK(row,col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col));
    }
    
    private void updateOldPos(int row, int col){
        if (posOK(row,col)){
            if (combatPos(row, col)) labyrinth[row][col] = MONSTER_CHAR;
            else labyrinth[row][col] = EMPTY_CHAR; 
        }
    }
    
    private int[] dir2Pos (int row, int col, Directions direction){
        
        int resultado[] = new int[2];
        switch (direction) {
            case DOWN:
                resultado[0] = row + 1;
                resultado[1] = col;
                break;
            case UP:
                resultado[0] = row - 1;
                resultado[1] = col;
                break;
            case LEFT:
                resultado[0] = row;
                resultado[1] = col - 1;
                break;
            case RIGHT:
                resultado[0] = row;
                resultado[1] = col + 1;
                break;
        }
        
        return resultado;
    }
    
    private int[] randomEmptyPos (){
        
        int fila, columna;
        
        do {
            fila = Dice.randomPos(nRows);
            columna = Dice.randomPos(nCols);
        } while (!emptyPos(fila, columna));
        
        int resultado[] = new int[2];
        resultado[0] = fila;
        resultado[1] = columna;
        
        return resultado;
    }
    

    private Monster putPlayer2D (int oldRow, int oldCol, int row, int col, Player player){
        
        Monster output = null;
        
        if (canStepOn(row,col)){
            
            if(posOK(oldRow, oldCol)){
                Player p = players[oldRow][oldCol];
                if (p == player){
                    updateOldPos(oldRow, oldCol);
                    players[oldRow][oldCol] = null;
                }
            }
            
            boolean monsterPos = monsterPos(row, col);
            
            if (monsterPos){
                labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }
            else {
                char number = player.getNumber();
                labyrinth[row][col] = number;
            }
            
            players[row][col] = player;
            player.setPos(row, col); 
        }
        
        return output;
    }
    
    // Métodos públicos

    public void spreadPlayers (ArrayList<Player> players){
        for (Player p : players){
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    

    public boolean haveAWinner(){
        return !(players[exitRow][exitCol] == null);
    }
    
    
    public void addMonster (int row, int col, Monster monster){
        if (posOK(row,col)){
            if (emptyPos(row,col)){
                labyrinth[row][col] = MONSTER_CHAR;
                monsters[row][col] = monster;
                monster.setPos(row, col);
            }
        }
    }
        
    
    public Monster putPlayer (Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        return monster;
    }
    
    public void addBlock (Orientation orientation, int startRow, int startCol, int length) {
        
        int incRow, incCol;
        
        if (orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }
        else {
            incRow = 0;
            incCol = 1;
        }
        
        int row = startRow;
        int col = startCol;
        
        while (posOK(row,col) && emptyPos(row,col) && length > 0){
            labyrinth[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row, int col){
       
        ArrayList<Directions> output = new ArrayList<>();
        
        if (canStepOn(row+1, col)) output.add(Directions.DOWN);
        if (canStepOn(row-1, col)) output.add(Directions.UP);
        if (canStepOn(row, col+1)) output.add(Directions.RIGHT);
        if (canStepOn(row, col-1)) output.add(Directions.LEFT);
        
        return output;
    }
    
    // To String
    @Override
    public String toString(){
        
        String cadena = "";
        for (int i = 0; i < nRows; i++){
            for (int j = 0; j < nCols; j++){
                cadena += labyrinth[i][j];
                if (j == nCols -1) cadena += "\n";
            }
        }
        
        return cadena;
    }
}

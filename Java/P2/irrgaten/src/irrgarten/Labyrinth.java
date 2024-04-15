
package irrgarten;
import java.util.ArrayList;

/**
 * @brief   Funciones del Laberinto
 *          EL (0,0) ES LA POSICIÓN SUPERIOR IZQUIERDA --> LAS FILAS SON NEGATIVAS
 *                                                         Y LAS COLUMNAS POSITIVAS                                                                          
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
        
        
        
        // Casllas vacías y muros
        for (int i = 0; i < nRows ; i++){
            for (int j = 0; j < nCols; j++){
                if ((i == 0 || i == nRows-1 || j == 0 || j == nCols-1)){
                    labyrinth[i][j] = BLOCK_CHAR; 
                }
                else {
                    labyrinth[i][j] = EMPTY_CHAR; 
                }
            }
        }
        
        // Casilla de salida
        labyrinth [this.exitRow + nRows - 1][this.exitCol] = EXIT_CHAR;
        
    }
    
    // Métodos privados
    
    /**
     * @brief Comprueba que la posición está dentro del laberinto
     * @param row Fila
     * @param col Columna
     * @return true si está dentro, false en caso contrario
     */
    private boolean posOK (int row, int col){
        return -row < nRows && col < nCols && row <= 0 && col >= 0;
    }
    
    /**
     * @brief Comprueba si la posición es vacía
     * @param row Fila
     * @param col Columna
     * @return True si vacía, false en caso contrario
     */
    private boolean emptyPos (int row, int col){
        return labyrinth[nRows+row-1][col] == EMPTY_CHAR;
    }
    
    /**
     * @brief Comprueba si la posición contiene un monstruo
     * @param row Fila
     * @param col Columna
     * @return True si lo tiene, false en caso contrario
     */
    private boolean monsterPos (int row, int col){
        return labyrinth[nRows+row-1][col] == MONSTER_CHAR;
    }
    
    /**
     * @brief Comprueba si la posición es la de salida
     * @param row Fila
     * @param col Columna
     * @return True si lo es, false en caso contrario
     */
    private boolean exitPos (int row, int col){
        return labyrinth[nRows+row-1][col] == EXIT_CHAR;
    }
    
    /**
     * @brief Comprueba si en la posición hay un combate
     * @param row Fila
     * @param col Columna
     * @return True si lo hay, false en caso contrario
     */
    private boolean combatPos (int row, int col){
        return labyrinth[nRows+row-1][col] == COMBAT_CHAR;
    }
    
    /**
     * @brief Indica si la posicion es correcta y, o es vacía, o hay un monstruo,
     *        o es la casilla de salida
     * @param row Fila
     * @param col Columna
     * @return True si lo es, False en caso contrario
     */
    private boolean canStepOn (int row, int col){
        return posOK(row,col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col));
    }
    
    /**
     * @brief Actualiza la casilla
     * @param row Fila
     * @param col Columna
     */
    private void updateOldPos(int row, int col){
        if (posOK(row,col)){
            if (labyrinth[nRows+row-1][col] == COMBAT_CHAR){
                labyrinth[nRows+row-1][col] = MONSTER_CHAR;
            }
            else {
                labyrinth[nRows+row-1][col] = EMPTY_CHAR;
            }
        }
    }
    
    /**
     * @brief 
     * @param row
     * @param col
     * @param direction
     * @return 
     */
    private int[] dir2Pos (int row, int col, Directions direction){
        
        int resultado[] = new int[2];
        switch (direction) {
            case DOWN:
                resultado[0] = row - 1;
                resultado[1] = col;
                break;
            case UP:
                resultado[0] = row + 1;
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
    
    /**
     * @brief Genera posición aleatoria del laberinto aseguranfo que esté vacía 
     * @return la posición
     */
    private int[] randomEmpyPos (){
        
        Dice dice = new Dice();
        int fila, columna;
        
        do{
            fila = dice.randomPos(nRows);
            columna = dice.randomPos(nCols);
        }
        while (labyrinth[fila][columna] != EMPTY_CHAR);
        
        int resultado[] = new int[2];
        resultado[0] = fila;
        resultado[1] = columna;
        
        return resultado;
    }
    
    // P3
    private Monster putPlayer2D (int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
    
    // Métodos públicos
    
    public int getNRows(){
        return nRows;
    }
    
    public int getNCols(){
        return nCols;
    }
    
    // P3
    public void spreadPlayers (ArrayList<Player> players){
        throw new UnsupportedOperationException();
    }
    
    /**
     * @brief Devuelve true si hay un jugador en la casilla de salida
     * @return True di lo hay, false en caso contrario
     */
    public boolean haveAWinner(){
        return !(players[nRows+exitRow-1][exitCol] == null);
    }
    
    // To String
    @Override
    public String toString(){
        
        String cadena = "";
        for (int i = nRows-1; i >= 0; i--){
            for (int j = 0; j < nCols; j++){
                cadena += labyrinth[i][j];
                if (j == nCols -1) cadena += "\n";
            }
        }
        
        return cadena;
    }
    
    /**
     * @brief Añade un monstruo al laberinto 
     * @param row Fila del monstruo
     * @param col Columna del monstruo
     * @param monster Monstruo a añadir
     */
    public void addMonster (int row, int col, Monster monster){
        if (posOK(row,col)){
            if (emptyPos(row,col)){
                labyrinth[nRows+row-1][col] = MONSTER_CHAR;
                monsters[nRows+row-1][col] = monster;
                monster.setPos(row, col);
            }
        }
    }
        
    
    public Monster putPlayer (Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    public void addBlock (Orientation orientation, int startRow, int length) {
        throw new UnsupportedOperationException();
    }
    
    public Directions[] validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    
}

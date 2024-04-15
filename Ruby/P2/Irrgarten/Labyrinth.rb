#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funciones del Laberinto
    EL (0,0) ES LA POSICIÓN SUPERIOR IZQUIERDA 
    LA (NROWS-1, NCOLS-1) ES LA POSCIÓN INFERIOR DERECHA
    
=end

require_relative 'Dice'
require_relative 'Monster'
require_relative 'Shield'
require_relative 'Orientation'
require_relative 'Directions'

module Irrgarten
    class Labyrinth

    private

        # Atributos de clase

        @@BLOCK_CHAR = 'X'
        @@EMPTY_CHAR = '-'
        @@MONSTER_CHAR = 'M'
        @@COMBAT_CHAR = 'C'
        @@EXIT_CHAR = 'E'
        @@ROW = 0
        @@COL = 1

        # Métodos privados

        def posOK (row, col)
            row < @nRows && col < @nCols && row >= 0 && col >= 0
        end

        def emptyPos (row, col)
            @labyrinth[row][col] == @@EMPTY_CHAR
        end

        def monsterPos(row, col)
            @labyrinth[row][col] == @@MONSTER_CHAR
        end

        def exitPos (row, col)
            @labyrinth[row][col] == @@EXIT_CHAR
        end

        def combatPos (row, col)
            @labyrinth[row][col] == @@COMBAT_CHAR
        end

        def canStepOn (row, col)
            posOK(row, col) && (emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col))
        end

        def updateOldPos (row, col)
            if posOK(row,col)
                if @labyrinth[row][col] == COMBAT_CHAR
                    @labyrinth[row][col] = MONSTER_CHAR
                else 
                    @labyrinth[row][col] = EMPTY_CHAR
                end
            end
        end

        def dir2Pos (row, col, direction)
            
            resultado[] = Array.new

            case direction
                when DOWN
                    resultado[0] = row - 1
                    resultado[1] = col
                when UP
                    resultado[0] = row + 1
                    resultado[1] = col
                when LEFT
                    resultado[0] = row
                    resultado[1] = col - 1
                else RIGHT
                    resultado[0] = row
                    resultado[1] = col + 1 
            end
            
            resultado
        end

        def randomemptyPos

            dice = Dice.new

            begin
                fila = dice.randomPost(@nRows)
                columna = dice.randomPost(@nCols)
            end while @@labyrinth[fila][columna] != @@EMPTY_CHAR

            resultado = Array.new
            resultado[0] = fila
            resultado[1] = columna
        end

        # P3
        def putPlayer2D (oldRow, oldCol, row, col, player)
        end

    public

        # Constructor

        def initialize (nRows, nCols, exitRow, exitCol)

            @nRows = nRows
            @nCols = nCols
            @exitRow = exitRow
            @exitCol = exitCol

            @monsters = Array.new(@nRows)
            for i in (0..@nRows-1)
                @monsters[i] = Array.new(@nCols)
            end
            @players = Array.new(@nRows)
            for i in (0..@nRows-1)
                @players[i] = Array.new(@nCols)
            end
            @labyrinth = Array.new(@nRows)
            for i in (0..@nRows-1)
                @labyrinth[i] = Array.new(@nCols)
            end

            for i in (0..@nRows-1) 
                for j in  (0..@nCols-1)
                    if (i == 0 || i == @nRows-1 || j == 0 || j == @nCols-1)
                        @labyrinth[i][j] = @@BLOCK_CHAR
                    else
                        @labyrinth[i][j] = @@EMPTY_CHAR
                    end 
                end 
            end

            @labyrinth[@exitRow][@exitCol] = @@EXIT_CHAR

        end

        # Métodos públicos

        def nRows
            @nRows
        end 

        def nCols
            @nCols
        end

        # P3
        def spreadPlayers (players)
        end

        def haveAWinner
            !(@players[@exitRow][@exitCol] == nil)
        end

        def to_s
            cadena = ""
            for i in (0..@nRows-1)
                for j in (0..@nCols-1)
                    cadena += @labyrinth[i][j]
                    if j == @nCols-1
                        cadena += "\n"
                    end
                end
            end
            cadena
        end

        def addMonster (row, col, monster)
            if posOK(row,col)
                if emptyPos(row,col)
                    @labyrinth[row][col] = @@MONSTER_CHAR
                    @monsters[row][col] = monster
                    monster.setPos(row, col)
                end
            end
        end

        # P3
        def putPlayer (direction, player)
        end

        # P3
        def addBlock(orientation, startRow, startCol, length)
        end

        # P3
        def validMoves(row, col)
        end
    end
end
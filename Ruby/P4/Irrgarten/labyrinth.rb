#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funciones del Laberinto
    
=end

require_relative 'dice'
require_relative 'monster'
require_relative 'shield'
require_relative 'orientation'
require_relative 'directions'
require_relative 'fuzzy_player'

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

        def pos_ok (row, col)
            row < @n_rows && col < @n_cols && row >= 0 && col >= 0
        end

        def empty_pos (row, col)
            @labyrinth[row][col] == @@EMPTY_CHAR
        end

        def monster_pos(row, col)
            @labyrinth[row][col] == @@MONSTER_CHAR
        end

        def exit_pos (row, col)
            @labyrinth[row][col] == @@EXIT_CHAR
        end

        def combat_pos (row, col)
            @labyrinth[row][col] == @@COMBAT_CHAR
        end

        def can_step_on (row, col)
            pos_ok(row, col) && (empty_pos(row,col) || monster_pos(row,col) || exit_pos(row,col))
        end

        def update_old_pos (row, col)
            if pos_ok(row,col)
                if combat_pos(row, col)
                    @labyrinth[row][col] = @@MONSTER_CHAR
                else 
                    @labyrinth[row][col] = @@EMPTY_CHAR
                end
            end
        end

        def dir_2_pos (row, col, direction)
            
            resultado = Array.new

            case direction
                when Directions::DOWN
                    resultado[0] = row + 1
                    resultado[1] = col
                when Directions::UP
                    resultado[0] = row - 1
                    resultado[1] = col
                when Directions::LEFT
                    resultado[0] = row
                    resultado[1] = col - 1
                else Directions::RIGHT
                    resultado[0] = row
                    resultado[1] = col + 1 
            end
            
            resultado
        end

        def random_empty_pos

            begin
                fila = Dice.random_pos(@n_rows)
                columna = Dice.random_pos(@n_cols)
            end while !(empty_pos(fila, columna))

            resultado = Array.new
            resultado[0] = fila
            resultado[1] = columna

            resultado

        end

        def put_player_2D (old_row, old_col, row, col, player)

            output = nil

            if (can_step_on(row,col))

                if (pos_ok(old_row, old_col))
                    p = @players[old_row][old_col]
                    if (p == player)
                        update_old_pos(old_row, old_col)
                        @players[old_row][old_col] = nil
                    end
                end 

                monster_pos = monster_pos(row, col)

                if (monster_pos)
                    @labyrinth[row][col] = @@COMBAT_CHAR
                    output = @monsters[row][col]
                else 
                    number = player.number
                    @labyrinth[row][col] = number
                end

                @players[row][col] = player

                player.set_pos(row, col)

            end

            output
        end

    public

        # Constructor

        def initialize (n_rows, n_cols, exit_row, exit_col)

            @n_rows = n_rows
            @n_cols = n_cols
            @exit_row = exit_row
            @exit_col = exit_col

            @monsters = Array.new(@n_rows)
            for i in (0..@n_rows-1)
                @monsters[i] = Array.new(@n_cols)
            end
            @players = Array.new(@n_rows)
            for i in (0..@n_rows-1)
                @players[i] = Array.new(@n_cols)
            end
            @labyrinth = Array.new(@n_rows)
            for i in (0..@n_rows-1)
                @labyrinth[i] = Array.new(@n_cols)
            end

            for i in (0..@n_rows-1) 
                for j in  (0..@n_cols-1)
                    
                    if (i == 0 || i == @n_rows-1 || j == 0 || j == @n_cols-1)
                        @labyrinth[i][j] = @@BLOCK_CHAR
                    else
                        @labyrinth[i][j] = @@EMPTY_CHAR
                    end 

                    @monsters[i][j] = nil
                    @players[i][j] = nil

                end 
            end

            @labyrinth[@exit_row][@exit_col] = @@EXIT_CHAR

        end

        # Métodos públicos

        def spread_players (players)

            players.each do |p| 
                pos = random_empty_pos()
                put_player_2D(-1, -1, pos[@@ROW], pos[@@COL], p)
            end

        end

        def have_a_winner
            !(@players[@exit_row][@exit_col] == nil)
        end

        def add_monster (row, col, monster)
            if pos_ok(row,col)
                if empty_pos(row,col)
                    @labyrinth[row][col] = @@MONSTER_CHAR
                    @monsters[row][col] = monster
                    monster.set_pos(row, col)
                end
            end
        end

        def put_player (direction, player)

            old_row = player.row
            old_col = player.col

            new_pos = dir_2_pos(old_row, old_col, direction)
            monster = put_player_2D(old_row, old_col, new_pos[@@ROW], new_pos[@@COL], player)
            return monster

        end

        def add_block (orientation, start_row, start_col, length)

            if (orientation == Orientation::VERTICAL)
                inc_row = 1
                inc_col = 0
            else
                inc_row = 0
                inc_col = 1
            end 

            row = start_row
            col = start_col

            while (pos_ok(row,col) && empty_pos(row,col) && length > 0)
                @labyrinth[row][col] = @@BLOCK_CHAR
                length -=1
                row += inc_row
                col += inc_col
            end

        end


        def valid_moves(row, col)
            
            output = Array.new

            if (can_step_on(row+1, col))
                output << Directions::DOWN 
            end

            if (can_step_on(row-1, col))
                output << Directions::UP 
            end

            if (can_step_on(row, col+1))
                output << Directions::RIGHT 
            end

            if (can_step_on(row, col-1))
                output << Directions::LEFT 
            end

            return output
            
        end

        def resurrection_fuzzy_player (row, col, fp)
            @players[row][col] = fp
        end

        def to_s
            cadena = ""
            for i in (0..@n_rows-1)
                for j in (0..@n_cols-1)
                    cadena << @labyrinth[i][j]
                    if j == @n_cols-1
                        cadena << "\n"
                    end
                end
            end
            cadena
        end

    end
end
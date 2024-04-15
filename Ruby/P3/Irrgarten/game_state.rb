#encoding:utf-8


module Irrgarten
    class GameState

        # Constructor 

        def initialize (labyrinthv, players, monsters, current_player,
                        winner, log)
            @labyrinthv = labyrinthv 
            @players = players 
            @monsters = monsters 
            @current_player = current_player 
            @winner = winner 
            @log = log
        end

        # Métodos Públicos
        
        def labyrinthv
            @labyrinthv
        end

        def players
            @players
        end

        def monsters
            @monsters
        end

        def current_player
            @currentPlayer
        end

        def winner
            @winner
        end

        def log
            @log
        end

        def to_s

            cadena = @labyrinth + "\n"
            cadena += @players + "\n"
            cadena += @monsters + "\n"
            cadena += "TURNO: JUAGADOR " + @current_player + "\n"

            if (@winner) 
                cadena += "HAY GANADOR!!!!" + "\n" + "FELICIDADES JUGADOR " 
                + @current_player + "\n";
            end

            cadena += @log + "\n"

            cadena

        end

    end
end
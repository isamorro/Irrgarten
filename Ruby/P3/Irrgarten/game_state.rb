#encoding:utf-8


module Irrgarten
    class GameState

        # Constructor 

        def initialize (labyrinth, players, monsters, current_player,
                        winner, log)
            @labyrinth = labyrinth 
            @players = players 
            @monsters = monsters 
            @current_player = current_player 
            @winner = winner 
            @log = log
        end

        # Métodos Públicos
        
        def labyrinth
            @labyrinth
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

            cadena = ""
            cadena << @labyrinth << "\n"
            cadena << @players << "\n"
            cadena << @monsters << "\n"
            cadena << "TURNO: JUGADOR " << @current_player.to_s << "\n"

            if (@winner) 
                cadena << "HAY GANADOR!!!!" << "\n" << "FELICIDADES JUGADOR " <<
                @current_player.to_s << "\n";
            end

            cadena << @log << "\n"

            cadena

        end

    end
end
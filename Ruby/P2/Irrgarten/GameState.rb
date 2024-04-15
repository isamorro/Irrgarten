#encoding:utf-8


module Irrgarten
    class GameState

        # Constructor 

        def initialize (labyrinthv, players, monsters, currentPlayer,
                        winner, log)
            @labyrinthv = labyrinthv 
            @players = players 
            @monsters = monsters 
            @currentPlayer = currentPlayer 
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

        def currentPlayer
            @currentPlayer
        end

        def winner
            @winner
        end

        def log
            @log
        end

    end
end
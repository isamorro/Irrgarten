#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funciones del Juego

=end

require_relative 'Dice'
require_relative 'GameCharacter'
require_relative 'GameState'
require_relative 'Monster'
require_relative 'Labyrinth'
require_relative 'Player'

module Irrgarten
    class Game

    private

        # Atributos de clase privados
        @@MAX_ROUNDS = 10

        # Métodos privados

        # Configrua el laberinto. Es totalmente elección propia,
        def configureLabyrinth
            
            dice = Dice.new
            n_bloques = 7
            n_monstruos = 3

            # Colocación de muros

            for i in (0..n_bloques)
                @labyrinth.addBlock (Orientation::HORIZONTAL,
                                    dice.randomPos(@labyrinth.nRows),
                                    dice.randomPos(@labyrinth.nCols))
            end

            # Colocaicón de monstruos
            for i in (0..n_monstruos)
                m = Monster.new ("Monstruo #" + i, dice.randomIntelligence,
                                dice.randomStrength)
                @labyrinth.addMonster   (dice.randomPos(@labyrinth.nRows),
                                        dice.randomPos(@labyrinth.nCols),
                                        m)
                @monsters << m
            end
        end

        # Actualiza los atributos del jugador de turno
        def nextPlayer
            
            dice = Dice.new
            anterior = @currentplayerIndex

            begin
                @currentplayerIndex = dice.whostarts(@players.length)
            end while @currentplayerIndex == anterior

            @currentPlayer = @players[@currentplayerIndex]
        end

        # P3
        def actualDirection (preferredDirection)
        end

        # P3
        def combat (monster)
        end

        # P3
        def manageReward(winner)
        end

        # P3
        def manageResurrection
        end

        def logPlayerWon
            @log << "EL JUGADOR HA GANADO EL COMBATE \n"
        end

        def logMonsterWon
            @log << "EL MONSTRUO HA GANADO EL COMBATE \n"
        end

        def logResurrected
            @log << "EL JUGADOR HA RESUCITADO \n"
        end

        def logPlayerSkipTurn
            @log << "EL JUGADOR ESTÁ MUERTO, HA PERDIDO EL TURNO \n"
        end

        def logPlayerNoOrders
            @log << "EL JUGADOR NO HA SEGUIDO LAS INTURCCIONES \n"
        end

        def logNoMonster
            @log << "EL JUGADOR SE HA MOVIDO A UNA CELDA VACIA O NO HA PODIDO MOVERSE \n"
        end

        def logRounds (rounds, max)
            @log << "SE HA ALCANZADO EL MAXIMO DE RONDAS \n"
        end

        # Métodos publicos 

        def initialize (nplayers)
            
            @log = ""
            dice = Dice.new

            # Creación de jugadores
            @players[] = Array.new
            for i in (0..nplayers)
                @players << Player.new (i.to_s, dice.randomIntelligence
                                        dice.randomStrength)
            end

            # Determinar quién empieza
            @currentplayerIndex = dice.whoStarts(nplayers)
            @currentPlayer = @players[@currentplayerIndex]

            @monsters[] = Array.new
            @labyrinth = Labyrinth.new(5, 6, 0, 1)

            configureLabyrinth()
            @labyrinth.spreadPlayers(@players)
        end

        def finished
            @labyrinth.haveAWinner
        end

        # P3
        def nextStep (preferredDirection)
        end

        def getGameState

            nombres_jugadores = "[ "
            for i in (0..@players.length)
                nombres_jugadores << @players[i].to_s <<  "\n"
            end
            nombre_jugadores << " ]"

            nombres_monstruos = "[ "
            for i in (0..@monsters.legth)
                nombres_monstruos << @monsters[i].to_s << "\n"
            end
            nombre_monstruos = " ]"

            new GameState   ("Laberinto ISA", nombres_jugadores, nombres_monsturos,
                            @currentplayerIndex, finished(), @log)

        end

    end
end
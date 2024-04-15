#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Función Main de Prueba de la Práctica 1

=end
require_relative 'Directions'
require_relative 'GameCharacter'
require_relative 'Orientation'

require_relative 'Weapon'
require_relative 'Shield'
require_relative 'Dice'
require_relative 'GameState'

module Irrgarten
    class TestP1
        def self.main 
            
            # PROBANDO CLASS WEAPON

            w = Weapon.new(2,6)
            puts w.to_s
            puts w.attack
            puts w.to_s
            puts w.discard

            # PROBANDO CLASS SHIELD

            s = Shield.new(0,0)
            puts s.to_s
            puts s.protect
            puts s.to_s
            puts s.discard

            # PROBANDO CLASS DICE

            dice = Dice.new

            puts '*********************************'
            i = 0
            puts 'Debe salir un número de 0 a 4' 
            for i in 0..10
                puts dice.randomPos(5)
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 9'
            for i in 0..10
                puts dice.whoStarts(10)
            end

            puts '*********************************' 
            puts 'Debe salir un número de 0 a 9.9999'
            for i in 0..10
                puts dice.randomIntelligence
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 9.9999'
            for i in 0..10
                puts dice.randomStrength
            end

            puts '*********************************'
            puts 'Debe salir un número de true o false, en general más false que true'
            for i in 0..10
                puts dice.resurrectPlayer
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 2'
            for i in 0..10
                puts dice.weaponsReward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 3'
            for i in 0..1
                puts dice.shieldsReward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 6'
            for i in 0..10
                puts dice.healthReward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 2'
            for i in 0..10
                puts dice.weaponPower
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 1'
            for i in 0..10
                puts dice.shieldPower
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 5'
            for i in 0..10
                puts dice.usesLeft
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 5'
            for i in 0..10
                puts dice.intensity(6)
            end

            puts '*********************************'
            for i in 0..10
                puts dice.discardElement(10)
            end

            # PROBANDO CLASS GAMESTATE
            
            labyrinthv = "Laberinto"
            players = "Isabel"
            monsters = "Monstruo"
            currentPlayer = 5
            winner = true
            log = "log"

            gamestate = GameState.new(labyrinthv, players, monsters, currentPlayer,winner, log)
            
            puts '*********************************'
            puts 'ELEMENTOS DE GAMESTATE'
            puts gamestate.labyrinthv 
            puts gamestate.players 
            puts gamestate.monsters 
            puts gamestate.currentPlayer
            puts gamestate.winner 
            puts gamestate.log 

            # PROBANDO "ENUMCLASS" VARIOS

            puts '*********************************'
            puts 'PROBANDO ENUMCLASS'
            puts Directions::LEFT
            puts Orientation::VERTICAL
            puts GameCharacter::PLAYER

        end
    end
end


Irrgarten::TestP1.main
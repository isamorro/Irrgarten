#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Función Main de Prueba de la Práctica 1

=end
require_relative 'directions'
require_relative 'game_character'
require_relative 'orientation'

require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'game_state'

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

            puts '*********************************'
            i = 0
            puts 'Debe salir un número de 0 a 4' 
            for i in 0..10
                puts Dice.random_pos(5)
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 9'
            for i in 0..10
                puts Dice.who_starts(10)
            end

            puts '*********************************' 
            puts 'Debe salir un número de 0 a 9.9999'
            for i in 0..10
                puts Dice.random_intelligence
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 9.9999'
            for i in 0..10
                puts Dice.random_strength
            end

            puts '*********************************'
            puts 'Debe salir un número de true o false, en general más false que true'
            for i in 0..10
                puts Dice.resurrect_player
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 2'
            for i in 0..10
                puts Dice.weapons_reward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 3'
            for i in 0..1
                puts Dice.shields_reward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 6'
            for i in 0..10
                puts Dice.health_reward
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 2'
            for i in 0..10
                puts Dice.weapon_power
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 1'
            for i in 0..10
                puts Dice.shield_power
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 5'
            for i in 0..10
                puts Dice.uses_left
            end

            puts '*********************************'
            puts 'Debe salir un número de 0 a 5'
            for i in 0..10
                puts Dice.intensity(6)
            end

            puts '*********************************'
            for i in 0..10
                puts Dice.discard_element(10)
            end

            # PROBANDO CLASS GAMESTATE
            
            labyrinthv = "Laberinto"
            players = "Isabel"
            monsters = "Monstruo"
            current_player = 5
            winner = true
            log = "log"

            game_state = GameState.new(labyrinthv, players, monsters, current_player,winner, log)
            
            puts '*********************************'
            puts 'ELEMENTOS DE GAMESTATE'
            puts game_state.labyrinthv 
            puts game_state.players 
            puts game_state.monsters 
            puts game_state.current_player
            puts game_state.winner 
            puts game_state.log 

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
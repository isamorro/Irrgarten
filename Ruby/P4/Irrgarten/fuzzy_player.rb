
#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades de los jugadores Fuzzy

=end

require_relative 'dice'
require_relative 'player'

module Irrgarten
    class FuzzyPlayer < Player

        def initialize (other)
            super(other.number, other.intelligence, other.strength)
            copia(other)
            @name = "Fuzzy " + @name
        end

        def move (direction, valid_moves)
            result = super
            Dice.next_step(result, valid_moves, intelligence)
        end

        def attack
            sum_weapons + Dice.intensity(strength)
        end

        def defensive_energy
            sum_shields + Dice.intensity(intelligence)
        end

    end
end
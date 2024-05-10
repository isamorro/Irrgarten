

require_relative 'Dice'
require_relative 'player'

module Irrgarten
    class FuzzyPlayer < Player

        def initialize (other)
            player_copy(other)
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

        def to_s
            "Fuzzy " + super
        end
    end
end
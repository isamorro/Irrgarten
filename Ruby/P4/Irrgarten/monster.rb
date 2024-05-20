#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades del monstruo

=end

require_relative 'dice'
require_relative 'labyrinth_character'

module Irrgarten
    class Monster < LabyrinthCharacter

    private 

        @@INITIAL_HEALTH = 5

    public 

        # Constructor 
        def initialize (name, intelligence, strength)
            super
            set_health(@@INITIAL_HEALTH)
        end

        # Métodos públicos

        def attack
            Dice.intensity(@strength)
        end

        def defend (received_attack)
            is_dead = dead()
            if (!is_dead)
                defensive_energy = Dice.intensity(@intelligence)
                if (defensive_energy < received_attack)
                    got_wounded()
                    is_dead = dead()
                end
            end
            is_dead
        end

        def to_s
            super + "]\n"
        end

    end
end
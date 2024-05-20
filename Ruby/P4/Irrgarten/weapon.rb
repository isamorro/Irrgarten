#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares
    
    Armas que utiliza el jugador en los ataques durante los combates

=end

require_relative 'dice'
require_relative 'combat_element'

module Irrgarten
    class Weapon < CombatElement

        # Constructor

        def initialize (power, uses)
           super
        end

        # Métodos públicos

        def attack 
            produce_effect
        end

        # To string
        def to_s
            "W[" + super
        end
    end

end
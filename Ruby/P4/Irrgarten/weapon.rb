#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Armas que utiliza el jugador en los ataques durante los combates

=end

require_relative 'dice'

module Irrgarten
    class Weapon < CombatElement

        # Constructor

        def initialize (p, u)
           super
        end

        # Métodos públicos

        def attack 
            produce_effect
        end

        # To string
        def to_s
            return "W[" + super
        end
    end

end
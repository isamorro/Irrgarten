#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Representa los escudos que utiliza el jugador para defenderse de un monstruo

=end

require_relative 'dice'
require_relative 'combat_element'

module Irrgarten
    class Shield < CombatElement
 
        # Constructor
        
        def initialize (p,u)
            super
        end

        # Métodos públicos
        def protect
            produce_effect
        end
        # To string

        def to_s
            return "S[" + super
        end

    end
end
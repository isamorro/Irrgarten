#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Define los tipos de elementos para el combate

    
=end

require_relative 'Dice'

module Irrgarten
    class CombatElement

        def initialize(effect, uses)
            @effect = effect
            @uses = uses
        end

        def produce_effect
            resultado = 0
            if @uses > 0  
                @uses = @uses - 1
                resultado = @effect
            end
            resultado
        end

        def discard
            Dice.discard_element(@uses)
        end

        def to_s
         cadena = @effect.to_s 
         cadena += ", " + @uses.to_s 
         cadena += "]"
         cadena
        end

    end
end
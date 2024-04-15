#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Representa los escudos que utiliza el jugador para defenderse de un monstruo

=end

require_relative 'dice'

module Irrgarten
    class Shield
 
        # Constructor
        
        def initialize (p,u)
            # Atributos de instancia privados
            @protection = p 
            @uses = u 
        end

        # Métodos públicos
        
        def protect
            resultado = 0
            if @uses > 0  
                @uses = @uses - 1
                resultado = @protection
            end
            resultado
        end

        # Implementa la decisión de si un escudo es descartado
        def discard
            Dice.discard_element(@uses)
        end

        # To string

        def to_s
            cadena = "S[" + @protection.to_s() + ", " + @uses.to_s() + "]"
        end

    end
end
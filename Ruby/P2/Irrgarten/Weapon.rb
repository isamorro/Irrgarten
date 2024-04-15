#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Armas que utiliza el jugador en los ataques durante los combates

=end

require_relative 'Dice'

module Irrgarten
    class Weapon

        # Constructor

        def initialize (p, u)
            @power = p
            @uses = u 
            @dice = Dice.new    
        end

        # Métodos públicos

        def attack 
            resultado = 0
            if @uses > 0 
                @uses = @uses - 1
                resultado = @power
            end 
            resultado
        end

        # Implementa la decisión de si un arma es descartada
        def discard
            @dice.discardElement(@uses)
        end

        # To string
        def to_s
            cadena = "W[" + @power.to_s() + ", " + @uses.to_s() + "]"
        end
    end

end
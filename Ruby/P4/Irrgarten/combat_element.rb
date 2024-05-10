
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
                resultado = @protection
            end
            resultado
            
        end

        def discard_element
            Dice.discard_element(@uses)
        end

        def to_s
         cadena = @effect + ", " + @uses +"]"
         cadena
        end

    end
end
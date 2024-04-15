#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades del monstruo

=end

require_relative 'dice'

module Irrgarten
    class Monster

    private 

        @@INITIAL_HEALTH = 5
        
        # Métodos privados 
        
        # Decrementa en una unidad la salud el monstruo
        def got_wounded
            @health = @health - 1
        end

    public 

        # Constructor 
        def initialize (name, intelligence, strength)
            @name = name
            @intelligence = intelligence
            @strength = strength
            @health = @@INITIAL_HEALTH
            @row = 0
            @col = 0
        end

        # Métodos públicos

        # Devuelve true si el monstruo está muerto
        def dead
            resultado = false
            if (@health <= 0) 
                resultado = true 
            end
            resultado
        end

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

        def pos (row, col)
            @row = row
            @col = col
        end

        def to_s
            cadena = "[ Nombre: " + @name + "\n"
            cadena  += " Inteligencia: " + @intelligence.to_s + "\n"
            cadena  += " Fuerza: " + @strength.to_s + "\n"
            cadena  += " Salud: " + @health.to_s + "\n"
            cadena  += " Posicion: (" + @row.to_s + ", " + @col.to_s + ") ]\n"
        end

    end
end
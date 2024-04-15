#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades del monstruo

=end

require_relative 'Dice'

module Irrgarten
    class Monster

    private 

        @@INITIAL_HEALTH = 5
        
        # Métodos privados 
        
        # Decrementa en una unidad la salud el monstruo
        def gotWounded
            if @health > 0
                @health = @health - 1
            end
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
            (@health == 0)
        end

        def attack
            dice = Dice.new
            dice.intensity(@strength)
        end

        # P3
        def defend (receivedAttack)
        end

        def setPos (row, col)
            @row = row
            @col = col
        end

        def to_s
            cadena = "[Nombre: " + @name + "\n"
            cadena  += " Inteligencia: " + @intelligence.to_s + "\n"
            cadena  += " Fuerza: " + @strength.to_s + "\n"
            cadena  += " Salud: " + @health.to_s + "\n"
            cadena  += " Posicion: (" + @row.to_s + ", " + @col.to_s + ")]\n"
        end

    end
end
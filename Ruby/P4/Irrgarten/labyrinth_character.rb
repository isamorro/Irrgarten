
#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades de los personajes de juego
    De esta heredan Monster y Player

=end

module Irrgarten
    class LabyrinthCharacter

        def initialize (name, intelligence, strength, health)
            @name = name
            @intelligence = intelligence
            @strength = strength
            @health = health

            @row = -1
            @col = -1
        end

        def copia(other)
            set_pos(other.row, other.col)
        end

        def dead
            (@health <= 0)
        end

        def row
            @row
        end

        def col
            @col
        end

        def intelligence
            @intelligence
        end

        def strength
            @strength
        end

        def health
            @health
        end

        def name 
            @name
        end

        def set_health (health)
            @health = health
        end
        
        def set_pos (row, col)
            @row = row
            @col = col
        end

        def to_s
            cadena = "[ Nombre: " + @name + ","
            cadena  += " Inteligencia: " + @intelligence.to_s + ","
            cadena  += " Fuerza: " + @strength.to_s + ","
            cadena  += " Salud: " + @health.to_s + ","
            cadena  += " Posicion: (" + @row.to_s + ", " + @col.to_s + ") "
            cadena
        end

        def got_wounded
            if (@health > 0)
                @health -= 1
            end
        end

    end
end
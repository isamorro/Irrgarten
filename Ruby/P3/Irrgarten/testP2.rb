#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 
    Función Main de Prueba de la Práctica 2

=end

require_relative 'monster'
require_relative 'player'
require_relative 'labyrinth'

module Irrgarten
    class TestP2
        def self.main 
            
            puts "******************"
            puts "PRUEBA CLASE MONSTER"

                m = Monster.new("Monstruo 1", 2.0, 5.0)

                # Prueba método DEAD

                puts m.to_s

                if m.dead
                    puts "MONSTRUO 1 MUERTO"
                else
                    puts "MONSTRUO 1 VIVO"
                end

                # Prueba método ATTACK

                puts m.attack

                # Prueba método SETPOS
                puts "Cambiamos monstruo a posicion (-1,-1)"
                m.pos(1,1)

                puts m.to_s


            puts "******************"
            puts "PRUEBA CLASE PLAYER"

                p = Player.new('1', 5.0, 5.0)
                puts p.to_s

            puts "******************"
            puts "PRUEBA CLASE LABYRINTH"

                l = Labyrinth.new(5, 6, 1, 0)
                puts l.to_s

                # Añadmos un monstruo
                l.add_monster(-3,3,m)

                puts l.to_s

                l1 = Labyrinth.new(3, 6, 0, 0)
                puts l1.to_s

        end
    end
end

Irrgarten::TestP2.main
#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades de los jugadores

=end

require_relative 'Dice'
require_relative 'Directions'
require_relative 'Weapon'
require_relative 'Shield'

module Irrgarten
    class Player 
    
    private 

        @@MAX_WEAPONS = 2
        @@MAX_SHIELDS = 3
        @@INITIAL_HEALTH = 10
        @@HITS2LOSE = 3

        def initialize (number, intelligence, strength)
            @number = number
            @name = "Player #" + @number.to_s
            @intelligence = intelligence
            @strength = strength
            @health = @@INITIAL_HEALTH
            @row = 0
            @col = 0
            @consecutiveHits = 0

            @weapons = Array.new
            @shields = Array.new

        end

        # Métodos privados

        # P3
        def receiveWeapon(w)
        end

        # P3
        def receiveShields(s)
        end

        def newWeapon
            dice = Dice.new
            power = dice.weaponPower
            uses = dice.usesLeft
            w = Wepon.new(powwer, uses)
        end

        def newShield            
            dice = Dice.new
            protection = dice.shieldPower
            uses = dice.usesLeft
            s = Shield.new(protection, uses)
        end

        def sumWeapons
            suma = 0
            for i in (0..@weapons.length)
                suma = suma + @weapons[i].attack
            end
        end

        def sumShields
            suma = 0
            for i in (0..@shields.length)
                suma = suma + @shields[i].protect
            end
        end

        def defensiveEnergy
            sumShields() + intelligence
        end

        # P3
        def manageHit(receiveAttack)
        end

        def resetHits
            consecutiveHits = 0
        end

        def gotWounded
            if health > 0
                health = health - 1
            end
        end

        def incConsecutiveHits
            consecutiveHits = consecutiveHits + 1
        end

    public 

        # Métodos públicos

        def resurrect
            @weapons.clear 
            @shields.clear 
            @health = @@INITIAL_HEALTH
            resetHits()
        end

        def getRow
            @row
        end

        def getCol
            @col
        end

        def getNumber
            @number
        end

        def setPos (row, col)
            @row = row
            @col = col
        end

        def dead
            (@health == 0)
        end

        # P3
        def move (direction, validMoves)
        end

        def attack
            sumWeapons() + strength
        end

        def defend
            manageHit(receiveAttack)
        end

        # P3
        def receiveReward
        end

        # To string
        def to_s
            cadena = ""
            cadena  += "[Nombre: " + @name + "\n"
            cadena  += " Numero: " + @number + "\n"
            cadena  += " Inteligencia: " + @intelligence.to_s + "\n"
            cadena  += " Fuerza: " + @strength.to_s + "\n"
            cadena  += " Salud: " + @health.to_s + "\n"
            cadena  += " Posicion: (" << @row.to_s + ", " + @col.to_s + ") " + "\n"
            cadena  += " Impactos consecutivos: " + @consecutiveHits.to_s + "]" 
        end

    
    end
end

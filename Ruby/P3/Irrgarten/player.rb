#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades de los jugadores

=end

require_relative 'dice'
require_relative 'directions'
require_relative 'weapon'
require_relative 'shield'

module Irrgarten
    class Player 
    
    private 

        @@MAX_WEAPONS = 2
        @@MAX_SHIELDS = 3
        @@INITIAL_HEALTH = 10
        @@HITS_2_LOSE = 3

        def initialize (number, intelligence, strength)
            @number = number
            @name = "Player #" + @number
            @intelligence = intelligence
            @strength = strength
            @health = @@INITIAL_HEALTH
            @row = 0
            @col = 0
            @consecutive_hits = 0

            @weapons = Array.new
            @shields = Array.new

        end

        # Métodos privados

        def receive_weapon(w)


            @weapons.each do |wi|
                discard = wi.discard()
                if (discard)
                    @weapons.delete(wi)
                end
            end

            size = @weapons.size

            if (size < @@MAX_WEAPONS)
                @weapons << w  
            end

        end

        def receive_shields(s)
        
            @shields.each do |si|
                discard = si.discard()
                if (discard)
                    @shields.delete(si)
                end
            end

            size = @shields.size

            if (size < @@MAX_SHIELDS)
                @shields << s 
            end
        
        end

        def new_weapon
            power = Dice.weapon_power
            uses = Dice.uses_left
            w = Wepon.new(power, uses)
        end

        def new_shield            
            protection = Dice.shield_power
            uses = Dice.uses_Left
            s = Shield.new(protection, uses)
        end

        def sum_weapons
            suma = 0
            for i in (0..@weapons.length)
                suma = suma + @weapons[i].attack
            end
        end

        def sum_shields
            suma = 0
            for i in (0..@shields.length)
                suma = suma + @shields[i].protect
            end
        end

        def defensive_energy
            sum_shields() + intelligence
        end

        def manage_Hit(receive_attack)

            defense = defensive_energy()

            if (defense < receiv_aAttack)
                got_wounded()
                inc_consecutive_hits()
            else
                reset_hits()
            end

            if (consecutive_hits == @@HITS_2_LOSE || dead())
                reset_hits()
                lose = true
            else
                lose = false 
            end

            return lose

        end

        def reset_hits
            consecutive_hits = 0
        end

        def got_wounded
            if health > 0
                health = health - 1
            end
        end

        def inc_consecutive_hits
            consecutive_hits += 1
        end

    public 

        # Métodos públicos

        def resurrect
            @weapons.clear 
            @shields.clear 
            @health = @@INITIAL_HEALTH
            reset_hits()
        end

        def row
            @row
        end

        def col
            @col
        end

        def number
            @number
        end

        def pos (row, col)
            @row = row
            @col = col
        end

        def dead
            (@health <= 0)
        end

        def move (direction, valid_moves)

            size = valid_moves.length
            contained = valid_moves.include?(direction)

            if (size > 0 && !contained)
                first_element = valid_moves[0]
                resultado = first_element
            else
                resultado = direction
            end

            return resultado

        end

        def attack
            sum_weapons() + strength
        end

        def defend
            manage_hit(receive_attack)
        end

        def receive_reward

            w_reward = Dice.weapons_reward()
            s_reward = Dice.shields_reward()

            for i in (0..w_reward)
                w_new = new_weapon()
                receive_weapon(w_new)
            end

            for i in (0..s_reward)
                s_new = new_shield()
                receive_rsield(s_new)
            end

            extra_health = Dice.health_reward()
            health += extra_health

        end

        # To string
        def to_s
            cadena = ""
            cadena  += "[ Nombre: " + @name + "\n"
            cadena  += " Numero: " + @number + "\n"
            cadena  += " Inteligencia: " + @intelligence.to_s + "\n"
            cadena  += " Fuerza: " + @strength.to_s + "\n"
            cadena  += " Salud: " + @health.to_s + "\n"
            cadena  += " Posicion: (" << @row.to_s + ", " + @col.to_s + ") " + "\n"
            cadena  += " Impactos consecutivos: " + @consecutive_hits.to_s + "]\n" 
        end

    
    end
end

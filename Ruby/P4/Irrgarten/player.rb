#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funcionalidades de los jugadores

=end

require_relative 'dice'
require_relative 'directions'
require_relative 'weapon'
require_relative 'shield'
require_relative 'labyrinth_character'

module Irrgarten
    class Player < LabyrinthCharacter
    
    private 

        @@MAX_WEAPONS = 2
        @@MAX_SHIELDS = 3
        @@INITIAL_HEALTH = 10
        @@HITS_2_LOSE = 3

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

        def receive_shield(s)
        
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
            w = Weapon.new(power, uses)
            w
        end

        def new_shield            
            protection = Dice.shield_power
            uses = Dice.uses_left
            s = Shield.new(protection, uses)
            s
        end

        def sum_weapons
            suma = 0
                @weapons.each do |w|
                    suma += w.attack
                end
            suma
        end

        def sum_shields
            suma = 0
                @shields.each do |s|
                    suma += s.protect
                end
            suma
        end

        def defensive_energy
            sum_shields() + @intelligence
        end

        def manage_hit(receive_attack)

            defense = defensive_energy()

            if (defense < receive_attack)
                got_wounded()
                inc_consecutive_hits()
            else
                reset_hits()
            end

            if (@consecutive_hits == @@HITS_2_LOSE || dead())
                reset_hits()
                lose = true
            else
                lose = false 
            end

            lose
        end

        def reset_hits
            @consecutive_hits = 0
        end

        def inc_consecutive_hits
            @consecutive_hits += 1
        end

    public 
    
        # Métodos públicos

        def initialize (number, intelligence, strength)
            super("Player #" + number, intelligence, strength)
            @number = number
            @consecutive_hits = 0
            @weapons = Array.new
            @shields = Array.new
            set_health(@@INITIAL_HEALTH)
        end

        def copia(other)
            super
        end

        def resurrect
            @weapons.clear 
            @shields.clear 
            @health = @@INITIAL_HEALTH
            reset_hits()
        end

        def number
            @number
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
            sum_weapons + strength
        end

        def defend (receive_attack)
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
                receive_shield(s_new)
            end

            extra_health = Dice.health_reward()
            @health += extra_health

        end

        # To string
        def to_s
            cadena = super
            cadena  += " Numero: " + @number + ","
            cadena  += " Impactos consecutivos: " + @consecutive_hits.to_s + "\n"
            cadena  += " Armas: "
         
            if (!@weapons.empty?)
                @weapons.each do |wi|
                    cadena += wi.to_s + " "
                end
            else
                cadena += "No dispone de armas"
            end
            cadena += "\n"
            cadena += " Shields: "
            if (!@shields.empty?)
                @shields.each do |si|
                    cadena += si.to_s + " "
                end
            else 
                cadena += "No dispone de escudos"
            end
            cadena += "]\n"
            
            cadena
        
        end

    
    end
end

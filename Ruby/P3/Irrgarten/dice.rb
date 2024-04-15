#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Toma todas las decisiones que dependen del azar en el juego

=end

module Irrgarten
    class Dice

        @@MAX_USES = 5 
        @@MAX_INTELLIGENCE = 10.0
        @@MAX_STRENGTH = 10.0 
        @@RESURRECT_PROB = 0.3 
        @@WEAPONS_REWARD = 2 
        @@SHIELDS_REWARD = 3 
        @@HEALTH_REWARD = 5 
        @@MAX_ATTACK = 3 
        @@MAX_SHIELD = 2

        @@generator = Random.new
        
        # Métodos públicos

        # Devuelve número de fila o columna aleatoria, de 0 a max-1
        def self.random_pos(max) 
            @@generator.rand(max)
        end

        # Devuelve índice del jugador que comenzará la partida, de 0 a nplayers - 1
        def self.who_starts(n_players)
            @@generator.rand(n_players)
        end

        # Devuelve un valor aleatorio de inteligencia del intervalo [0, MAX_INTELLIGENCE[
        def self.random_intelligence
            @@generator.rand(@@MAX_INTELLIGENCE)
        end

        # Devuelve un valor aleatorio de fuerza del intervalo [0, MAX_STRENGTH[
        def self.random_strength
            @@generator.rand(@@MAX_STRENGTH)
        end

        # Indica si un jugador muerto debe ser resucitado o no
        def self.resurrect_player
            @@generator.rand < @@RESURRECT_PROB
        end

        # Indica cantidad de armas que recibe el jugador por ganar el combate, [0, WEAPONS_REWARD]
        def self.weapons_reward 
            @@generator.rand(@@WEAPONS_REWARD+1)
        end

        # Indica la cantidad de escudos que recibirá el jugador por ganar el combate, [0, SHIELDS_REWARD]
        def self.shields_reward 
            @@generator.rand(@@SHIELDS_REWARD+1)
        end

        # Indica la cantidad de escudos que recibirá el jugador por ganar el combate, [0,HEALTH_REWARD]
        def self.health_reward 
            @@generator.rand(@@HEALTH_REWARD+1)
        end

        # Devuelve un valor aleatorio de [0, MAX_ATTACK[
        def self.weapon_power
            @@generator.rand(@@MAX_ATTACK)
        end

        # Devuelve valor aleatorio de [0, MAX_SHIELD[
        def self.shield_power
            @@generator.rand(@@MAX_SHIELD)
        end

        # Devuelve el número de usos que se asignará a un arma o escudo
        def self.uses_left 
            @@generator.rand(@@MAX_USES+1)
        end

        # Devuelve la cantidad de competencia aplicada
        def self.intensity (competence)
            @@generator.rand(competence)
        end

        # Devuelve true con una probabilidad inversamente proporcional a lo cercano que 
        # esté el parámetro del número máximo de usos que puede tener un arma o escudo
        def self.discard_element (uses_left)
            @@generator.rand(@@MAX_USES) >= uses_left
        end
        
    end
end
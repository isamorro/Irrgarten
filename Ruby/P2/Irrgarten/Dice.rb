#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Toma todas las decisiones que dependen del azar en el juego

=end

module Irrgarten
    class Dice

        # Constructor
        def initialize 
        
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
        
        end

        # Métodos públicos

        # Devuelve número de fila o columna aleatoria, de 0 a max-1
        def randomPos (max) 
            @@generator.rand(max)
        end

        # Devuelve índice del jugador que comenzará la partida, de 0 a nplayers - 1
        def whoStarts (nplayers)
            @@generator.rand(nplayers)
        end

        # Devuelve un valor aleatorio de inteligencia del intervalo [0, MAX_INTELLIGENCE[
        def randomIntelligence
            @@generator.rand(@@MAX_INTELLIGENCE)
        end

        # Devuelve un valor aleatorio de fuerza del intervalo [0, MAX_STRENGTH[
        def randomStrength
            @@generator.rand(@@MAX_STRENGTH)
        end

        # Indica si un jugador muerto debe ser resucitado o no
        def resurrectPlayer
            @@generator.rand < @@RESURRECT_PROB
        end

        # Indica cantidad de armas que recibe el jugador por ganar el combate, [0, WEAPONS_REWARD]
        def weaponsReward 
            @@generator.rand(@@WEAPONS_REWARD+1)
        end

        # Indica la cantidad de escudos que recibirá el jugador por ganar el combate, [0, SHIELDS_REWARD]
        def shieldsReward 
            @@generator.rand(@@SHIELDS_REWARD+1)
        end

        # Indica la cantidad de escudos que recibirá el jugador por ganar el combate, [0,HEALTH_REWARD]
        def healthReward 
            @@generator.rand(@@HEALTH_REWARD+1)
        end

        # Devuelve un valor aleatorio de [0, MAX_ATTACK[
        def weaponPower
            @@generator.rand(@@MAX_ATTACK)
        end

        # Devuelve valor aleatorio de [0, MAX_SHIELD[
        def shieldPower
            @@generator.rand(@@MAX_SHIELD)
        end

        # Devuelve el número de usos que se asignará a un arma o escudo
        def usesLeft 
            @@generator.rand(@@MAX_USES+1)
        end

        # Devuelve la cantidad de competencia aplicada
        def intensity (competence)
            @@generator.rand(competence)
        end

        # Devuelve true con una probabilidad inversamente proporcional a lo cercano que 
        # esté el parámetro del número máximo de usos que puede tener un arma o escudo
        def discardElement (usesLeft)
            @@generator.rand(@@MAX_USES) >= usesLeft
        end
        
    end
end
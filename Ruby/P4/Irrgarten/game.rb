#encoding:utf-8

=begin

    Autor: Isabel Morro Tabares 

    Funciones del Juego

=end

require_relative 'dice'
require_relative 'game_character'
require_relative 'game_state'
require_relative 'monster'
require_relative 'labyrinth'
require_relative 'player'
require_relative 'orientation'

module Irrgarten
    class Game

    private

        # Atributos de clase privados
        @@MAX_ROUNDS = 10

        # Métodos privados

        # Configrua el laberinto. Es totalmente elección propia,
        def configure_labyrinth

            orientacion = Orientation::HORIZONTAL
            @labyrinth.add_block(orientacion, 2, 3, 1)
    
            m1 = Monster.new("Monstruo #" + '1', 10.0, 10.0)
            m2 = Monster.new("Monstruo #" + '2',  10.0, 10.0)
            m3 = Monster.new("Monstruo #" + '3', Dice.random_intelligence,
                             Dice.random_strength)

            @labyrinth.add_monster(5, 5, m1)
            @labyrinth.add_monster(3, 7, m2)
            @labyrinth.add_monster(1, 4, m3)

            @monsters << m1
            @monsters << m2
            @monsters << m3

        end

        # Actualiza los atributos del jugador de turno
        def next_player
            @current_player_index = (@current_player_index + 1) % @players.size
            @current_player = @players[@current_player_index]
        end


        def actual_direction (preferred_direction)
            current_row = @current_player.row
            current_col = @current_player.col
            valid_moves = Array.new
            valid_moves = @labyrinth.valid_moves(current_row, current_col)
            output = @current_player.move(preferred_direction, valid_moves)
            return output
        end


        def combat (monster)

            rounds = 0
            winner = GameCharacter::PLAYER
            player_attack = @current_player.attack()
            lose = monster.defend(player_attack)

            while (!lose && rounds < @@MAX_ROUNDS) 
            
                winner = GameCharacter::MONSTER 
                rounds += 1
                monster_attack = monster.attack()
                lose = @current_player.defend(monster_attack)

                if (!lose)
                    player_attack = @current_player.attack()
                    winner = GameCharacter::PLAYER
                    lose = monster.defend(player_attack)
                end

            end

            log_rounds(rounds, @@MAX_ROUNDS)
            winner
        end

   
        def manage_reward (winner)
            
            if (winner == GameCharacter::PLAYER)
                @current_player.receive_reward()
                log_player_won()
            else
                log_monster_won()
            end

        end

        def manage_resurrection

            resurrect = Dice.resurrect_player()

            if (resurrect)
                @current_player.resurrect()
                fp = FuzzyPlayer.new(@current_player)
                @current_player = fp
                @players[@current_player_index] = fp
                @labyrinth.resurrection_fuzzy_player(@current_player.row, @current_player.col, fp)
                log_resurrected()
            else 
                log_player_skip_turn()
            end

        end

        def log_player_won
            @log << "EL JUGADOR HA GANADO EL COMBATE \n"
        end

        def log_monster_won
            @log << "EL MONSTRUO HA GANADO EL COMBATE \n"
        end

        def log_resurrected
            @log << "EL JUGADOR HA RESUCITADO \n"
        end

        def log_player_skip_turn
            @log << "EL JUGADOR ESTA MUERTO, HA PERDIDO EL TURNO \n"
        end

        def log_player_no_orders
            @log << "EL JUGADOR NO HA SEGUIDO LAS INTRUCCIONES \n"
        end

        def log_no_monster
            @log << "EL JUGADOR SE HA MOVIDO A UNA CELDA VACIA O NO HA PODIDO MOVERSE \n"
        end

        def log_rounds (rounds, max)
            @log << "SE HA ALCANZADO EL MAXIMO DE RONDAS \n"
        end

        # Métodos publicos 
    
    public

        def initialize (n_players)
            
            @log = ""

            # Creación de jugadores
            @players = Array.new
            for i in (0..n_players-1)
                @players << Player.new(i.to_s, 0, 5)
            end

            # Determinar quién empieza
            @current_player_index = Dice.who_starts(n_players)
            @current_player = @players[@current_player_index]

            @monsters = Array.new
            @labyrinth = Labyrinth.new(10, 10, 0, 1)

            configure_labyrinth()
            @labyrinth.spread_players(@players)
        end

        def finished
            @labyrinth.have_a_winner
        end

        def next_step (preferred_direction)
            
            @log = ""
            dead = @current_player.dead()

            if (!dead)

                direction = actual_direction(preferred_direction)

                if (direction != preferred_direction)
                    log_player_no_orders()
                end

                monster = @labyrinth.put_player(direction, @current_player)

                if (monster == nil)
                    log_no_monster()
                else 
                    winner = combat(monster)
                    manage_reward(winner)
                end
            
            else
                manage_resurrection()
            end

            end_game = finished()

            if (!end_game)
                next_player()
            end

            end_game

        end

        def game_state

            jugadores = ""
            for i in (0..@players.length)
                jugadores << @players[i].to_s
            end

            monstruos = ""
            for i in (0..@monsters.length)
                monstruos << @monsters[i].to_s
            end

            return GameState.new(@labyrinth.to_s, jugadores, monstruos,
                                @current_player_index, finished(), @log)

        end

    end
end

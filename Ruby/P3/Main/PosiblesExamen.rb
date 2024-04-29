
require_relative '../Irrgarten/labyrinth'
require_relative '../Irrgarten/dice'
require_relative '../Irrgarten/monster'
require_relative '../Irrgarten/player'
require_relative '../Irrgarten/weapon'
require_relative '../Irrgarten/shield'


module Examen
    class PosiblesExamen
        def self.main

            
            ################## ENUMERADOS ####################

            # DIRECTIONS

            puts Irrgarten::Directions::LEFT
            puts Irrgarten::Directions::RIGHT
            puts Irrgarten::Directions::UP
            puts Irrgarten::Directions::DOWN

            # ORIENTATION

            puts Irrgarten::Orientation::VERTICAL
            puts Irrgarten::Orientation::HORIZONTAL

            # GAMECHARACTER

            puts Irrgarten::GAMECHARACTER::PLAYER
            puts Irrgarten::GAMECHARACTER::MONSTER

            ################## ARMAS ####################
            # Crear arma
            w1 = Irrgarten::Weapon.new(Irrgarten::Dice.weapon_power, Irrgarten::Dice.uses_left)

            # atacar 
            devuelve_power = w1.attack

            # descartar arma
            bool_descartar_arma = w1.discard

            # to string
            puts w1.to_s

            ################## ESCUDOS ####################
           
            # Crear escudo
            s1 = Irrgarten::Shield.new(Irrgarten::Dice.shield_power, Irrgarten::Dice.uses_left)

            # Proteger
            devuelve_proteccion = s1.protect

            # Descartar
            bool_descartar_escudo = s1.discard

            # to string
            s1.to_s

            ################## MONSTRUO ####################

            # Crear monstruo
            m1 = Irrgarten::Monster.new("Nombre M1", Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)


            # Comprobar si muerto
            monstruo_muerto = m1.dead

            # atacar
            devuelve_intensidad_de_ataque = m1.attack

            # defender
            received_attack 
            devuelve_bool_monstruo_muerto = m1.defend (received_attack)

            # cambiar pos
            m1.pos(row, col)

            # to string
            puts m1.to_s

            ################## LABERINTO ####################

            # Crear Laberinto 

            n_cols = 5
            n_rows = 5
            exit_col = 0
            exit_row = 2
            l1 = Irrgarten::Labyrinth.new(n_rows, n_cols, exit_row, exit_col)
            puts l1.to_s

            # Poner jugadores en laberinto
            jugadores = []
            l1.spread_players(jugadores)
            puts l1.to_s

            # Tener jugador
            bool_hay_jugador = l1.have_a_winner

            # To String
            puts l1.to_s
            
            # Poner monstruo en laberinto
            row = 2
            col = 3
            l1.add_monster(row, col, m1)
            puts l1.to_s

            # Poner jugador
            hay_monster = l1.put_player(direccion, jugador)

            # Poner bloque 
            l1.add_block(orientation, fil_ini, col_ini, longitud)

            # array de movimientos válidos
            movimientos_validos = [] # array de direcciones
            movimientos_validos = l1.valid_moves (fila, col)

            # to_s
            puts l1.to_s

            ################## JUGADOR ####################

            # Crear jugadores

            jugadores = Array.new

            j1 = Irrgarten::Player.new('1', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)
            j2 = Irrgarten::Player.new('2', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)
            j3 = Irrgarten::Player.new('3', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)
            j4 = Irrgarten::Player.new('4', Irrgarten::Dice.random_intelligence, Irrgarten::Dice.random_strength)

            jugadores << j1
            jugadores << j2
            jugadores << j3
            jugadores << j4

            # Resucitar
            jugador_1.resurrect

            # Obtener Posiciones
            row = jugador.row
            col = jugador.col

            # Obtener numero
            num = jugador.number

            # Cambiar Pos
            jugador.pos(row, col)

            # Comprobar si muerto
            jugador.dead

            # Mover jugador 
            velidMoves = [] # Array de direcciones
            direction # A donde quiero ir
            direccion = jugador.move(direction, validMoves)

            # Atacar
            devuelve_float = jugador.attack

            # Defender
            devuelve_booleano = jugador.defend(ataque_recibido)

            # Recibir botin
            jugador.receive_reward

            # to string
            jugador.to_s

            ################## JUEGO ####################

            vector_jugadores = []
            game = Irrgarten::Game.new(vector_jugadores)

            # Comprobacion si juego terminado
            terminado = game.finished

            # Siguiente paso
            se_ha_terminado_el_juego = juego.next_step(direccion_preferida)

            # Obtener estado del juego
            game_state = game.game_state

            ################## ESTADO DEL JUEGO ####################

            game_state = Irrgarten::GameState.new(laberinto.to_s, players.to_s, monsters.to_s, indice_current_player, bool_hay_ganador, log)


        end
    end
end

Examen::PosiblesExamen.main

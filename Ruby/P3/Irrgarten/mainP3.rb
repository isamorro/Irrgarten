
require_relative 'controller'
require_relative 'textUI'
require_relative 'game'
require_relative 'dice'

module Irrgarten
    class MainP3
        def self.main ()

            numero_jugadores = 2

            vista = UI::TextUI.new
            game = Game.new(numero_jugadores)
            controller = Control::Controller.new(game, vista)
            controller.play()

        end
    end
end

Irrgarten::MainP3.main
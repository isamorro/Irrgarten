
require_relative '../Control/controller'
require_relative '../UI/textUI'
require_relative '../Irrgarten/game'
require_relative '../Irrgarten/dice'

module MainP3
    class Main
        def self.main ()

            numero_jugadores = 2

            vista = UI::TextUI.new
            game = Irrgarten::Game.new(numero_jugadores)
            controller = Control::Controller.new(game, vista)
            controller.play()

        end
    end
end

MainP3::Main.main
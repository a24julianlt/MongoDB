package com.view;

import com.controller.PartidaController;
import com.model.Partida;

public class App {
    public static void main(String[] args) {
        PartidaController controller = new PartidaController();

        Partida p1 = controller.crearPartida("Mario", "Space Invaders", 1200, 15, 3);
        Partida p2 = controller.crearPartida("Luigi", "Pac-Man", 800, 10, 2);
        Partida p3 = controller.crearPartida("Mario", "Donkey Kong", 1500, 18, 4);
        Partida p4 = controller.crearPartida("Peach", "Tetris", 600, 12, 1);
        Partida p5 = controller.crearPartida("Mario", "Super Mario Bros", 2000, 25, 5);
        Partida p6 = controller.crearPartida("Luigi", "Galaga", 950, 14, 3);

    }
}

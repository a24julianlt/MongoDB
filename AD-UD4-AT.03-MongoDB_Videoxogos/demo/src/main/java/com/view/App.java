package com.view;

import java.util.ArrayList;
import java.util.List;

import com.controller.PartidaController;
import com.model.Partida;

public class App {
    public static void main(String[] args) {
        PartidaController controller = new PartidaController();
        List<Partida> partidas = new ArrayList<>();

        Partida p1 = controller.crearPartida("Mario", "Space Invaders", 1200, 15, 3);
        partidas.add(p1);
        Partida p2 = controller.crearPartida("Luigi", "Pac-Man", 800, 10, 2);
        partidas.add(p2);
        Partida p3 = controller.crearPartida("Mario", "Donkey Kong", 1500, 18, 4);
        partidas.add(p3);
        Partida p4 = controller.crearPartida("Peach", "Tetris", 600, 12, 1);
        partidas.add(p4);
        Partida p5 = controller.crearPartida("Mario", "Super Mario Bros", 2000, 25, 5);
        partidas.add(p5);
        Partida p6 = controller.crearPartida("Luigi", "Galaga", 950, 14, 3);
        partidas.add(p6);

        // guardar partidas
        partidas.forEach(p -> controller.guardarPartida(p));

        


        controller.close();
    }
}

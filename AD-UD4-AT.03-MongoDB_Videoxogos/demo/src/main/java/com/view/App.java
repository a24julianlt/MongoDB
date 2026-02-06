package com.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.controller.PartidaController;
import com.model.Partida;

public class App {
        public static void main(String[] args) {
                System.setProperty("org.slf4j.simpleLogger.log.org.mongodb.driver", "error");

                PartidaController controller = new PartidaController();
                List<Partida> partidas = new ArrayList<>();

                Partida p1 = controller.crearPartida("Mario", "Space Invaders", 1200, 15, 3);
                partidas.add(p1);
                Partida p2 = controller.crearPartida("Luigi", "Pac-Man", 800, 10, 2);
                partidas.add(p2);
                Partida p3 = controller.crearPartida("Mario", "Space Invaders", 1600, 20, 4);
                partidas.add(p3);
                Partida p4 = controller.crearPartida("Peach", "Tetris", 600, 12, 1);
                partidas.add(p4);
                Partida p5 = controller.crearPartida("Mario", "Pac-Man", 1400, 18, 3);
                partidas.add(p5);
                Partida p6 = controller.crearPartida("Luigi", "Space Invaders", 950, 14, 2);
                partidas.add(p6);

                // guardar partidas
                partidas.forEach(p -> controller.guardarPartida(p));

                List<Bson> filtros = Arrays.asList();
                List<Document> resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 1. Puntuación total por xogador
                filtros = Arrays.asList(
                                new Document("$group",
                                                new Document("_id", "$xogador")
                                                                .append("PuntuacionTotal",
                                                                                new Document("$sum", "$puntuacion"))));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 2. Mellor partida de cada xogador
                filtros = Arrays.asList(
                                new Document("$group",
                                                new Document("_id", "$xogador")
                                                                .append("PuntuacionMaxima",
                                                                                new Document("$max", "$puntuacion"))));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 3. Partida máis curta por xogo
                filtros = Arrays.asList(
                                new Document("$group",
                                                new Document("_id", "$xogo")
                                                                .append("DuracionMinima",
                                                                                new Document("$min", "$duracion"))));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 4. Ranking por xogadores
                filtros = Arrays.asList(
                                new Document("$group",
                                                new Document("_id", "$xogador")
                                                                .append("PuntuacionTotal",
                                                                                new Document("$sum", "$puntuacion"))),
                                new Document("$sort", new Document("PuntuacionTotal", -1)));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 5. Listaxe simplificada de partidas
                filtros = Arrays.asList(new Document("$project", new Document("_id", 0)
                                .append("xogador", 1)
                                .append("xogo", 1)
                                .append("puntuacion", 1)));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // 6. Xogos máis puntuables
                filtros = Arrays.asList(new Document("$group", new Document("_id", "$xogo")
                                .append("PuntaucionMedia", new Document("$avg", "$puntuacion"))),
                                new Document("$sort", new Document("PuntaucionMedia", -1)));

                resultados = controller.consulta(filtros);
                resultados.forEach(p -> System.out.println(p));

                System.out.println();

                // Limpiamos la BD para volver a ejecutar

                partidas.forEach(p -> System.out.println(controller.borrar(p)));

                controller.close();
        }
}

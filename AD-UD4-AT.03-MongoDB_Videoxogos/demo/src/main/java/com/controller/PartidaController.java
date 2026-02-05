package com.controller;

import org.bson.*;

import com.model.Partida;
import com.mongodb.client.MongoCollection;

public class PartidaController {
    private final MongoProvider mongo = new MongoProvider();

    public Partida crearPartida(String xogador, String xogo, int puntuacion, float duracion, int nivel) {
        return new Partida(xogador, xogo, puntuacion, duracion, nivel);
    }

    public void guardarPartida(Partida par) {
        try {
            MongoCollection<Document> collection = mongo.partida();

            Document docPartida = new Document("xogador", par.getXogador())
                    .append("xogo", par.getXogo())
                    .append("puntuacion", par.getPuntuacion())
                    .append("duracion", par.getDuracion())
                    .append("nivel", par.getNivel());

            collection.insertOne(docPartida);
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR UNA PARTIDA: " + e.getMessage());
        }
    }

    public void consulta() {

    }

    public void close() {
        mongo.close();
    }
}

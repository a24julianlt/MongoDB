package com.controller;

import org.bson.Document;

import com.model.Partida;
import com.mongodb.client.MongoCollection;

public class PartidaController {
    private final MongoCollection<Document> collection = new MongoProvider().partida();

    public Partida crearPartida(String xogador, String xogo, int puntuacion, float duracion, int nivel) {
        return new Partida(xogador, xogo, puntuacion, duracion, nivel);
    }
}

package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.*;
import org.bson.conversions.Bson;

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

    public List<Document> consulta(List<Bson> filtros) {
        List<Document> listDoc = new ArrayList<>();

        try {
            MongoCollection<Document> collection = mongo.partida();

            collection.aggregate(filtros).into(listDoc);

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR LAS PARTIDAS: " + e.getMessage());
        }

        return listDoc;
    }

    public Long borrar(Partida p) {
        Long count = 0L;

        try {
            MongoCollection<Document> collection = mongo.partida();

            Document docPartida = new Document("xogador", p.getXogador())
                    .append("xogo", p.getXogo())
                    .append("puntuacion", p.getPuntuacion())
                    .append("duracion", p.getDuracion())
                    .append("nivel", p.getNivel());

            count = collection.deleteMany(docPartida).getDeletedCount();

        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR UNA PARTIDA: " + e.getMessage());
        }

        return count;
    }

    public void close() {
        mongo.close();
    }
}

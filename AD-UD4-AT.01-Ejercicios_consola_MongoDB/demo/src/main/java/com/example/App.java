package com.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import java.util.Scanner;
import org.bson.Document;

public class App {
    public static void main(String[] args) {
        try (MongoProvider mongo = new MongoProvider();
                Scanner sc = new Scanner(System.in)) {
            MongoCollection<Document> collection = mongo.alumnado();
            /*
             * Document alumno = new Document("nombre", "a1")
             * .append("ciclo", "DAM2")
             * .append("edad", 19);
             * 
             * collection.insertOne(alumno);
             *
             *
             * Document alumno = new Document("nombre", "a2")
             * .append("ciclo", "DAM2")
             * .append("edad", 23);
             * 
             * collection.insertOne(alumno);
             */

            System.out.println();
            System.out.println();

            collection.find().forEach(d -> System.out.println(d.toJson()));

            Long count = collection.deleteOne(Filters.eq("nombre", "a2")).getDeletedCount();

            System.out.println();

            System.out.println(count);

            System.out.println();
            System.out.println();

            collection.find().forEach(d -> System.out.println(d.toJson()));
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}

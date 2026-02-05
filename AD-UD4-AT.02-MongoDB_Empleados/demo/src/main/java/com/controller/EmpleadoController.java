package com.controller;

import com.model.Empleado;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import java.util.ArrayList;
import java.util.List;

import org.bson.*;
import org.bson.conversions.Bson;

public class EmpleadoController {

    public Empleado crearEmpleado(int Emp_no, String nombre, int dep, int salario, String fechaalta, String oficio,
            Integer comision) {
        return new Empleado(Emp_no, nombre, dep, salario, fechaalta, oficio, comision);
    }

    public void guardarEmpleado(Empleado emp) {
        try (MongoProvider mongo = new MongoProvider()) {
            MongoCollection<Document> collection = mongo.empleado();

            Document docEmp = new Document("Emp_no", emp.getEmp_no())
                    .append("nombre", emp.getNombre())
                    .append("dep", emp.getDep())
                    .append("salario", emp.getSalario())
                    .append("fechaalta", emp.getFechaalta());

            if (emp.getOficio() != null) {
                docEmp.append("oficio", emp.getOficio());
            }

            if (emp.getComision() != null) {
                docEmp.append("comision", emp.getComision());
            }

            collection.insertOne(docEmp);
        } catch (Exception e) {
            System.out.println("ERROR AL GUARDAR UN EMPLEADO: " + e.getMessage());
        }
    }

    public List<Document> listarEmpleados(List<Bson> listaFiltro) {
        List<Document> listDoc = new ArrayList<>();

        try (MongoProvider mongo = new MongoProvider()) {
            MongoCollection<Document> collection = mongo.empleado();

            ArrayList<Document> resultados = collection.aggregate(listaFiltro).into(new ArrayList<>());
            resultados.forEach(d -> listDoc.add(d));

        } catch (Exception e) {
            System.out.println("ERROR AL LISTAR TODOS LOS EMPLEADOS CON AGGREGATE: " + e.getMessage());
        }

        return listDoc;
    }

    public Document buscarEmpleado(Empleado emp) {
        List<Document> listDoc = new ArrayList<>();

        try (MongoProvider mongo = new MongoProvider()) {
            MongoCollection<Document> collection = mongo.empleado();

            collection.find(Filters.eq("Emp_no", emp.getEmp_no())).forEach(d -> listDoc.add(d));

        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR UN EMPLEADO: " + e.getMessage());
        }

        return listDoc.get(0);
    }

    public Long borrarEmpleado(Empleado emp) {
        Long count = 0L;

        try (MongoProvider mongo = new MongoProvider()) {
            MongoCollection<Document> collection = mongo.empleado();

            count = collection.deleteOne(Filters.eq("Emp_no", emp.getEmp_no())).getDeletedCount();

        } catch (Exception e) {
            System.out.println("ERROR AL BORRAR EMPLEADO: " + e.getMessage());
        }

        return count;
    }

    public Long modificarEmpleado(Bson modificador, Bson filtro) {
        Long count = 0L;

        try (MongoProvider mongo = new MongoProvider()) {
            MongoCollection<Document> collection = mongo.empleado();

            count = collection.updateMany(filtro, modificador).getModifiedCount();

        } catch (Exception e) {
            System.out.println("ERROR AL MODIFICAR UN EMPLEADO: " + e.getMessage());
        }

        return count;
    }
}

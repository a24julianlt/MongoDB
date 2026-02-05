package com.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.controller.EmpleadoController;
import com.model.Empleado;
import com.mongodb.client.model.Filters;

public class App {
        public static void main(String[] args) {

                System.setProperty("org.slf4j.simpleLogger.log.org.mongodb.driver", "error");

                EmpleadoController empContr = new EmpleadoController();

                List<Empleado> empleados = new ArrayList<>();

                Empleado emp1 = empContr.crearEmpleado(1, "Juan", 10, 1000, "10/10/1999",
                                null,
                                null);
                empleados.add(emp1);
                Empleado emp2 = empContr.crearEmpleado(2, "Alicia", 10, 1400, "07/08/2000",
                                "Profesora", null);
                empleados.add(emp2);
                Empleado emp3 = empContr.crearEmpleado(3, "María Jesús", 20, 1500,
                                "05/01/2005", "Analista", 100);
                empleados.add(emp3);
                Empleado emp4 = empContr.crearEmpleado(4, "Alberto", 20, 1100, "15/11/2001",
                                null, null);
                empleados.add(emp4);
                Empleado emp5 = empContr.crearEmpleado(5, "Fernando", 30, 1400, "20/11/1999",
                                "Analista", 200);
                empleados.add(emp5);

                for (Empleado e : empleados) {
                        empContr.guardarEmpleado(e);
                }

                List<Bson> sinFiltros = Arrays.asList();
                empContr.listarEmpleados(sinFiltros).forEach(e -> System.out.println(e));

                // Empleados del departamento 10
                System.out.println();
                System.out.println("Empleados de departamento 10");
                List<Bson> listaFiltro = Arrays.asList(
                                new Document("$match", Filters.eq("dep", 10)));
                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                System.out.println();

                // Empleados de los departamentos 10 y 20
                System.out.println("Empleados de departamento 10 y 20");
                listaFiltro = Arrays.asList(
                                new Document("$match",
                                                Filters.in("dep", Arrays.asList(10, 20))));
                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                System.out.println();

                // Empleados con salario > 1300 y oficio Profesora
                System.out.println("Empleados con salario > 1300 y oficio Profesora");
                listaFiltro = Arrays.asList(
                                new Document("$match", Filters.and(
                                                Filters.eq("oficio", "Profesora"),
                                                Filters.gt("salario", 1300))));
                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                System.out.println();

                // Sube el salario de los analistas en 100, a todos
                System.out.println("Sube el salario de los analistas en 100, a todos");
                Bson modificador = new Document("$inc", new Document("salario", 100));
                Long count = empContr.modificarEmpleado(modificador, Filters.eq("oficio", "Analista"));
                System.out.println(count);

                System.out.println();

                // Decreta la comision en 20€
                System.out.println("Decreta la comision en 20€");
                modificador = new Document("$inc", new Document("comision", -20));
                count = empContr.modificarEmpleado(modificador, Filters.exists("comision"));
                System.out.println(count);

                // Media de salario
                System.out.println();
                System.out.println("Media del salario");
                listaFiltro = Arrays.asList(
                                new Document("$group", new Document("_id", null)
                                                .append("promedioSalario",
                                                                new Document("$avg", "$salario"))));

                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                // Visualiza por departamento el número de empleados, el salario medio y el
                // máximo salario.
                System.out.println();
                System.out.println(
                                "Visualiza por departamento el número de empleados, el salario medio y el máximo salario.");
                listaFiltro = Arrays.asList(
                                new Document("$group", new Document("_id", "$dep")
                                                .append("numEmpleados", new Document("$sum", 1))
                                                .append("salarioMedio", new Document("$avg", "$salario"))
                                                .append("salarioMáximo", new Document("$max", "$salario"))));

                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                // Visualiza el nombre del empleado que tiene el máximo salario
                System.out.println();
                System.out.println("Visualiza el nombre del empleado que tiene el máximo salario");
                listaFiltro = Arrays.asList(
                                new Document("$sort", new Document("salario", -1)),
                                new Document("$limit", 1),
                                new Document("$project", new Document("_id", 0)
                                                .append("nombre", 1)
                                                .append("salario", 1)));

                empContr.listarEmpleados(listaFiltro).forEach(e -> System.out.println(e));

                empleados.forEach(e -> empContr.borrarEmpleado(e));
        }
}

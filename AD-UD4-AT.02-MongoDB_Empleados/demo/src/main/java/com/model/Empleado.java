package com.model;

public class Empleado {
    private int Emp_no;
    private String nombre;
    private int dep;
    private int salario;
    private String fechaalta;
    private String oficio;
    private Integer comision;

    public Empleado(int Emp_no, String nombre, int dep, int salario, String fechaalta, String oficio, Integer comision) {
        this.Emp_no = Emp_no;
        this.nombre = nombre;
        this.dep = dep;
        this.salario = salario;
        this.fechaalta = fechaalta;
        this.oficio = oficio;
        this.comision = comision;
    }

    /**
     * Devuelve el no del empleado
     * 
     * @return Emp_no
     */
    public int getEmp_no() {
        return Emp_no;
    }

    /**
     * Modifica el no del empleado
     * 
     * @param emp_no
     */
    public void setEmp_no(int emp_no) {
        Emp_no = emp_no;
    }

    /**
     * Devuelve el nombre del empleado
     * 
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del empleado
     * 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el departamento al que pertenece
     * 
     * @return dep
     */
    public int getDep() {
        return dep;
    }

    /**
     * Modifica el departamento al que pertenece
     * 
     * @param dep
     */
    public void setDep(int dep) {
        this.dep = dep;
    }

    /**
     * Devuelve el salario del empleado
     * 
     * @return salario
     */
    public int getSalario() {
        return salario;
    }

    /**
     * Modifica el salario del empleado
     * 
     * @param salario
     */
    public void setSalario(int salario) {
        this.salario = salario;
    }

    /**
     * Devuelve la fecha de alta del empleado
     * 
     * @return fechaalta
     */
    public String getFechaalta() {
        return fechaalta;
    }

    /**
     * Modifica la fecha de alta
     * 
     * @param fechaalta
     */
    public void setFechaalta(String fechaalta) {
        this.fechaalta = fechaalta;
    }

    /**
     * Devuelve el oficio del empleado
     * 
     * @return oficio
     */
    public String getOficio() {
        return oficio;
    }

    /**
     * Modifica el oficio del empleado
     * 
     * @param oficio
     */
    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    /**
     * Devuelve la comision del empleado
     * 
     * @return comision
     */
    public Integer getComision() {
        return comision;
    }

    /**
     * Modifica la comision del empleado
     * 
     * @param comision
     */
    public void setComision(int comision) {
        this.comision = comision;
    }

}

package com.mycompany.consultorio;

public class Medico {
    private int id;
    private String nombre;
    private String especialidad;

    public Medico(int id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Especialidad: " + especialidad;
    }
}

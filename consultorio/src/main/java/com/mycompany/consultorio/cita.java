/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consultorio;

/**
 *
 * @author Daniel Noriega R
 */
public class cita {
    static int contador = 1;
    int id;
    String fecha;
    String hora;
    String motivo;
    medico doctor;
    paciente paciente;

    public cita() {
        this.id = contador++;
    }
}
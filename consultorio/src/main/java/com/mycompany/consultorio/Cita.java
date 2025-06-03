package com.mycompany.consultorio;

public class Cita {
    private int id;
    private String fechaHora;
    private String motivo;
    private int idMedico;
    private int idPaciente;

    public Cita(int id, String fechaHora, String motivo, int idMedico, int idPaciente) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public int getId() { return id; }
    public String getFechaHora() { return fechaHora; }
    public String getMotivo() { return motivo; }
    public int getIdMedico() { return idMedico; }
    public int getIdPaciente() { return idPaciente; }

    @Override
    public String toString() {
        return "ID: " + id + ", Fecha y Hora: " + fechaHora + ", Motivo: " + motivo +
               ", ID Médico: " + idMedico + ", ID Paciente: " + idPaciente;
    }
}
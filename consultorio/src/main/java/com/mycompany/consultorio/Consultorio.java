package com.mycompany.consultorio;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Consultorio {

    static List<medico> listamedico = new ArrayList<>();
    static List<paciente> listapaciente = new ArrayList<>();
    static List<cita> listacitas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        String usuario;
        String contrasena;
        System.out.println("Bienvenido al programa para dar de alta tu cita");
        System.out.println("Inicia sesion para continuar");
        System.out.println("Usuario: ");
        usuario = entrada.nextLine();
        System.out.println("Contraseña: ");
        contrasena = entrada.nextLine();
        System.out.println("");
        if (ValidarUsuario(usuario, contrasena)) {
            System.out.println("Bienvenido: " + usuario);
            menu();
            transacciones();
        } else {
            System.out.println("Usuario / Contraseña incorrecta. Favor de validar.");
        }
    }

    static boolean ValidarUsuario(String usuario, String contrasena) {
        String usuariolocal = "admin";
        String contrasenalocal = "root";
        return usuariolocal.equals(usuario) && contrasenalocal.equals(contrasena);
    }

    static void menu() {
        System.out.println("Seleccione una opcion");
        System.out.println("1.-Dar de alta un medico");
        System.out.println("2.-Dar de alta un paciente");
        System.out.println("3.-Crear Cita");
        System.out.println("4.-Lista de medicos");
        System.out.println("5.-Lista de pacientes");
        System.out.println("6.-Lista de citas");
    }

    static void transacciones() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.print("Ingrese el numero de operacion a realizar: ");
        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Alta de medico");
                altamedico();
                break;
            case 2:
                System.out.println("Alta de paciente");
                AltaPaciente();
                break;
            case 3:
                System.out.println("Crear Cita");
                CrearCita();
                break;
            case 4:
                System.out.println("Lista de medicos");
                listarMedicos();
                break;
            case 5:
                System.out.println("Lista de pacientes");
                ListaPacientes();
                break;
            case 6:
                System.out.println("Lista de citas");
                ListaCitas();
                break;
        }
    }

    static void altamedico() {
        Scanner entrada = new Scanner(System.in);
        String respuesta;

        do {
            medico medico = new medico();
            System.out.print("Ingrese el ID del medico: ");
            medico.id = entrada.nextInt();
            entrada.nextLine();
            System.out.print("Ingrese el Nombre del medico: ");
            medico.nombre = entrada.nextLine();
            System.out.print("Ingresa la especialidad: ");
            medico.especialidad = entrada.nextLine();
            listamedico.add(medico);
            System.out.println("medico registrado");

            System.out.print("dar de alta otro medico? (si/no): ");
            respuesta = entrada.nextLine();

        } while (respuesta.equalsIgnoreCase("si"));
        menu();
        transacciones();
    }

    static void AltaPaciente() {
        Scanner entrada = new Scanner(System.in);
        String respuesta;

        do {
            paciente paciente = new paciente();
            System.out.print("Ingrese el ID del paciente: ");
            paciente.id = entrada.nextInt();
            entrada.nextLine();
            System.out.print("Ingrese el Nombre del paciente: ");
            paciente.nombre = entrada.nextLine();
            listapaciente.add(paciente);
            System.out.println("Paciente registrado");
            System.out.print("agregar otro paciente? (si/no): ");
            respuesta = entrada.nextLine();
        } while (respuesta.equalsIgnoreCase("si"));
        menu();
        transacciones();
    }

    static void CrearCita() {
        Scanner entrada = new Scanner(System.in);

        if (listamedico.isEmpty() || listapaciente.isEmpty()) {
            System.out.println("Registre pacientes antes de crear citas");
            menu();
            transacciones();
            return;
        }

        cita nuevaCita = new cita();

        System.out.print("Ingrese el ID de la cita: ");
        nuevaCita.id = entrada.nextInt();
        entrada.nextLine();
        System.out.print("Ingrese la fecha de la cita (DD/MM/AAAA): ");
        nuevaCita.fecha = entrada.nextLine();
        System.out.print("Ingrese la hora de la cita (HH:MM): ");
        nuevaCita.hora = entrada.nextLine();
        System.out.print("Ingrese el motivo de la cita: ");
        nuevaCita.motivo = entrada.nextLine();

        System.out.println("Lista de medicoss:");
        for (medico m : listamedico) {
            System.out.println("ID: " + m.id + " | Nombre: " + m.nombre + " | Especialidad: " + m.especialidad);
        }
        System.out.print("Ingrese el ID del medico para esta cita: ");
        int idMedico = entrada.nextInt();
        entrada.nextLine();

        medico medicoSeleccionado = null;
        for (medico m : listamedico) {
            if (m.id == idMedico) {
                medicoSeleccionado = m;
                break;
            }
        }

        if (medicoSeleccionado == null) {
            System.out.println("Medico no encontrado.");
            menu();
            transacciones();
            return;
        }

        System.out.println("Lista de pacientes:");
        for (paciente p : listapaciente) {
            System.out.println("ID: " + p.id + " | Nombre: " + p.nombre);
        }
        System.out.print("Ingrese el ID del paciente para esta cita: ");
        int idPaciente = entrada.nextInt();
        entrada.nextLine();

        paciente pacienteSeleccionado = null;
        for (paciente p : listapaciente) {
            if (p.id == idPaciente) {
                pacienteSeleccionado = p;
                break;
            }
        }

        if (pacienteSeleccionado == null) {
            System.out.println("Paciente no encontrado.");
            menu();
            transacciones();
            return;
        }

        nuevaCita.doctor = medicoSeleccionado;
        nuevaCita.paciente = pacienteSeleccionado;

        listacitas.add(nuevaCita);
        System.out.println("Cita creada exitosamente.");

        menu();
        transacciones();
    }

    static void listarMedicos() {
        System.out.println("Lista de medicos:");
        if (listamedico.isEmpty()) {
            System.out.println("No hay medicos registrados.");
        } else {
            for (medico m : listamedico) {
                System.out.println("ID: " + m.id + " | Nombre: " + m.nombre + " | Especialidad: " + m.especialidad);
            }
        }
        menu();
        transacciones();
    }

    static void ListaPacientes() {
        System.out.println("Lista de pacientes:");
        if (listapaciente.isEmpty()) {
            System.out.println("No hay pacientes registrados");
        } else {
            for (paciente p : listapaciente) {
                System.out.println("ID: " + p.id + " | Nombre: " + p.nombre);
            }
        }
        menu();
        transacciones();
    }

    static void ListaCitas() {
        System.out.println("Lista de citas:");
        if (listacitas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            for (cita c : listacitas) {
                System.out.println("ID: " + c.id +
                                   " | Fecha: " + c.fecha +
                                   " | Hora: " + c.hora +
                                   " | Motivo: " + c.motivo +
                                   " | Doctor: " + c.doctor.nombre +
                                   " | Paciente: " + c.paciente.nombre);
            }
        }
        menu();
        transacciones();
    }
}
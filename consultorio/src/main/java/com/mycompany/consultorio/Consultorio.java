package com.mycompany.consultorio;

import java.io.*;
import java.util.*;

public class Consultorio {

    private static final String ARCHIVO_MEDICOS = "medicos.csv";
    private static final String ARCHIVO_PACIENTES = "pacientes.csv";
    private static final String ARCHIVO_CITAS = "citas.csv";

    private static List<Medico> medicos = new ArrayList<>();
    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Cita> citas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        if (!usuario.equals("admin") || !password.equals("root")) {
            System.out.println("Credenciales incorrectas. Saliendo...");
            return;
        }

        cargarMedicos();
        cargarPacientes();
        cargarCitas();

        int opcion;
        do {
            System.out.println("\n1. Registrar médico");
            System.out.println("2. Registrar paciente");
            System.out.println("3. Registrar cita");
            System.out.println("4. Mostrar médicos");
            System.out.println("5. Mostrar pacientes");
            System.out.println("6. Mostrar citas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> registrarMedico(sc);
                case 2 -> registrarPaciente(sc);
                case 3 -> registrarCita(sc);
                case 4 -> mostrarMedicos();
                case 5 -> mostrarPacientes();
                case 6 -> mostrarCitas();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

private static void registrarMedico(Scanner sc) {
    System.out.print("Nombre del médico: ");
    String nombre = sc.nextLine();
    System.out.print("Especialidad: ");
    String especialidad = sc.nextLine();
    int id = medicos.size() + 1;
    medicos.add(new Medico(id, nombre, especialidad));

    List<String[]> datos = new ArrayList<>();
    datos.add(new String[]{String.valueOf(id), nombre, especialidad});
    guardarCSV(ARCHIVO_MEDICOS, datos, new String[]{"ID", "Nombre", "Especialidad"});

    System.out.println("Médico registrado con ID " + id);
}


private static void registrarPaciente(Scanner sc) {
    System.out.print("Nombre del paciente: ");
    String nombre = sc.nextLine();
    int id = pacientes.size() + 1;
    pacientes.add(new Paciente(id, nombre));

    List<String[]> datos = new ArrayList<>();
    datos.add(new String[]{String.valueOf(id), nombre});
    guardarCSV(ARCHIVO_PACIENTES, datos, new String[]{"ID", "Nombre"});

    System.out.println("Paciente registrado con ID " + id);
}


    private static void registrarCita(Scanner sc) {
    if (medicos.isEmpty()) {
        System.out.println("No hay médicos registrados.");
        return;
    }
    if (pacientes.isEmpty()) {
        System.out.println("No hay pacientes registrados.");
        return;
    }

    System.out.println("Médicos disponibles:");
    for (Medico m : medicos) {
        System.out.println(m.getId() + ". " + m.getNombre() + " (" + m.getEspecialidad() + ")");
    }
    System.out.print("Seleccione el ID del médico: ");
    int idMedico = Integer.parseInt(sc.nextLine());
    Medico medico = medicos.stream().filter(m -> m.getId() == idMedico).findFirst().orElse(null);
    if (medico == null) {
        System.out.println("Médico no encontrado.");
        return;
    }

    System.out.println("Pacientes disponibles:");
    for (Paciente p : pacientes) {
        System.out.println(p.getId() + ". " + p.getNombre());
    }
    System.out.print("Seleccione el ID del paciente: ");
    int idPaciente = Integer.parseInt(sc.nextLine());
    Paciente paciente = pacientes.stream().filter(p -> p.getId() == idPaciente).findFirst().orElse(null);
    if (paciente == null) {
        System.out.println("Paciente no encontrado.");
        return;
    }

    System.out.print("Fecha y hora (ej. 2025-06-03 15:00): ");
    String fechaHora = sc.nextLine();
    System.out.print("Motivo de la cita: ");
    String motivo = sc.nextLine();

    int id = citas.size() + 1;
    Cita cita = new Cita(id, fechaHora, motivo, idMedico, idPaciente);
    citas.add(cita);

    List<String[]> datos = new ArrayList<>();
    datos.add(new String[]{String.valueOf(id), fechaHora, motivo, String.valueOf(idMedico), String.valueOf(idPaciente)});
    guardarCSV(ARCHIVO_CITAS, datos, new String[]{"ID", "FechaHora", "Motivo", "IDMedico", "IDPaciente"});

    System.out.println("Cita registrada con ID " + id);
}


    private static void mostrarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        System.out.println("Lista de médicos:");
        medicos.forEach(m -> System.out.println(m));
    }

    private static void mostrarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        System.out.println("Lista de pacientes:");
        pacientes.forEach(p -> System.out.println(p));
    }

    private static void mostrarCitas() {
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }
        System.out.println("Lista de citas:");
        citas.forEach(c -> {
            Medico medico = medicos.stream().filter(m -> m.getId() == c.getIdMedico()).findFirst().orElse(null);
            Paciente paciente = pacientes.stream().filter(p -> p.getId() == c.getIdPaciente()).findFirst().orElse(null);
            System.out.println("ID: " + c.getId() +
                    ", Fecha y Hora: " + c.getFechaHora() +
                    ", Motivo: " + c.getMotivo() +
                    ", Médico: " + (medico != null ? medico.getNombre() : "Desconocido") +
                    ", Paciente: " + (paciente != null ? paciente.getNombre() : "Desconocido"));
        });
    }

    private static void guardarCSV(String archivo, List<String[]> datos, String[] encabezado) {
        boolean existe = new File(archivo).exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, true))) {
            if (!existe) {
                writer.println(String.join(",", encabezado));
            }
            for (String[] fila : datos) {
                writer.println(String.join(",", fila));
            }
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    private static void cargarMedicos() {
        File archivo = new File(ARCHIVO_MEDICOS);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String especialidad = partes[2];
                medicos.add(new Medico(id, nombre, especialidad));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar médicos: " + e.getMessage());
        }
    }

    private static void cargarPacientes() {
        File archivo = new File(ARCHIVO_PACIENTES);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                pacientes.add(new Paciente(id, nombre));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
    }

    private static void cargarCitas() {
        File archivo = new File(ARCHIVO_CITAS);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            br.readLine(); // Saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int id = Integer.parseInt(partes[0]);
                String fechaHora = partes[1];
                String motivo = partes[2];
                int idMedico = Integer.parseInt(partes[3]);
                int idPaciente = Integer.parseInt(partes[4]);
                citas.add(new Cita(id, fechaHora, motivo, idMedico, idPaciente));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar citas: " + e.getMessage());
        }
    }
}

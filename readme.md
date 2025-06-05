# Consultorio Médico en Java

## Instalación y configuración
1. Clona este repositorio para realizar los cambios
2. Compila los archivos `.java` con `javac`.
3. Ejecuta el programa con `java`.

## Uso del programa
1. Inicia sesión como administrador local con las respectivas credenciales
user: "admin"
pass: "Root"
3. al inciar debemos de selccionar las opciones para dar de alta a los medicos y pacientes
    3.1 Registro de pacientes: para el registro de pacientes debemos de ingresar el nombre en la consola, en automatico el programa le asiganara un numero ID unico
    3.2 Registro de medicos: para el registro de medicos debemos ingresar los siguintes datos en la consola:
       *Nombre de medico
       *Especialidad
       **Una vez registrado estos datos, en automatico el programa le asiganara un numero ID unico
   
4. una vez registrados podremos acceder a agendar citas
5. al iniciar la opcion agendar cita nos solicitara los siguintes datos
    5.1 Id medico: nos solicitara que seleccionemos el ID del medico **el sistema desplegara la lista de medicos
    5.2 ID Paciente: nos solicitara que seleccionemos el ID del paciente **el sistema desplegara la lista de pacientes
    5.3 Fecha & Hora: el sistema solicitara que ingresemos la Fecha & Hora en formato YYYY-MM-DD HH:MM
    5.4 Motivo de cita: se debera de capturara un brebe motivo por el cual se agenda la cita medica
    5.5 muestra el ID de registro de cita medica
   
6. Mostrar medicos, desplegara la lsita de medicos registrados
7. Mostrar pacientes, desplegara la lista de pacientes registrados
8. Mostrar Citas, el sistema desplegara la informacion relaciona con las citas registradas, datos como:
   -- ID
   -- Fecha & Hora
   -- Motivo
   -- Medico
   -- Paciente

9. Salir del sistema, se desplegara un mensaje: "Saliendo..."


## Créditos
Quisiera agradecer a 2 personas exte4rnas a tecmilenio las cuales me ayudaron a darme una explicacion y retroalimnetacion acerca de la creacion de metodos y su relacion con archicos CSV.
- Saul Jimenez (System delivery Analist - Flex)
- Ruben Garcia (explicacion de programacion en java) 

## Licencia
MIT License

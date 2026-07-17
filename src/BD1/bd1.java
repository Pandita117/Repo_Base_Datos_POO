package BD1;

import Models.Tarjeta;
import Models.Usuario;
import Utils.Validacion;
import Utils.Validaciones;
import dao.UsuarioDAO;
import dao.TarjetaDao;
import java.util.ArrayList;
import java.util.Scanner;

public class bd1 {
    static Scanner scan = new Scanner(System.in);
    static UsuarioDAO dao = new UsuarioDAO();
    static TarjetaDao tarjed = new TarjetaDao();


    //Flujo main
    public static void main(String[] args) {
//Menu de acciones user o tarjetas
        int opcion_menu = -1;
        System.out.println("=====Bienvendido======");

        while (opcion_menu != 0) {
            System.out.println("1. Gestion de Usuario");
            System.out.println("2. Gestion de Tarjetas");
            System.out.println("0. Salir");
            opcion_menu = Validacion.Scanner_enteros(scan,"");

            switch (opcion_menu) {
                case 1:
                int opcion = 0;
                do {
                    menu();
                    try {
                        opcion = Integer.parseInt(scan.nextLine());

                        switch (opcion) {
                            case 1:
                                mostrarUsuario();
                                break;
                            case 2:
                                registrarUsuario();
                                break;
                            case 3:
                                buscarUsuario();
                                break;
                            case 4:
                                editarUsuario();
                                break;
                            case 5:
                                eliminarUsuario();
                                break;
                            case 6:
                                System.out.println("Saliendo");

                                break;

                            default:
                                System.out.println("\nOpción inválida.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nDebe ingresar un número.");
                    }
                    if (opcion != 6) {
                        System.out.println("\nPresione ENTER para continuar...");
                        scan.nextLine();
                    }
                } while (opcion != 6);
                break;
                case 2 :
                    int opcion_tarje = -1;
                    do {
                        menu_tarjeta();
                        opcion_tarje = Validacion.Scanner_enteros(scan, "");
                        switch (opcion_tarje) {
                            case 1:
                                mostrar_tarjetas();
                                break;
                            case 2:
                                registrarTarjeta();
                                break;
                            case 3:
                                mostrarTarjetaIndividual();
                                break;
                            case 4:
                                System.out.println("En proceso");
                                break;
                            case 5:
                                System.out.println("Saliendo...");
                                break;
                            default:
                                System.out.println("Opcion Invalida");
                        }
                        if (opcion_tarje!= 5) {
                            System.out.println("\nPresione ENTER para continuar...");
                            scan.nextLine();
                        }
                    } while (opcion_tarje!=5 );

                    break;

                default:
                    System.out.println("Opcion invalida intente de nuevo");
                    break;
            }
        }
    }
//Menu de user del main
        public static void menu () {
            System.out.println();
            System.out.println("===========================================");
            System.out.println("       SISTEMA DE USUARIOS");
            System.out.println("===========================================");
            System.out.println("1. Mostrar usuarios");
            System.out.println("2. Registrar usuarios");
            System.out.println("3. Buscar usuarios");
            //System.out.println("4. Editar usuarios");
            System.out.println("4. Eliminar usuarios");
            System.out.println("5. Salir");
            System.out.println("===========================================");
            System.out.print("Seleccione una opción: ");
        }


        //Metodo de mostrar usuarios
        public static void mostrarUsuario () {
            ArrayList<Usuario> lista = dao.leerTodos();

            if (lista.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
                return;
            }
            System.out.println();
            System.out.printf("%-5s %-15s %-30s %-8s %-10s %-10s%n",
                    "ID",
                    "NOMBRE",
                    "APELLIDO",
                    "EDAD",
                    "EMAIL",
                    "ACTIVO");
            System.out.println("---------------------------------------------------------------------------------------------");
            for (Usuario usuario : lista) {
                System.out.printf("%-5d %-15s %-30s %-8d %-10s %-10s%n",
                        usuario.getId_user(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getEdad(),
                        usuario.getEmail(),
                        usuario.isActivo() ? "SI" : "NO");
            }
        }
//Registro de user ya calado
        public static void registrarUsuario () {
            Usuario usuario = new Usuario();
            String nombre;

            do {
                System.out.print("Ingrese el nombre del usuario: ");
                nombre = scan.nextLine();

                if (!Validaciones.soloLetras(nombre)) {
                    System.out.println("El nombre solo puede contener letras.");
                }
            } while (!Validaciones.soloLetras(nombre));

            String apellido;
            do {
                System.out.println("Ingrese el apellido del usuario: ");
                apellido = scan.nextLine();
                if (!Validaciones.soloLetras(apellido)) {
                    System.out.println("El apellido materno solo puede contener letras.");
                }
            } while (!Validaciones.soloLetras(apellido));
            int edad = 0;
            boolean edadValida = false;
            while (!edadValida) {
                try {
                    System.out.print("Ingrese la edad del usuario: ");
                    edad = Integer.parseInt(scan.nextLine());

                    if (Validaciones.edadValida(edad)) {
                        edadValida = true;
                    } else {
                        System.out.println("Edad inválida.");
                    }
                } catch (Exception e) {
                    System.out.println("Debe ingresar un número.");
                }
            }
            String email;
            do {
                System.out.println("Ingrese el email del usuario: ");
                email = scan.nextLine();
                if (Validaciones.estaVacio(email)) {
                    System.out.println("El email no puede quedar vacio");
                }
            } while (Validaciones.estaVacio(email));
            String password;
            do {
                System.out.println("Cree una contraseña para el usuario: ");
                password = scan.nextLine();
                if (Validaciones.estaVacio(password)) {
                    System.out.println("La contraseña no puede estar vacia");
                }
            } while (Validaciones.estaVacio(password));

            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setEdad(edad);
            usuario.setEmail(email);
            usuario.setPassword(password);
            usuario.setActivo(true);

            if (dao.insertar(usuario)) {
                System.out.println("Usuario registrado correctamente.");
            } else {
                System.out.println("No fue posible registrar al usuario");
            }
        }
        //Busqueda de id mostrar datos de id seleccionado
        public static void buscarUsuario () {
            int id = 0;
            boolean correcto = false;

            while (!correcto) {
                try {
                    System.out.print("Ingrese el ID del usuario: ");
                    id = Integer.parseInt(scan.nextLine());
                    correcto = true;
                } catch (Exception e) {
                    System.out.println("Debe ingresar un número.");
                }
            }

            Usuario usuario = dao.buscar(id);

            if (usuario == null) {
                System.out.println("\nUsuario no encontrado.");
                return;
            }

            System.out.println();
            System.out.println("==============================");
            System.out.println("      DATOS DEL USUARIO");
            System.out.println("==============================");
            System.out.println("ID: " + usuario.getId_user());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Edad: " + usuario.getEdad());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Password: " + usuario.getPassword());
            System.out.println("Activo: " + (usuario.isActivo() ? "SI" : "NO"));
        }
        //Deletear el user
        public static void eliminarUsuario () {
            int id = 0;

            try {
                System.out.print("Ingrese el ID del usuario a eliminar: ");
                id = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println("ID inválido.");
                return;
            }
            Usuario usuario = dao.buscar(id);
            if (usuario == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }
            System.out.println("Usuario encontrado:");
            System.out.println(usuario.getNombre());
            System.out.print("¿Está seguro de eliminarlo? (S/N): ");
            String respuesta = scan.nextLine().toUpperCase();
            if (!respuesta.equals("S")) {
                System.out.println("Operación cancelada.");
                return;
            }
            if (dao.eliminar(id)) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No fue posible eliminar al usuario.");
            }
        }

        //rEGISTRAR TARJETAS
        public static void registrarTarjeta() {



            System.out.print("Ingrese el ID del usuario dueño de la tarjeta: ");
            int idUsuario;
            try {
                idUsuario = Integer.parseInt(scan.nextLine());
            } catch (Exception e) {
                System.out.println("ID de usuario inválido.");
                return;
            }

            Usuario usuario = dao.buscar(idUsuario);
            if (usuario == null) {
                System.out.println("El usuario con ID " + idUsuario + " no existe. No se puede crear la tarjeta.");
                return;
            }

            System.out.println("Registrando tarjeta para: " + usuario.getNombre() + " " + usuario.getApellido());

            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setId_user(idUsuario);

            System.out.print("Tipo de tarjeta (D = Débito, C = Crédito): ");
            String tipo = Validacion.scan_string_vacio(scan).toUpperCase();

            double saldo = 0;
            double credito = 5000;

            try {
                if (tipo.equals("D")) {
                    System.out.print("Saldo inicial: ");
                    saldo = Double.parseDouble(scan.nextLine());
                } else if (tipo.equals("C")) {
                    System.out.print("Límite de crédito: ");
                    credito = Double.parseDouble(scan.nextLine());
                } else {
                    System.out.println("Tipo inválido. Registro cancelado.");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Monto numérico inválido. Registro cancelado.");
                return;
            }

            tarjeta.setTipo(tipo);
            tarjeta.setSaldo(saldo);
            tarjeta.setCredito(credito);

            if (tarjed.guardar(tarjeta)) {
                System.out.println("¡TARJETA REGISTRADA CON ÉXITO!");
                System.out.println("Número: " + tarjeta.getNumero());
                System.out.println("CLABE:  " + tarjeta.getClabe());
                System.out.println("CVV:    " + tarjeta.getCvv());
                System.out.println("Expiración: " + tarjeta.getFecha_exp());
                System.out.println("===========================================");
            } else {
                System.out.println("Error: No se pudo guardar la tarjeta en la base de datos.");
            }
        }

    //Menu de las tarjetas
    public static void menu_tarjeta (){
        System.out.println();
        System.out.println("===========================================");
        System.out.println("       SISTEMA DE TARJETAS");
        System.out.println("===========================================");
        System.out.println("1. Mostrar tarjetas");
        System.out.println("2. Registrar tarjeta");
        System.out.println("3. Buscar Tarjeta");
        System.out.println("4. Eliminar o desactivar tarjeta");
        System.out.println("5. Salir");
        System.out.println("Seleccione una opcion");
    }
//MOstrar tarjetas
    public static void mostrar_tarjetas() {

        ArrayList<Tarjeta> lista = tarjed.listar();

        if (lista.isEmpty()) {
            System.out.println("\nNo hay tarjetas registradas en el sistema.");
            return;
        }

        System.out.println();
        System.out.printf("%-5s %-18s %-16s %-8s %-5s %-12s %-10s %-10s %-8s%n",
                "ID",
                "CLABE",
                "NÚMERO",
                "EXP",
                "CVV",
                "SALDO",
                "TIPO",
                "LÍMITE CRÉD.",
                "ID USER");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        for (Tarjeta t : lista) {
            System.out.printf("%-5d %-18s %-16s %-8s %-5s $%-11.2f %-10s $%-9.2f %-8d%n",
                    t.getId_tarjeta(),
                    t.getClabe(),
                    t.getNumero(),
                    t.getFecha_exp(),
                    t.getCvv(),
                    t.getSaldo(),
                    t.getTipo().equals("D") ? "Débito" : "Crédito",
                    t.getCredito(),
                    t.getId_user());
        }
    }

    public static void mostrarTarjetaIndividual() {


        System.out.print("Ingrese el ID de la tarjeta que desea consultar: ");

        int idBuscar;
        while (true) {
            try {
                idBuscar = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Error: Ingrese solo números válidos. Intente de nuevo: ");
            }
        }


        Tarjeta tarjeta = tarjed.buscar(idBuscar);

        if (tarjeta == null) {
            System.out.println("\n[ERROR] No se encontró ninguna tarjeta con el ID: " + idBuscar);
            return;
        }


        System.out.printf("ID Tarjeta:          %d%n", tarjeta.getId_tarjeta());
        System.out.printf("Número de Tarjeta:   %s%n", tarjeta.getNumero());
        System.out.printf("CLABE Interbancaria: %s%n", tarjeta.getClabe());
        System.out.printf("Fecha de Expiración: %s%n", tarjeta.getFecha_exp());
        System.out.printf("CVV (Seguridad):     %s%n", tarjeta.getCvv());
        System.out.printf("Tipo de Cuenta:      %s%n", tarjeta.getTipo().equalsIgnoreCase("D") ? "Débito" : "Crédito");
        System.out.printf("Saldo Disponible:    $%.2f%n", tarjeta.getSaldo());
        System.out.printf("Límite de Crédito:   $%.2f%n", tarjeta.getCredito());
        System.out.printf("Estado de Tarjeta:   %s%n", tarjeta.isActivo() ? "ACTIVA / OPERATIVA" : "INACTIVA / BLOQUEADA");
        System.out.printf("ID Usuario Dueño:    %d%n", tarjeta.getId_user());
    }

//    public static void deletear_tarjeta (){
//        mostrar_tarjetas();
//        int tarjeta_elimi = -1;
//
//        try {
//            System.out.print("Ingrese el ID d la tarjeta a eliminar ");
//            tarjeta_elimi = Integer.parseInt(scan.nextLine());
//        } catch (Exception e) {
//            System.out.println("ID inválido.");
//            return;
//        }
//        Tarjeta tarje = dao.(tarjeta_elimi);
//        if (tarje == null) {
//            System.out.println("Usuario no encontrado.");
//            return;
//        }
//        System.out.println("Usuario encontrado:");
//        System.out.println(usuario.getNombre());
//        System.out.print("¿Está seguro de eliminarlo? (S/N): ");
//        String respuesta = scan.nextLine().toUpperCase();
//        if (!respuesta.equals("S")) {
//            System.out.println("Operación cancelada.");
//            return;
//        }
//        if (dao.eliminar(tarjeta_elimi)) {
//            System.out.println("Usuario eliminado correctamente.");
//        } else {
//            System.out.println("No fue posible eliminar al usuario.");
//        }
//
//    }
//




}
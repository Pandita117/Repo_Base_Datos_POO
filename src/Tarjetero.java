import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Tarjetero {

    public static Scanner leer = new Scanner(System.in);
    public static Random random = new Random();

    Credito credi= new Credito(null,null,null,null,null,null,false,null,0,0,0,null);
    Debito debi= new Debito(null,null,null,null,null,null,false,null,0);

    public static void crearTarjeta(ArrayList<Tarjeta> listaTarjetas, Scanner leer) {
        boolean sal = true;
        while (sal) {
            int tipo
            System.out.println("\nElija:");
            System.out.println("1. Crear tarjeta de credito");
            System.out.println("2. Crear tarjeta de debito");
            System.out.println("3. Salir");
                   tipo=leer.nextInt();
                   try {

                   }

            String nombreLocal, bancoLocal, numTarjetaLocal, clabeLocal, cvvLocal, fechaLocal;
            int mesLocal, anioLocal, saldoLocal, fechaCorteLocal;
            double anualidadLocal;

            switch (tipo) {
                case 1:
                    System.out.println("Inserte el nombre del propietario");
                    nombreLocal = Validacion.scan_string_vacio(leer);
                    System.out.println("Inserte de que banco es su tarjeta");
                    bancoLocal = Validacion.scan_string_vacio(leer);
                    numTarjetaLocal = generarNumeros(16);
                    clabeLocal = generarNumeros(18);
                    cvvLocal = generarNumeros(3);
                    saldoLocal = random.nextInt(5001) + 5000;
                    mesLocal = random.nextInt(12) + 1;
                    anioLocal = random.nextInt(6) + 26;
                    fechaCorteLocal = random.nextInt(28) + 1;
                    anualidadLocal = random.nextInt(2501) + 500;
                    fechaLocal = String.format("%02d/%02d", mesLocal, anioLocal);

                    System.out.println("\n--- Tarjeta de Crédito Creada ---");
                    System.out.println("Propietario: " + nombreLocal);
                    System.out.println("Tarjeta: " + numTarjetaLocal);


                    Credito nuevaCredito = new Credito(clabeLocal, cvvLocal, fechaLocal, numTarjetaLocal, nombreLocal, bancoLocal, true, "Credito", saldoLocal, anualidadLocal, 0, "Día " + fechaCorteLocal);
                    listaTarjetas.add(nuevaCredito);
                    break;

                case 2:
                    System.out.println("Inserte el nombre del propietario");
                    nombreLocal = Validacion.scan_string_vacio(leer);
                    System.out.println("Inserte de que banco es su tarjeta");
                    bancoLocal = Validacion.scan_string_vacio(leer);
                    System.out.println("Inserte cantidad a depositar a su tarjeta (Puede depositar 0)");

                    saldoLocal = Validacion.Scanner_enteros(leer,"");
                    if (saldoLocal>=0) {
                        saldoLocal = Validacion.Scanner_enteros(leer, "");
                        leer.nextLine();
                    } else {
                        System.out.println("No se puede ingresar numeros negativos ");
                        break;
                    }

                    numTarjetaLocal = generarNumeros(16);
                    clabeLocal = generarNumeros(18);
                    cvvLocal = generarNumeros(3);
                    mesLocal = random.nextInt(12) + 1;
                    anioLocal = random.nextInt(6) + 26;
                    fechaLocal = String.format("%02d/%02d", mesLocal, anioLocal);

                    System.out.println("\n--- Tarjeta de Débito Creada ---");
                    System.out.println("Propietario: " + nombreLocal);
                    System.out.println("Tarjeta: " + numTarjetaLocal);

                    Debito nuevaDebito = new Debito(clabeLocal, cvvLocal, fechaLocal, numTarjetaLocal, nombreLocal, bancoLocal, true, "Debito", saldoLocal);
                    listaTarjetas.add(nuevaDebito);
                    break;

                case 3:
                    sal = false;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
    }


    public static void mostrarTarjetas(ArrayList<Tarjeta> listaTarjetas) {
        if (listaTarjetas.isEmpty()) {
            System.out.println("No hay tarjetas registradas");
            return;
        }

        System.out.println("\n--- TARJETAS REGISTRADAS ---");
        for (Tarjeta tar : listaTarjetas) {
            String ultimos4 = tar.getNo_tar().substring(tar.getNo_tar().length() - 4);
            System.out.println(" | Nombre: " + tar.getNombre() + " | Numero de tarjeta: ****" + ultimos4 + " | Tipo: " + tar.getTipo() + " | Activo: " + (tar.isActivo() ? "Sí" : "No"));
        }

        System.out.println("\nIntroduce los últimos 4 dígitos de la tarjeta que desea utilizar:");
        String idBuscado = Validacion.scan_string_vacio(leer);

        Tarjeta tarjetaSeleccionada = null;
        for (Tarjeta tar : listaTarjetas) {
            if (tar.getNo_tar().endsWith(idBuscado)) {
                tarjetaSeleccionada = tar;
                break;
            }
        }

        if (tarjetaSeleccionada != null) {

            boolean continuarSubmenu = true;
            while (continuarSubmenu) {
                System.out.println("\n--> Tarjeta seleccionada de: " + tarjetaSeleccionada.getNombre());

                if (tarjetaSeleccionada.getTipo().equals("Credito")) {
                    System.out.println("1. Mostrar datos\n2. Comprar\n3. Pagar Tarjeta\n4. Depositar\n5. Ver movimientos\n6. Bloquear\n7. Activar\n8. Volver");
                    int acc = Validacion.Scanner_enteros(leer, "Opción: ");
                    Credito credi = (Credito) tarjetaSeleccionada;

                    switch (acc) {
                        case 1: credi.mostrar_datos();
                            break;
                        case 2: credi.comprar();
                            break;
                        case 3: credi.pagar_tarjeta();
                            break;
                        case 4: credi.depositar();
                            break;
                        case 5: credi.verMovimientos();
                            break;
                        case 6: credi.bloquear_tarjeta();
                            break;
                        case 7: credi.activar_tarjeta();
                            break;
                        case 8: continuarSubmenu = false;
                            break;
                        default: System.out.println("Opcion invalida");
                            break;
                    }
                } else if (tarjetaSeleccionada.getTipo().equals("Debito")) {
                    System.out.println("1. Mostrar datos\n2. Comprar\n3. Transferir\n4. Depositar\n5. Retirar\n6. Ver movimientos\n7. Bloquear\n8. Activar\n9. Volver");
                    int acc = Validacion.Scanner_enteros(leer, "Opción: ");
                    Debito debi = (Debito) tarjetaSeleccionada;

                    switch (acc) {
                        case 1: debi.mostrar_datos();
                            break;
                        case 2: debi.comprar();
                            break;
                        case 3: debi.transferir(listaTarjetas);
                            break;
                        case 4: debi.depositar();
                            break;
                        case 5: debi.retirar();
                            break;
                        case 6: debi.verMovimientos();
                            break;
                        case 7: debi.bloquear_tarjeta();
                            break;
                        case 8: debi.activar_tarjeta();
                            break;
                        case 9: continuarSubmenu = false;
                            break;
                        default: System.out.println("Opcion invalida");
                            break;
                    }
                }
            }
        } else {
            System.out.println("No se encontró ninguna tarjeta con esos últimos 4 dígitos.");
        }
    }


    public static String generarNumeros(int cantidad) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
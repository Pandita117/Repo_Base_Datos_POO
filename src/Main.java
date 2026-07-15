//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
void main() {
    Scanner scan = new Scanner(System.in);

    ArrayList<Usuario> listaUsuario = new ArrayList<>();
    int opc;
    System.out.println("Bienvenido a su sistema");
    boolean sal = true;
        while (sal) {
            System.out.println("Elija la accion que quiera realizar");
            System.out.println("1. Crear usuario");
            System.out.println("2. Crear tarjeta");
            System.out.println("3. Mostrar tarjetero");
            System.out.println("4. Salir");
            int acc;
            try {
                acc = Integer.parseInt(scan.nextLine());
                switch (acc) {
                    case 1:
                        do {
                        Usuario.menuUsuario();
                        opc= scan.nextInt();
                        switch (opc){
                            case 1:
                                Usuario.mostrarUsuario(listaUsuario);
                                break;
                            case 2:
                                Usuario.añadirUsuario(listaUsuario);
                                break;
                            case 3:
                                Usuario.editarUsuario(listaUsuario);
                                break;
                            case 4:
                                Usuario.eliminarUsuario(listaUsuario);
                                break;
                            default:
                                System.out.println("Opcion invalida");

                        }
                        }while (opc != 5);
                        break;
                    case 2:
                        Tarjetero.mostrarTarjetas(listaTarjetas);
                        break;
                    case 3:
                        System.out.println("Saliendo");
                        sal = false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("\nDebe ingresar un número.");
            }
        }
    }

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Usuario {
    protected String nombre, apellido, email, password;
    protected int edad, id_incrementable = 0, id ;
    protected boolean activo;


    static Scanner scan = new Scanner(System.in);

    public Usuario(String nombre, String apellido, String email, String password, int edad, int id, boolean activo) {
        id_incrementable++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.edad = edad;
        this.id = id_incrementable;
        this.activo = activo;
    }
    public Usuario() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public static void menuUsuario(){
        System.out.println("1. Mostrar usuario");
        System.out.println("2. Crear usuario");
        System.out.println("3. Editar usuario");
        System.out.println("4. Eliminar usuario");
        System.out.println("5. Salir");
    }
    public static void mostrarUsuario(ArrayList<Usuario> listaUsuario) {
        if (listaUsuario.isEmpty()) {
            System.out.println("La lista está vacía. Agrega algo");
            return;
        }
        System.out.println("--- INFORMACIÓN ---");
        for (Usuario us : listaUsuario) {
            System.out.println("ID: " + us.getId());
            System.out.println("Nombre: " + us.getNombre());
            System.out.println("Edad: " + us.getEdad());
            System.out.println("Tarjetas registradas: " );
            System.out.println(us.isActivo() ? "SI" : "NO");
            System.out.println("-------------------");
        }
    }

    public static void añadirUsuario(ArrayList<Usuario> listaUsuario) {
        String nom, ape, gma, pas;
int id, ed=0;
        do {
            System.out.print("Ingrese el nombre: ");
            nom = scan.nextLine();

            if (!Validaciones.soloLetras(nom)) {
                System.out.println("El nombre solo puede contener letras.");
            }
        } while (!Validaciones.soloLetras(nom));
        do {
            System.out.print("Ingrese el apellido: ");
            ape = scan.nextLine();
            if (!Validaciones.soloLetras(ape)) {
                System.out.println("El apellido solo puede contener letras.");
            }
        } while (!Validaciones.soloLetras(ape));
        boolean edadValida = false;
        while (!edadValida) {
            try {
                System.out.print("Ingrese la edad: ");
                ed = Integer.parseInt(scan.nextLine());

                if (Validaciones.edadValida(ed)) {
                    edadValida = true;
                }
                else {
                    System.out.println("Edad inválida.");
                }
            }
            catch (Exception e) {

                System.out.println("Debe ingresar un número.");
            }
        }
        System.out.println("Ingrese su email: ");
        gma = scan.nextLine();
        System.out.println("Ingrese su contraseña:");
        pas = scan.nextLine();
        boolean act = true;
        this.id_incrementable=id;
        Usuario usuario = new Usuario(nom,ape,gma,pas,ed,id,act);
    }

    public static void editarUsuario(ArrayList<Usuario> listaUsuario){
            if (listaUsuario.isEmpty()) {
                System.out.println("No hay usuarios para modificar.");
                return;
            }
            System.out.println("--- Usuarios registrados actualmente ---");
            for (Usuario p : listaUsuario) {
                System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Apellido: " + p.getApellido());
            }
            System.out.print("Ingrese el ID del grupo a modificar: ");
            int idBuscar;
            while (true) {
                try {
                    idBuscar = scan.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese solo números válidos.");
                    scan.next();
                }
            }
            Usuario usuarioEncontrado = null;
            for (Usuario user: listaUsuario) {
                if (user.getId() == idBuscar) {
                    usuarioEncontrado = user;
                    break;
                }
            }
            if (usuarioEncontrado == null) {
                System.out.println("Error: No se encontró ningún grupo con el ID " + idBuscar);
                return;
            }
            boolean sal = true;
            while (sal) {
                System.out.println("\n¿Qué desea modificar?");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. Edad");
                System.out.println("4. Correo");
                System.out.println("5. Contraseña");
                System.out.println("6. Activar");
                System.out.println("7. Salir");
                int mod;
                while (true) {
                    try {
                        mod = scan.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese solo números válidos.");
                        scan.next();
                    }
                }
                switch (mod) {
                    case 1 :
                        System.out.print("Nuevo nombre: ");
                        String nombre_mod = Validacion.scan_string_vacio(scan);
                        usuarioEncontrado.setNombre(nombre_mod);
                        System.out.println("¡Registro modificado con éxito!");
                        break;
                    case 2 :
                        System.out.print("Nuevo apellido: ");
                        String apelido_mod =Validacion.scan_string_vacio(scan);
                        usuarioEncontrado.setApellido(apelido_mod);
                        System.out.println("¡Registro modificado con éxito!");
                        break;
                    case 3:
                        System.out.print("Nueva edad: ");
                        int edad_nueva = Validacion.Scanner_enteros(scan,"");
                        if (edad_nueva<=18){
                            System.out.println("Edad invalida");
                            break;
                        } else {
                            usuarioEncontrado.setEdad(scan.nextInt());
                            System.out.println("¡Registro modificado con éxito!");
                        }
                        break;
                    case 4:
                        System.out.print("Nuevo email: ");
                        usuarioEncontrado.setEmail(scan.next());
                        System.out.println("¡Registro modificado con éxito!");
                        break;
                    case 5:
                        System.out.print("Nueva password: ");
                        usuarioEncontrado.setPassword(scan.next());
                        System.out.println("¡Registro modificado con éxito!");
                        break;
                    case 6:
                        if (usuarioEncontrado.isActivo()) {
                            System.out.println("El grupo ya se encuentra activo.");
                        } else {
                            usuarioEncontrado.setActivo(true);
                            System.out.println("Activado con éxito.");
                        }
                        break;
                    case 7 : sal = false;
                        break;
                    default: System.out.println("Opción inválida.");
                        break;
                }
            }
        }
        public static void eliminarUsuario(ArrayList<Usuario> listaUsuario){
            if (listaUsuario.isEmpty()) {
                System.out.println("No hay usuarios para modificar.");
                return;
            }
            System.out.println("--- Usuarios registrados actualmente ---");
            for (Usuario p : listaUsuario) {
                System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Apellido: " + p.getApellido());
            }
            System.out.print("Ingrese el ID del grupo a modificar: ");
            int idBuscar;
            while (true) {
                try {
                    idBuscar = scan.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese solo números válidos.");
                    scan.next();
                }
            }
            Usuario usuarioEncontrado = null;
            for (Usuario p : listaUsuario) {
                if (p.getId() == idBuscar) {
                    usuarioEncontrado = p;
                    break;
                }
            }
            if (usuarioEncontrado == null) {
                System.out.println("Error: No se encontró ningún grupo con el ID " + idBuscar);
                return;
            }
            boolean sal = true;
            while (sal) {
                System.out.println("\nElija qué quiere realizar con " + usuarioEncontrado.getNombre() + usuarioEncontrado.getApellido() + ":");
                System.out.println("1. Desactivar");
                System.out.println("2. Eliminar");
                System.out.println("3. Salir");
                int des;
                while (true) {
                    try {
                        des = scan.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Ingrese solo números válidos.");
                        scan.next();
                    }
                }
                switch (des) {
                    case 1 :
                        if (!usuarioEncontrado.().isEmpty() || grupoEncontrado.getTutor() != null) {
                            System.out.println("¡ERROR!: No se puede desactivar el grupo porque tiene alumnos inscritos o un tutor asignado.");
                        } else if (!usuarioEncontrado.getActivo()) {
                            System.out.println("Error: El grupo ya está desactivado.");
                        } else {
                            grupoEncontrado.setActivo(false);
                            System.out.println("Desactivado con éxito.");
                        }
                        break;
                    case 2:
                        if (!grupoEncontrado.getAlumnosInscritos().isEmpty() || grupoEncontrado.getTutor() != null) {
                            System.out.println("¡ERROR!: No se puede eliminar el grupo porque contiene alumnos inscritos o tiene un tutor asignado.");
                        } else {
                            lista.remove(grupoEncontrado);
                            System.out.println("Grupo eliminado con éxito.");
                            salo = false;
                        }
                        break;
                    case 3: salo = false;
                        break;
                    default: System.out.println("Opción inválida.");
                        break;
                }
            }
        }
        }





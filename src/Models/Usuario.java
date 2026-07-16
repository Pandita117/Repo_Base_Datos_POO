package Models;

public class Usuario {
    protected int id_user ;
    protected String nombre ;
    protected int edad;
    protected String apellido;
    protected String email;
    protected  String password;
    protected boolean activo ;


    public Usuario(int id_user, String nombre, int edad, String apellido, String email, String password, boolean activo) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.edad = edad;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.activo = activo;
    }

    public Usuario(String nombre, String apellido, String email, String password, int edad, int idUser, boolean activo) {
    }

    public Usuario() {

    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

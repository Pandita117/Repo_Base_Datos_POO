package Interfaces;
import  java.util.List;
import Models.Usuario;
    public interface IUsuarioDAO{
        boolean insertar(Usuario usuario);
        List<Usuario> leerTodos();
        boolean actualizar(Usuario usuario);
        boolean eliminar(int id);
        boolean cambiarEstado(int id, boolean activo);
        Usuario buscar(int id);
}

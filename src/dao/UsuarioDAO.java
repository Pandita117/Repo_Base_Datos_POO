



package dao;




import CONNECTION.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Interfaces.IUsuarioDAO;
import Models.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios_emiel (nombre, apellido, edad, email, password, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getEdad());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPassword());
            ps.setBoolean(6, usuario.isActivo());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> leerTodos() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios_emiel";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario user = new Usuario();
                user.setId_user(rs.getInt("id_user"));
                user.setNombre(rs.getString("nombre") != null ? rs.getString("nombre").trim() : "");
                user.setApellido(rs.getString("apellido") != null ? rs.getString("apellido").trim() : "");
                user.setEdad(rs.getInt("edad"));
                user.setEmail(rs.getString("email") != null ? rs.getString("email").trim() : "");
                user.setPassword(rs.getString("password") != null ? rs.getString("password").trim() : "");
                user.setActivo(rs.getBoolean("activo"));
                lista.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios_emiel SET nombre=?, apellido=?, edad=?, email=?, password=? WHERE id_user=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setInt(3, usuario.getEdad());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getPassword());
            ps.setInt(6, usuario.getId_user());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuarios_emiel WHERE id_user=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean cambiarEstado(int id, boolean activo) {
        String sql = "UPDATE usuarios_emiel SET activo=? WHERE id_user=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, activo);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario buscar(int id) {
        String sql = "SELECT * FROM usuarios_emiel WHERE id_user=?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario(); // Objeto limpio

                    u.setId_user(rs.getInt("id_user"));
                    u.setNombre(rs.getString("nombre") != null ? rs.getString("nombre").trim() : "");
                    u.setApellido(rs.getString("apellido") != null ? rs.getString("apellido").trim() : "");
                    u.setEdad(rs.getInt("edad"));
                    u.setEmail(rs.getString("email") != null ? rs.getString("email").trim() : "");
                    u.setPassword(rs.getString("password") != null ? rs.getString("password").trim() : "");
                    u.setActivo(rs.getBoolean("activo"));

                    return u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
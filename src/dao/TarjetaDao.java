package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import CONNECTION.Conexion;
import Models.Tarjeta;
import Interfaces.ITarjetaDAO;

public class TarjetaDao implements ITarjetaDAO {

    @Override
    public boolean guardar(Tarjeta tarjeta) {
        String sql = "INSERT INTO tarjetas_emiel (clabe, numero, fecha_exp, cvv, saldo, tipo, credito, activo, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tarjeta.getClabe());
            ps.setString(2, tarjeta.getNumero());
            ps.setString(3, tarjeta.getFecha_exp());
            ps.setString(4, tarjeta.getCvv());
            ps.setDouble(5, tarjeta.getSaldo());
            ps.setString(6, tarjeta.getTipo());
            ps.setDouble(7, tarjeta.getCredito());
            ps.setBoolean(8, tarjeta.isActivo());
            ps.setInt(9, tarjeta.getId_user());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al guardar tarjeta: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Tarjeta> listar() {
        ArrayList<Tarjeta> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarjetas_emiel ORDER BY id_tarjeta";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();

                tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
                tarjeta.setClabe(rs.getString("clabe"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setFecha_exp(rs.getString("fecha_exp"));
                tarjeta.setCvv(rs.getString("cvv"));
                tarjeta.setSaldo(rs.getDouble("saldo"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setCredito(rs.getDouble("credito"));
                tarjeta.setActivo(rs.getBoolean("activo"));
                tarjeta.setId_user(rs.getInt("id_user"));

                lista.add(tarjeta);
            }
        } catch (Exception e) {
            System.out.println("Error al listar tarjetas: " + e.getLocalizedMessage());
        }
        return lista;
    }

    @Override
    public Tarjeta buscar(int id) {
        Tarjeta tarjeta = null;
        String sql = "SELECT * FROM tarjetas_emiel WHERE id_tarjeta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tarjeta = new Tarjeta();

                    tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
                    tarjeta.setClabe(rs.getString("clabe"));
                    tarjeta.setNumero(rs.getString("numero"));
                    tarjeta.setFecha_exp(rs.getString("fecha_exp"));
                    tarjeta.setCvv(rs.getString("cvv"));
                    tarjeta.setSaldo(rs.getDouble("saldo"));
                    tarjeta.setTipo(rs.getString("tipo"));
                    tarjeta.setCredito(rs.getDouble("credito"));
                    tarjeta.setActivo(rs.getBoolean("activo"));
                    tarjeta.setId_user(rs.getInt("id_user"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar tarjeta: " + e.getLocalizedMessage());
        }
        return tarjeta;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM tarjetas_emiel WHERE id_tarjeta = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error al eliminar tarjeta: " + e.getLocalizedMessage());
            return false;
        }
    }
}
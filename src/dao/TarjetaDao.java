package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import CONNECTION.Conexion;
import Models.Tarjeta;
import Interfaces.ITarjetaDAO;

  class TarjetaDAO implements ITarjetaDAO {


    @Override
    public boolean guardar(Tarjeta tarjeta) {
        try {
            Connection con = Conexion.conectar();
            String sql = "INSERT INTO tarjetas "
                    + "(clabe, numero, mes_exp, año_exp, saldo, tipo, credito, activo, id_user) "
                    + "VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, tarjeta.getId());
            ps.setString(1, tarjeta.getClabe());
            ps.setString(2, tarjeta.getNumero());
            ps.setString(3, tarjeta.getFecha_exp());
            ps.setDouble(4, tarjeta.getSaldo());
            ps.setString(5, tarjeta.getTipo());
            ps.setDouble(6, tarjeta.getCredito());
            ps.setBoolean(7, tarjeta.isActivo());
            ps.setInt(8, tarjeta.getId_user());
            ps.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return false;
        }
    }
    @Override
    public ArrayList<Tarjeta> listar() {
        ArrayList<Tarjeta> lista = new ArrayList<>();
        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM tarjetas ORDER BY id_tarjeta";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
                tarjeta.setClabe(rs.getString("clabe"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setFecha_exp(rs.getString("Fecha expiracion"));
                tarjeta.setSaldo(rs.getDouble("saldo"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setCredito(rs.getDouble("credito"));
                tarjeta.setActivo(rs.getBoolean("activa"));
                tarjeta.setId_user(rs.getInt("id_user"));
                lista.add(tarjeta);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return lista;
    }

    @Override
    public Tarjeta buscar(int id) {
        Tarjeta tarjeta = null;

        try {
            Connection con = Conexion.conectar();
            String sql = "SELECT * FROM tarjetas WHERE numero = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tarjeta = new Tarjeta();
                tarjeta.setId_tarjeta(rs.getInt("id_tarjeta"));
                tarjeta.setClabe(rs.getString("clabe"));
                tarjeta.setNumero(rs.getString("numero"));
                tarjeta.setFecha_exp(rs.getString("Fecha expiracion"));
                tarjeta.setSaldo(rs.getDouble("saldo"));
                tarjeta.setTipo(rs.getString("tipo"));
                tarjeta.setCredito(rs.getDouble("credito"));
                tarjeta.setActivo(rs.getBoolean("activo"));
                tarjeta.setId_user(rs.getInt("id_user"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return tarjeta;
    }
    @Override
    public boolean eliminar(int id) {
        try {
            Connection con = Conexion.conectar();
            if (con == null) return false;

            String sql = "DELETE FROM tarjetas WHERE id_tarjeta = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();
            con.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return false;
        }
    }

}
package Models;

import java.util.Random;

public class Tarjeta {
    protected  int id_tarjeta;
    protected String clabe;
     protected String numero;
    protected String fecha_exp;
    protected double saldo;
    protected String tipo;
    protected String cvv;
    protected double credito;
    protected boolean activo;
    protected int id_user;

    public Tarjeta(int id_tarjeta, String clabe, String numero, String fecha_exp, String cvv, double saldo, String tipo, double credito, boolean activo, int id_user) {
        this.id_tarjeta = id_tarjeta;
        this.clabe = clabe;
        this.numero = numero;
        this.cvv = cvv;
        this.fecha_exp = fecha_exp;
        this.saldo = saldo;
        this.tipo = tipo;
        this.credito = credito;
        this.activo = activo;
        this.id_user = id_user;
    }

    public Tarjeta() {

    }

    static Random random = new Random();
    public static String generarNumeros(int cantidad) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < cantidad; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }

        return sb.toString();
    }



    void main(String[] args) {
        String numeroTarjeta = generarNumeros(16);
        String clabe = generarNumeros(18);
        String cvv = generarNumeros(3);

        int mes = random.nextInt(12) + 1;
        int anio = random.nextInt(6) + 26; // 26-31 (2026-2031)

        String fecha = String.format("%02d/%02d", mes, anio);

        this.numero= numeroTarjeta;
        this.clabe = clabe;
        this.cvv= cvv;
        this.fecha_exp= fecha;
    }



    public int getId_tarjeta() {
        return id_tarjeta;
    }

    public void setId_tarjeta(int id_tarjeta) {
        this.id_tarjeta = id_tarjeta;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha_exp() {
        return fecha_exp;
    }

    public void setFecha_exp(String fecha_exp) {
        this.fecha_exp = fecha_exp;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}

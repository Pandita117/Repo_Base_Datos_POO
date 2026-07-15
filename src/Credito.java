import java.util.Scanner;

public class Credito extends Tarjeta implements AccionesCredito {
    protected double credito, anualidad, saldo;
    protected String fecha_cor;

    public Credito(String CLABE, String CVV, String fecha_ven, String no_tar, String nombre, String banco, boolean activo, String tipo, double credito, double anualidad, double saldo, String fecha_cor) {
        super(CLABE, CVV, fecha_ven, no_tar, nombre, banco, activo, tipo);
        this.credito = credito;
        this.anualidad = anualidad;
        this.saldo = saldo;
        this.fecha_cor = fecha_cor;
    }

    Scanner leer = new Scanner(System.in);

    public Credito() {
    }

    @Override
    public void mostrar_datos(){
        System.out.println("CLABE: " + getCLABE());
        System.out.println("CVV: " + getCVV());
        System.out.println("Fecha de Vencimiento: " + getFecha_ven());
        System.out.println("Número de Tarjeta: " + getNo_tar());
        System.out.println("Nombre del Titular: " + getNombre());
        System.out.println("Banco: " + getBanco());
        System.out.println("Activa: " + (isActivo() ? "Sí" : "No"));
        System.out.println("Tipo: Credito");
        System.out.println("Fecha de corte: "+ getFecha_cor());
        System.out.println("Anualidad : "+getAnualidad());

    }

    @Override
    public void comprar() {
        if (!isActivo()) {
            System.out.println("No se puede realizar la compra porque la tarjeta está desactivada.");
            return;
        }
        System.out.println("Ingrese el monto a comprar: ");
        double monto = Validacion.Scanner_enteros(leer,"");
        if (monto>0 ) {
            monto = Validacion.Scanner_doubles(leer, "");
            leer.nextLine();
        } else {
            System.out.println("No se puede ingresar valores negativos");
            return;
        }
        this.saldo += monto;
        this.movimientos++;
        System.out.println("Compra realizada. Su saldo a pagar ahora es: " + this.saldo);
        if (this.saldo > this.credito) {
            System.out.println("Nota: Ha excedido su límite de crédito de " + this.credito);
        }
    }
    @Override
    public void depositar() {
        if (!isActivo()) {
            System.out.println("La tarjeta está bloqueada, no se puede depositar.");
            return;
        }
        System.out.println("Ingrese el monto a depositar (pagar a la tarjeta): ");
        double monto = Validacion.Scanner_enteros(leer,"");
        if (monto>0) {
            monto = Validacion.Scanner_doubles(leer, "");
            leer.nextLine();
        } else {
            System.out.println("No se pueden ingresar montos negativos");
            return;
        }

        this.saldo -= monto;
        this.movimientos++;
        System.out.println("Pago realizado. Su saldo deudor actual es: " + this.saldo);
    }

    @Override
    public void darDeBaja() {

    }

    @Override
    public void verMovimientos() {
        System.out.println("Total de movimientos realizados: " + this.movimientos);
        System.out.println("Saldo deudor actual: " + this.saldo);
        System.out.println("Límite de crédito: " + this.credito);
    }

    @Override
    public void bloquear_tarjeta() {
        if (!isActivo()) {
            System.out.println("La tarjeta ya se encuentra bloqueada.");
        } else {
            this.activo = false;
            System.out.println("Tarjeta bloqueada exitosamente.");
        }
    }

    @Override
    public void activar_tarjeta() {
        if (isActivo()) {
            System.out.println("La tarjeta ya se encuentra activa.");
        } else {
            this.activo = true;
            System.out.println("Tarjeta activada exitosamente.");
        }
    }

    @Override
    public void pagar_tarjeta() {
        System.out.println("Pagando tarjeta");
        depositar();


    }

//    @Override
//    public void pagar_anualidad() {
//        if (isActivo()) {
//            this.saldo += anualidad;
//            this.movimientos++;
//            System.out.println("Se ha cargado la anualidad de " + anualidad + " a su saldo.");
//        } else {
//            System.out.println("Tarjeta inactiva, no se puede cargar la anualidad.");
//        }
//    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getAnualidad() {
        return anualidad;
    }

    public void setAnualidad(double anualidad) {
        this.anualidad = anualidad;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFecha_cor() {
        return fecha_cor;
    }

    public void setFecha_cor(String fecha_cor) {
        this.fecha_cor = fecha_cor;
    }


}

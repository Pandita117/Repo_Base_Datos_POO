import java.util.Scanner;
import java.util.ArrayList;
public class Debito extends Tarjeta implements AccionesDebito {
    public static Scanner leer = new Scanner(System.in);
    protected double saldo;


    public Debito(String CLABE, String CVV, String fecha_ven, String no_tar, String nombre, String banco, boolean activo, String tipo, double saldo) {
        super(CLABE, CVV, fecha_ven, no_tar, nombre, banco, activo, tipo);
        this.saldo = saldo;
    }

    public Debito() {

    }

    @Override
    public void comprar() {
        if (!isActivo()) {
            System.out.println("No se puede realizar la compra porque la tarjeta está desactivada.");
            return;
        }
        System.out.println("Ingrese el monto de su compra");
        double monto = Validacion.Scanner_doubles(leer, "");
        if (monto <= 0) {
            System.out.println("No se pueden comprar valores negativos");
            return;
        }
        if (this.saldo >= monto) {
            this.saldo -= monto;
            System.out.println("Compra realizada exitosamente por: $" + monto);
            System.out.println("Nuevo saldo: $" + this.saldo);
        } else {
            System.out.println("Saldo insuficiente. Saldo actual: $" + this.saldo);
        }
    }

    @Override
    public void depositar() {
        if ((!isActivo())) {
            System.out.println("No se puede realizar el depósito porque la tarjeta está desactivada");
            return;
        }
        System.out.println("Ingrese el monto a depositar ");
        double deposito = Validacion.Scanner_doubles(leer, "");
        if (deposito <= 0) {
            System.out.println("No se pueden depositar valores negativos");
            return;
        }
        this.saldo += deposito;
        System.out.println("Depósito realizado exitosamente por: $" + deposito);
        System.out.println("Nuevo saldo: $" + this.saldo);
        this.movimientos++;

    }


    @Override
    public void darDeBaja() {

    }

    @Override
    public void verMovimientos() {
        if (!isActivo()) {
            System.out.println("No se puede ver movimientos tarjeta desactivada");
            return;
        }
        System.out.println("La cantidad de movimientos realizados por esta tarjeta son:" + this.movimientos);

    }

    @Override
    public void bloquear_tarjeta() {
        if (!isActivo()) {
            System.out.println("La tarjeta ya se encuentra bloqueada");
        } else {
            this.activo = false;
            System.out.println("Tarjeta desactivada exitosamente");
        }
    }

    @Override
    public void activar_tarjeta() {
        if (isActivo()) {
            System.out.println("La tarjeta ya se encuentra activa");
        } else {
            this.activo = true;
            System.out.println("Tarjeta activada exitosamente");
        }

    }


    @Override
    public void retirar() {
        if (!isActivo()) {
            System.out.println("No se puede retirar dinero tarjeta esta descativada");
            return;
        }
        System.out.println("Ingrese el monto a retirar");
        double retiro = Validacion.Scanner_doubles(leer, "");
        if (retiro <= 0) {
            System.out.println("No se pueden retirar numeros negativos");
            return;
        }
        if (this.saldo >= retiro) {
            this.saldo -= retiro;
            System.out.println("Retiro realizado exitosamente por: $" + retiro);
            System.out.println("Nuevo saldo: $" + this.saldo);
        } else {
            System.out.println("Saldo insuficiente. Saldo actual: $" + this.saldo);
        }
    }


    @Override
    public void transferir(ArrayList<Tarjeta> listaTarjetas) {
        if (!isActivo()) {
            System.out.println("No se puede transferir porque su tarjeta está desactivada.");
            return;
        }
        if (listaTarjetas.isEmpty()) {
            System.out.println("No hay tarjetas registradas");
            return;
        }
        System.out.println("\n--- TARJETAS REGISTRADAS ---");
        for (Tarjeta tar : listaTarjetas) {
            String ultimos4 = tar.getNo_tar().substring(tar.getNo_tar().length() - 4);
            System.out.println(" | Nombre: " + tar.getNombre() + " | Numero de tarjeta: ****" + ultimos4 + " | Tipo: " + tar.getTipo() + " | Activo: " + (tar.isActivo() ? "Sí" : "No"));
        }
        System.out.println("Introduce los últimos 4 dígitos de la tarjeta deL DESTINATARIO :");
        String digitosDestino = leer.next();
        leer.nextLine();


        Tarjeta tarjetaDestino = null;
        for (Tarjeta tar : listaTarjetas) {
            if (tar.getNo_tar().endsWith(digitosDestino)) {
                tarjetaDestino = tar;
                break;
            }
        }

        if (tarjetaDestino == null) {
            System.out.println("Error: No se encontró ninguna tarjeta de destino con esos dígitos.");
            return;
        }
        if (!tarjetaDestino.isActivo()) {
            System.out.println("Error: La tarjeta de destino está desactivada.");
            return;
        }
        if (tarjetaDestino == this) {
            System.out.println("Error: No puedes transferirte a ti mismo.");
            return;
        }

        System.out.println("Ingrese el monto a transferir: ");
        double monto = Validacion.Scanner_doubles(leer, "");

        if (monto <= 0) {
            System.out.println("Error: No se pueden transferir montos negativos o iguales a cero.");
            return;
        }


        if (this.saldo >= monto) {

            this.saldo -= monto;
            this.movimientos++;

            if (tarjetaDestino instanceof Debito) {
                ((Debito) tarjetaDestino).saldo += monto;
            } else if (tarjetaDestino instanceof Credito) {
                ((Credito) tarjetaDestino).setSaldo(((Credito) tarjetaDestino).getSaldo() - monto);
            }
            tarjetaDestino.movimientos++;

            System.out.println("¡Transferencia realizada con éxito!");
            System.out.println("Se enviaron $" + monto + " a la cuenta de " + tarjetaDestino.getNombre());
            System.out.println("Su saldo actual es: $" + this.saldo);
        } else {
            System.out.println("Saldo insuficiente para completar la transferencia. Saldo actual: $" + this.saldo);
        }
    }

    @Override
    public void mostrar_datos (){
        System.out.println("CLABE: " + getCLABE());
        System.out.println("CVV: " + getCVV());
        System.out.println("Fecha de Vencimiento: " + getFecha_ven());
        System.out.println("Número de Tarjeta: " + getNo_tar());
        System.out.println("Nombre del Titular: " + getNombre());
        System.out.println("Banco: " + getBanco());
        System.out.println("Saldo: " + getSaldo());
        System.out.println("Activa: " + (isActivo() ? "Sí" : "No"));
        System.out.println("Tipo: Debito");
    }


    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
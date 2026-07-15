public abstract class Tarjeta {
    protected String CLABE, CVV, fecha_ven, no_tar, nombre, banco, tipo;
    protected boolean activo;
    protected int movimientos;

    public Tarjeta(String CLABE, String CVV, String fecha_ven, String no_tar, String nombre, String banco, boolean activo, String tipo) {
        this.CLABE = CLABE;
        this.CVV = CVV;
        this.fecha_ven = fecha_ven;
        this.no_tar = no_tar;
        this.nombre = nombre;
        this.banco = banco;
        this.activo = true;
        this.tipo = tipo;
        this.movimientos = 0;
    }

    public Tarjeta() {

    }

    public void mostrar_datos() {
        System.out.println("\n--- DATOS DE LA TARJETA ---");
        System.out.println("Propietario: " + nombre);
        System.out.println("Banco: " + banco);
        System.out.println("Número de Tarjeta: " + no_tar);
        System.out.println("CLABE: " + CLABE);
        System.out.println("CVV: " + CVV);
        System.out.println("Fecha de Vencimiento: " + fecha_ven);
        System.out.println("Tipo: " + tipo);
        System.out.println("Estado: " + (activo ? "Activa" : "Bloqueada"));
        System.out.println("Movimientos realizados: " + movimientos);
    }

    public abstract void comprar();
    public abstract void depositar();
    public abstract void darDeBaja();
    public abstract void verMovimientos();
    public abstract void bloquear_tarjeta();
    public abstract void activar_tarjeta();

    public String getCLABE() {
        return CLABE;
    }

    public void setCLABE(String CLABE) {
        this.CLABE = CLABE;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getFecha_ven() {
        return fecha_ven;
    }

    public void setFecha_ven(String fecha_ven) {
        this.fecha_ven = fecha_ven;
    }

    public String getNo_tar() {
        return no_tar;
    }

    public void setNo_tar(String no_tar) {
        this.no_tar = no_tar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }
}
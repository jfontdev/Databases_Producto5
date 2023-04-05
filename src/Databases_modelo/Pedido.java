package Databases_modelo;

import java.time.LocalDateTime;

public class Pedido {

    private int cantidad;
    private String numeroPedido;
    private LocalDateTime fechaPedido;
    private Cliente cl;
    private Articulo art;

    /*
     *para los metodos
     **/
    private LocalDateTime fechaEnvio;
    private float precioEnvio;


    /*
     * Constructor
     */
    public Pedido(int cantidad, String numeroPedido, LocalDateTime fechaPedido, Cliente cl, Articulo art) {
        this.cantidad = cantidad;
        this.numeroPedido = numeroPedido;
        this.fechaPedido = fechaPedido;
        this.cl = cl;
        this.art = art;

    }
    /*
    * Método para calcular pedido enviado
    */
    public static LocalDateTime calcularEnvio(LocalDateTime fecha, int minutos) {
        return fecha.plusMinutes(minutos);
    }
    public void calcularEnvio() {
        fechaEnvio = fechaPedido.plusMinutes(art.getTiempoPreparacion());
    }
    public boolean pedidoEnviado() {
        calcularEnvio();
        return fechaEnvio.isBefore(LocalDateTime.now());
    }
    /*
     **Metodo para calcular precio de los productos del pedido más los gastos de envío
     */
    public float precioEnvio() {
        float precio = art.getPrecioVenta() * cantidad;
        precio += (art.getGastosEnvio() * (1 - cl.descuentoEnv()));
        return precio;
    }
    /*
     *********************Getters & Setters************************
     */

    /*
     * cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /*
     * numeroPedido
     */
    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /*
     * fechaPedido
     */
    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    /*
     * cliente
     */
    public Cliente getCl() {
        return cl;
    }

    public void setCl(Cliente cl) {
        this.cl = cl;
    }

    /*
     * articulo
     */
    public Articulo getArt() {
        return art;
    }

    public void setArt(Articulo art) {
        this.art = art;
    }

    @Override
    public String toString() {
        return "Pedido " +
                "cantidad=" + cantidad +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", cl=" + cl +
                ", art=" + art +
                ", gastosEnvio"= + art.getGastosEnvio() +
                ", Enviado=" + pedidoEnviado() +
                ", precioEnvio=" + precioEnvio();
    }
}



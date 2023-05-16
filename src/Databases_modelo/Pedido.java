package Databases_modelo;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
// FALTA HACER LAS RELACIONES DE CLIENTE Y ARTICULOS
@Table(name="pedidos")
public class Pedido implements Serializable {
    @Column(name = "numeroPedidoID")
    protected static int numeroPedidoID;

    @Id
    @Column(name = "numeroPedido")
    protected String numeroPedido;

    @Column(name = "cliente")
    protected Cliente cliente;

    @Column(name = "articulo")
    protected Articulo articulo;

    @Column(name = "cantidadArticulo")
    protected int cantidadArticulo;

    @Column(name = "fechaPedido")
    protected LocalDateTime fechaPedido;

    @Column(name = "fechaEnvio")
    protected LocalDateTime fechaEnvio;

    @Column(name = "tiempoPreparacion")
    protected int tiempoPreparacion;

    @Column(name = "seHaEnviado")
    protected boolean seHaEnviado;

    /*
     * Constructor
     */
    public Pedido(Cliente cliente, Articulo articulo, int cantidadArticulo)  {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidadArticulo = cantidadArticulo;
        this.numeroPedido = generadorIdPedido();
        this.fechaPedido = LocalDateTime.now();
        this.tiempoPreparacion = articulo.getTiempoPreparacion();
    }

    public Pedido() {

    }

    public static String generadorIdPedido(){
        numeroPedidoID++;
        return String.valueOf(numeroPedidoID);
    }

    /*
    * Método para calcular pedido enviado
    */
    public void calcularEnvio() {
        fechaEnvio = fechaPedido.plusDays(tiempoPreparacion);
    }
    public boolean pedidoEnviado() {
        calcularEnvio();
        seHaEnviado = fechaEnvio.isBefore(LocalDateTime.now());
        return seHaEnviado;
    }

    public boolean esCancelable() {
        return  !seHaEnviado;
    }
    /*
     **Metodo para calcular precio de los productos del pedido más los gastos de envío
     */

    public float calcularGastosEnvio() {
        float descuentoCliente = this.cliente.getDescuentoCliente() / 100;
        float descuento = descuentoCliente > 0 ? articulo.getGastosEnvio() * descuentoCliente : articulo.getGastosEnvio();
        return articulo.getGastosEnvio() - descuento;
    }

    public float precioEnvio() {
        float precioArticulo = articulo.getPrecioVenta() * cantidadArticulo;
        float costeEnvio = calcularGastosEnvio();
        return precioArticulo + costeEnvio;
    }
    /*
     *********************Getters & Setters************************
     */

    /*
    * Numero Pedido ID
     */
    public int getNumeroPedidoId(){return numeroPedidoID;}

    public void setNumeroPedidoId(int numeroPedidoId) {this.numeroPedidoID = numeroPedidoId; }
    /*
     * cantidad
     */
    public int getCantidadArticulo() {
        return cantidadArticulo;
    }

    public void setCantidadArticulo(int cantidadArticulo) {
        this.cantidadArticulo = cantidadArticulo;
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
     *    Fecha Envio
     */

    public LocalDateTime getFechaEnvio(){return fechaEnvio;}


    public void setFechaEnvio(LocalDateTime fechaEnvio) {this.fechaEnvio = fechaEnvio;}
    /*
    * Tiempo Preparacion
    */

    public int getTiempoPreparacion(){ return tiempoPreparacion;}

    public void setTiempoPreparacion(int tiempoPreparacion){ this.tiempoPreparacion = tiempoPreparacion;}
    /*
     * cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /*
     * articulo
     */
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    /*
     * Se Ha Enviado
     */

    public boolean getSeHaEnviado() { return seHaEnviado;}

    public void setSeHaEnviado(boolean seHaEnviado) { this.seHaEnviado = seHaEnviado;}

    @Override
    public String toString() {
        return "Pedido " +
                ", numeroPedido='" + numeroPedido + '\'' +
                ", fechaPedido=" + fechaPedido +
                ", nombreCliente=" + cliente.getNombre() +
                ", nifCliente=" + cliente.getNif() +
                ", articuloCodigo=" + articulo.getCodigoArticulo() +
                ", articuloDescripcion=" + articulo.getDescripcion() +
                ", cantidad=" + cantidadArticulo +
                ", articuloPrecio=" + articulo.getPrecioVenta() +
                ", precioEnvio=" + precioEnvio() +
                ", Enviado=" + pedidoEnviado();
    }


}



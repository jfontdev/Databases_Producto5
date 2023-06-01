package Databases_modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity

@Table(name="articulos")
public class Articulo implements Serializable {

  @Id
  @Column(name = "codigo_articulo")
  protected String codigoArticulo;

  @Column(name = "descripcion")
  protected String descripcion;

  @Column(name = "precio_venta")
  protected float precioVenta;

  @Column(name = "gastos_envio")
  protected float gastosEnvio;

  @Column(name = "tiempo_preparacion")
  protected int tiempoPreparacion;


  public Articulo (String codigoArticulo, String descripcion, float precioVenta, float gastosEnvio, int tiempoPreparacion){

    this.codigoArticulo = codigoArticulo;
    this.descripcion = descripcion;
    this.precioVenta = precioVenta;
    this.gastosEnvio = gastosEnvio;
    this.tiempoPreparacion = tiempoPreparacion;
  }

  public Articulo() {

  }

  public String getCodigoArticulo(){
    return codigoArticulo;
  }

  public void setCodigoArticulo(String codigoArticulo){
    this.codigoArticulo = codigoArticulo;
  }

  public String getDescripcion(){
    return descripcion;
  }
  public void setDescripcion(String descripcion){
    this.descripcion = descripcion;
  }
  public float getPrecioVenta(){
    return precioVenta;
  }
  public void setPrecioVenta(float precioVenta){
    this.precioVenta = precioVenta;
  }
  public float getGastosEnvio(){
    return gastosEnvio;
  }
  public void setGastosEnvio(float gastosEnvio){
    this.gastosEnvio = gastosEnvio;
  }
  public int getTiempoPreparacion(){
    return tiempoPreparacion;
  }
  public void setTiempoPreparacion(int tiempoPreparacion){
    this.tiempoPreparacion = tiempoPreparacion;
  }

  @Override
  public String toString() {
      return new StringJoiner(", ", Articulo.class.getSimpleName() + "[", "]")
              .add("codigo articulo='" + codigoArticulo + "'")
              .add("descripcion='" + descripcion + "'")
              .add("precio venta='" + precioVenta + "'")
              .add("gastos envio='" + gastosEnvio + "'")
              .add("tiempo preparacion='" + tiempoPreparacion + "'")
              .toString();
  }

}

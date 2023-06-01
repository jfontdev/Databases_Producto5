package Databases_vista;
import Databases_modelo.Articulo;
import java.util.ArrayList;

public class ArticuloVista {

    public void render(Articulo articulo){
        System.out.println("El articulo numero " + articulo.getCodigoArticulo() + " se ha creado");
    }

    public void renderAll(ArrayList<Articulo> articulos){
        for (Articulo articulo : articulos){
            System.out.println(articulo.getCodigoArticulo() + " " + articulo.getDescripcion() + " " + articulo.getPrecioVenta() + " " + articulo.getGastosEnvio() + " " + articulo.getTiempoPreparacion() );
        }
    }

    public void error(Exception exception){
        System.out.println("Ha ocurrido un error en artículos: \n\n");
        System.out.println("Mensaje de error de sql \n" + exception.getMessage());
    }
}

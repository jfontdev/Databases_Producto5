package Databases_controlador;
import Databases_modelo.Articulo;
import Databases_modelo.Datos;
import Databases_vista.ArticuloVista;

import java.util.ArrayList;

public class ArticulosControlador {
    private Datos datos;
    private ArticuloVista articuloVista;

    public ArticuloControlador(Datos datos) {
        this.datos = datos;
        this.articuloVista = new ArticuloVista();
    }



    public void articleList(){
        final ArrayList<Articulo> clientes = datos.getArticles();
        this.articuloVista.renderAll(articulos);
    }

    public void createArticle(Articulo articulo){
        try {
            this.datos.articleAdd(articulo);
            this.articuloVista.render(articulo);
        }catch (Exception e){
            articuloVista.error(e);
        }
    }

    public boolean articleExists(String codigoArticulo){
        return this.datos.articleExists(codigoArticulo);
    }

    public Articulo returnArticulo(String codigoArticulo){
        Articulo articulo = null;
        try {
            for (int i = 0; i < this.datos.customerLength(); i++){
                if (email.equals(this.datos.getCustomers().get(i).getEmail())){
                    cliente = this.datos.getCustomers().get(i);
                }
            }
        }catch (Exception e){
            articuloVista.error(e);
        }
        return cliente;
    }

}

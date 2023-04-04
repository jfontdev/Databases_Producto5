package Databases_controlador;
import Databases_modelo.Articulo;
import Databases_modelo.Datos;
import Databases_vista.ArticuloVista;

import java.util.ArrayList;

public class ArticulosControlador {
    private Datos datos;
    private ArticuloVista articuloVista;

    public ArticulosControlador(Datos datos) {
        this.datos = datos;
        this.articuloVista = new ArticuloVista();
    }

    public void articleList(){
        final ArrayList<Articulo> articulos = datos.getArticles();
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

    public Articulo returnArticulo(String codigoArticulo) {
        return this.datos.getArticulos().getByCode(codigoArticulo);
    }

    public boolean articuloRepe(String codigoArticulo) {
        return this.datos.articleExists(codigoArticulo);
    }

}

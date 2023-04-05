package Databases_modelo;

public class ListaArticulos extends Lista<Articulo>{
    public boolean contains(String codigoArticulo){
        for (Articulo articulo : this.lista) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return true;
            }
        }
        return false;
    }

    public Articulo getByCode(String codigoArticulo) {
        for (Articulo articulo : this.lista) {
            if (articulo.getCodigoArticulo().equals(codigoArticulo)) {
                return articulo;
            }
        }
        return null;
    }

    @Override
    public void add(Articulo articulo) throws Exception{
        if(contains(articulo.getCodigoArticulo())){
            throw new Exception("El articulo ya existe");
        }else {
            this.lista.add(articulo);
        }
    }
}

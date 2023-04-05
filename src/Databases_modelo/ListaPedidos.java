package Databases_modelo;

public class ListaPedidos extends Lista<Pedido>{
    public boolean contains(String numeroPedido){
        for (Pedido pedido : this.lista) {
            if (pedido.getNumeroPedido().equals(numeroPedido)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Pedido pedido) throws Exception{
        if(contains(pedido.getNumeroPedido())){
            throw new Exception("El cliente ya existe");
        }else {
            this.lista.add(pedido);
        }
    }
}



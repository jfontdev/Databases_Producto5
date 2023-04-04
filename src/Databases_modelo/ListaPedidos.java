package Databases_modelo;

public class ListaPedidos extends Lista<Pedido> {

    public String toString() {
        int indx = 0;
        String out = "Lista de Pedidos:\n";
        for (Pedido aux : lista) {
            out += indx + " - " + aux + "\n\n";
            indx++;
        }
        return out;
    }
}

package Databases_modelo;

public class ListaClientes extends Lista<Cliente>{
    public boolean contains(String email){
        for (Cliente cliente : this.lista) {
            if (cliente.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Cliente cliente) throws Exception{
        if(contains(cliente.getEmail())){
            throw new Exception("El cliente ya existe");
        }else {
            this.lista.add(cliente);
        }
    }
}

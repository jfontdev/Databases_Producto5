package Databases_controlador;

import Databases_modelo.Cliente;
import Databases_modelo.Datos;
import Databases_vista.ClienteVista;

import java.util.ArrayList;

public class ClientesControlador {
    private Datos datos;
    private ClienteVista clienteVista;

    public ClientesControlador(Datos datos) {
        this.datos = datos;
        this.clienteVista = new ClienteVista();
    }

    public void clientList(){
        final ArrayList<Cliente> clientes = datos.getCustomers();
        this.clienteVista.renderAll(clientes);
    }

    public void premiumClientList(){
        ArrayList<Cliente> clientesPremium = datos.getPremiumCustomers();
        this.clienteVista.renderAll(clientesPremium);
    }

    public void standardClientList(){
        ArrayList<Cliente> clientesEstandard = datos.getStandardCustomers();
        this.clienteVista.renderAll(clientesEstandard);
    }

    public void createClient(Cliente cliente){
        try {
            this.datos.customerAdd(cliente);
            this.clienteVista.render(cliente);
        }catch (Exception e){
            clienteVista.error(e);
        }
    }

    public boolean clientExists(String email){
        return this.datos.customerExists(email);
    }

    public Cliente returnCliente(String email){
        Cliente cliente = null;
        try {
            for (int i = 0; i < this.datos.customerLength(); i++){
                if (email.equals(this.datos.getCustomers().get(i).getEmail())){
                    cliente = this.datos.getCustomers().get(i);
                }
            }
        }catch (Exception e){
            clienteVista.error(e);
        }
        return cliente;
    }

}

package Databases_modelo;

import enums.ClienteTipo;

public class ClientePremium extends Cliente {

    public ClientePremium(String nombre, String apellidos, String domicilio, String nif, String email, ClienteTipo tipo) {
        super(nombre, apellidos, domicilio, nif, email, ClienteTipo.PREMIUM);
    }

    public float calcAnual() {
        return this.tipo == ClienteTipo.PREMIUM ? 20 : 0;
    }

    public float descuentoEnv(){
        return this.tipo == ClienteTipo.PREMIUM ? 30 : 0;
    }
}

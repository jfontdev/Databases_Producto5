package Databases_modelo;

import enums.ClienteTipo;

public class ClientePremium extends Cliente {

    public ClientePremium(String nombre, String apellidos, String domicilio, String nif, String email, ClienteTipo tipo) {
        super(nombre, apellidos, domicilio, nif, email, ClienteTipo.PREMIUM);
    }


}

package Databases_modelo;

import enums.ClienteTipo;

public class ClienteEstandard extends Cliente {

    public ClienteEstandard(String nombre, String apellidos, String domicilio, String nif, String email, ClienteTipo tipo) {
        super(nombre, apellidos, domicilio, nif, email, ClienteTipo.ESTANDARD);
    }

    @Override
    public float calcAnual() {
        return 0;
    }

    @Override
    public float descuentoEnv() {
        return 0;
    }
}

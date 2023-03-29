package Databases_modelo;

import enums.ClienteTipo;

import java.util.StringJoiner;

public abstract class Cliente {
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String nif;
    private String email;
    protected ClienteTipo tipo;
    protected float tasaCliente;
    protected float descuentoCliente;

    public Cliente(String nombre, String apellidos, String domicilio, String nif, String email, ClienteTipo tipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
        this.tipo = tipo;
        this.tasaCliente = calcAnual();
        this.descuentoCliente = descuentoEnv();
    }

    public abstract float calcAnual();

    public abstract float descuentoEnv();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClienteTipo getTipo() {
        return tipo;
    }

    public void setTipo(ClienteTipo tipo) {
        this.tipo = tipo;
    }

    public float getTasaCliente(){
        return this.tasaCliente;
    }

    public void setTasaCliente(float tasaCliente){
        this.tasaCliente = tasaCliente;
    }

    public float getDescuentoCliente(){
        return this.descuentoCliente;
    }

    public void setDescuentoCliente(float descuentoCliente){
        this.descuentoCliente = descuentoCliente;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cliente.class.getSimpleName() + "[", "]")
                .add("nombre='" + nombre + "'")
                .add("apellidos='" + apellidos + "'")
                .add("domicilio='" + domicilio + "'")
                .add("nif='" + nif + "'")
                .add("email='" + email + "'")
                .add("tipo=" + tipo)
                .add("tasaCliente=" + tasaCliente)
                .add("descuentoCliente=" + descuentoCliente)
                .toString();
    }
}

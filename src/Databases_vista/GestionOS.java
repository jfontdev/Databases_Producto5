package Databases_vista;

import Databases_controlador.ArticulosControlador;
import Databases_controlador.ClientesControlador;
import Databases_controlador.PedidosControlador;
import Databases_dao.DAOException;
import Databases_modelo.*;
import enums.ClienteTipo;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class GestionOS {
    private Datos datos;
    private ClientesControlador clientesControlador;
    private ArticulosControlador articulosControlador;
    private PedidosControlador pedidosControlador;
    Scanner teclado = new Scanner(System.in);

    public GestionOS() throws DAOException, SQLException {
        this.datos = new Datos();
        this.clientesControlador = new ClientesControlador(this.datos);
        this.articulosControlador = new ArticulosControlador(this.datos);
        this.pedidosControlador = new PedidosControlador(this.datos);
    }

    public void inicio(){
        boolean salir = false;
        int opcion;
        do {
            System.out.print("\n");
            System.out.println("1. Crear Cliente");
            System.out.println("2. Mostrar Clientes");
            System.out.println("3. Mostrar Clientes Premium");
            System.out.println("4. Mostrar Clientes Estandard");
            System.out.println("5. Crear artículo");
            System.out.println("6. Mostrar artículos");
            System.out.println("7. Crear Pedido");
            System.out.println("8. Mostrar Pedidos");
            System.out.println("9. Mostrar Pedidos Enviados");
            System.out.println("10. Mostrar Pedidos Pendientes");
            System.out.println("11. Borrar Pedido");
            System.out.println("0. Salir de la aplicacion");
            opcion = pedirOpcion();
            switch (opcion){
                case 1:
                    crearCliente();
                    break;
                case 2:
                    listarClientes(teclado,null);
                    break;
                case 3:
                    listarClientes(teclado,ClienteTipo.PREMIUM);
                    break;
                case 4:
                    listarClientes(teclado,ClienteTipo.ESTANDARD);
                    break;
                case 5:
                    createArticulos();
                    break;
                case 6:
                    listarArticulos();
                    break;
                case 7:
                    crearPedido();
                    break;
                case 8:
                    listarPedidos(teclado,null);
                    break;
                case 9 :
                    listarPedidos(teclado,true);
                    break;
                case 10 :
                    listarPedidos(teclado,false);
                    break;
                case 11 :
                    borrarPedido(teclado);
                    break;
                case 0:
                    salir = true;
            }
        } while (!salir);
    }

    int pedirOpcion(){
        int respuesta;
        System.out.println("Elige una opcion (1,2,3,4, 5, 6, 7, 8, 9, 10, 11 o 0):");
        respuesta = teclado.nextInt();
        return respuesta;
    }
    public void createArticulos() {
        System.out.println("--- Crear articulo ---");
        System.out.print("codigo articulo: ");
        String codigoArticulo = teclado.next();
        System.out.print("Descripcion: ");
        String descripcion = teclado.next();
        System.out.print("Precio de venta: ");
        Float precioVenta = teclado.nextFloat();
        System.out.print("Coste envio: ");
        Float gastosEnvio = teclado.nextFloat();
        System.out.print("Tiempo preparacion: ");
        Integer tiempoPreparacion = teclado.nextInt();
        if(!articulosControlador.articuloRepe(codigoArticulo)) {
            Articulo articulo = new Articulo(codigoArticulo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
            this.articulosControlador.createArticle(articulo);
        } else {
           System.out.println("Ya existe este artículo");
        }
    }


    public void crearCliente() {
        System.out.println(" -- Crear Cliente -- ");
        System.out.print("Nombre: ");
        String nombre = teclado.next();
        teclado.nextLine();
        System.out.print("Apellido: ");
        String apellido = teclado.next();
        teclado.nextLine();
        System.out.print("Domicilio: ");
        String domicilio = teclado.next();
        teclado.nextLine();
        System.out.print("NIF: ");
        String nif = teclado.next();
        teclado.nextLine();
        System.out.print("Email: ");
        String email = teclado.nextLine();
        if(clientesControlador.clientExists(email)){
            System.out.println("El cliente ya existe");
            return;
        }
        System.out.print("Tipo (1) Premium (2) Estandard: ");
        String tipo;
        do{
            tipo = teclado.next();
        }while (!"12".contains(tipo));
        switch (tipo){
            case "1":
                ClientePremium clientePremium = new ClientePremium(nombre,apellido,domicilio,nif,email,ClienteTipo.PREMIUM);
                this.clientesControlador.createClient(clientePremium);
                break;
            case "2":
                ClienteEstandard clienteEstandard = new ClienteEstandard(nombre,apellido,domicilio,nif,email,ClienteTipo.ESTANDARD);
                this.clientesControlador.createClient(clienteEstandard);
                break;
        }
    }

    public void listarClientes(Scanner scanner, ClienteTipo clienteTipo) {
        if (clienteTipo != null) {
            if (clienteTipo == ClienteTipo.ESTANDARD) {
                clientesControlador.standardClientList();
            }
            if (clienteTipo == ClienteTipo.PREMIUM) {
                clientesControlador.premiumClientList();
            }
        } else {
            clientesControlador.clientList();
        }

    }
    public void listarArticulos() {
        articulosControlador.articleList();
    }

    /*
    ********* Pedidos************
    */
    public void crearPedido() {
        Cliente cliente;
        Articulo articulo;
        System.out.println("--- Crear Pedido --- ");
        System.out.print("Email Cliente: ");
        String emailCliente = teclado.next();
        cliente = this.clientesControlador.returnCliente(emailCliente);
        System.out.println("--- Lista Articulos disponibles ---");
        this.articulosControlador.articleList();
        System.out.println("--- Introduce el codigo del articulo --- \n");
        System.out.println("Codigo Articulo");
        String idArticulo = teclado.next();
        articulo = this.articulosControlador.returnArticulo(idArticulo);
        System.out.print("Cantidad: ");
        int cantidad = teclado.nextInt();
        Pedido nuevoPedido = new Pedido(cliente,articulo,cantidad);
        this.pedidosControlador.crearPedido(nuevoPedido);
    }




    public void listarPedidos(Scanner scanner,Boolean estadoEnvio){
        Optional<Boolean> estado = Optional.ofNullable(estadoEnvio);
        if(Boolean.TRUE.equals(estadoEnvio)){
            pedidosControlador.listarPedidosEnviados();
        }else if(Boolean.FALSE.equals(estadoEnvio)){
            pedidosControlador.listarPedidosPendientes();
        }else if (estado.isEmpty()) {
            pedidosControlador.listarPedidos();
        }
    }

    public void borrarPedido(Scanner scanner){
        Pedido pedido;
        System.out.println("Numero Pedido: ");;
        String numeroPedido = teclado.next();
        pedido = this.pedidosControlador.mostrarPedido(numeroPedido);
        if (pedido.esCancelable()){
            this.pedidosControlador.borrar(pedido);
            System.out.println("El pedido con numero de pedido: " + numeroPedido + " ha sido borrado correctamente");
        }else {
            System.out.println("Este pedido no se puede cancelar en estos momentos.");
        }
    }
}
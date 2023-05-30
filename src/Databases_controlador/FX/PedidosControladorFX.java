package Databases_controlador.FX;

import Databases_modelo.Pedido;
import Databases_modelo.Datos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PedidosControladorFX implements Initializable {
    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private TableColumn colCliente;
    @FXML
    private TableColumn colArticulo;
    @FXML
    private TableColumn colCantidad;
    @FXML
    private TableColumn colFecha;
    @FXML
    private TableColumn colFechaEnvio;
    @FXML
    private TableColumn colTiempoPrep;
    @FXML
    private TableColumn colEnviado;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    ToggleGroup tg;
    ObservableList<Pedido> invoices;
    Datos datos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();

        try {
            this.datos = new Datos();
        } catch (Exception e) {
            System.err.println("Error creating Data. " + e);
        }

        //Inicializar el ObservableList con la base de datos
        ArrayList<Pedido> dataCustomers = datos.getPedidosPendientes();
        if (dataCustomers != null) {
            invoices = FXCollections.observableArrayList(dataCustomers);
        }


        //Inicializar la Tabla
        this.tablaPedidos.setItems(invoices);
                //Inicializar la columna poniendo el nombre del atributo del modelo
        this.colCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        this.colArticulo.setCellValueFactory(new PropertyValueFactory("articulo"));
        this.colCantidad.setCellValueFactory(new PropertyValueFactory("cantidadArticulo"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory("fechaPedido"));
        this.colFechaEnvio.setCellValueFactory(new PropertyValueFactory("fechaEnvio"));
        this.colTiempoPrep.setCellValueFactory(new PropertyValueFactory("tiempoPreparacion"));
        this.colEnviado.setCellValueFactory(new PropertyValueFactory("seHaEnviado"));

    }

    @FXML
    public void addInvoice(ActionEvent event) {

    }

    @FXML
    private void updateInvoice(ActionEvent event) {
    }

    @FXML
    private void deleteInvoice(ActionEvent event) {

    }
}


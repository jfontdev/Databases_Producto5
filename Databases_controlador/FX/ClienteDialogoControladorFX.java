package Databases_controlador.FX;

import java.net.URL;
import java.util.ResourceBundle;

import Databases_modelo.Cliente;
import enums.ClienteTipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClienteDialogoControladorFX implements Initializable {

    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtIdCardNumber;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<ClienteTipo> cboxCustomerType;


    private Cliente customer;
    private ObservableList<Cliente> customers;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cboxCustomerType.getItems().setAll(ClienteTipo.values());
    }

    public void inicializeVariables(ObservableList<Cliente> clientes){
        this.customers = clientes;
    }
    public void inicializeVariables(ObservableList<Cliente> clientes, Cliente cliente){
        this.customers = clientes;
        this.customer = cliente;

        txtFirstName.setText(cliente.getNombre());
        txtLastName.setText(cliente.getApellidos());
        txtEmail.setText(cliente.getEmail());
        txtAddress.setText(cliente.getDomicilio());
        txtIdCardNumber.setText(cliente.getNif());
        cboxCustomerType.setValue(cliente.getTipo());
    }

    @FXML
    private void cancelar(ActionEvent event) {
        this.customer = null;
        Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
        stage.close();
    }

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    @FXML
    private void guardar(ActionEvent event) {
        String nombre = this.txtFirstName.getText();
        String apellidos = this.txtLastName.getText();
        String domicilio = this.txtAddress.getText();
        String nif = this.txtIdCardNumber.getText();
        String email = this.txtEmail.getText();
        ClienteTipo tipo = this.cboxCustomerType.getValue();

        Cliente cliente = new Cliente(nombre, apellidos, domicilio, nif, email, tipo);

        if(!customers.contains(cliente)){
            //Modificar
            if(this.customer != null){
                this.customer.setNombre(nombre);
                this.customer.setApellidos(apellidos);
                this.customer.setDomicilio(domicilio);
                this.customer.setNif(nif);
                this.customer.setEmail(email);
                this.customer.setTipo(tipo);
                this.customer.setTasaCliente(this.customer.calcAnual());
                this.customer.setDescuentoCliente(this.customer.descuentoEnv());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();
            }
            else{
                this.customer = cliente;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();
            }
            Stage stage = (Stage) this.btnCancelar.getScene().getWindow();
            stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Customer already exists");
            alert.showAndWait();
        }
    }


}

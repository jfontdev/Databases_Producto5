package Databases_controlador.FX;

import Databases_modelo.Cliente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Databases_modelo.Datos;
import enums.ClienteTipo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ClientesControladorFX implements Initializable {
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn colFirstName;
    @FXML
    private TableColumn colLastName;
    @FXML
    private TableColumn colAdress;
    @FXML
    private TableColumn colIdCard;
    @FXML
    private TableColumn colEmail;
    @FXML
    private TableColumn colType;
    @FXML
    private TableColumn colFee;
    @FXML
    private TableColumn colDiscount;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private RadioButton btnRegular;
    @FXML
    private RadioButton btnPremium;
    @FXML
    private RadioButton btnTodos;

    ToggleGroup tg;
    ObservableList<Cliente> customers;
    ObservableList<Cliente> filterCustomers;
    Datos datos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();
        btnRegular.setToggleGroup(tg);
        btnPremium.setToggleGroup(tg);
        btnTodos.setToggleGroup(tg);
        btnTodos.setSelected(true);

        try {
            this.datos = new Datos();
        } catch (Exception e) {
            System.err.println("Error creating Data. " + e);
        }

        //Inicializar el ObservableList con la base de datos
        ArrayList<Cliente> dataCustomers = datos.getClientes();
        if (dataCustomers != null) {
            customers = FXCollections.observableArrayList(dataCustomers);
        }


        //Inicializar la Tabla
        this.tablaClientes.setItems(customers);

        //Inicializar la columna poniendo el nombre del atributo del modelo
        this.colFirstName.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colLastName.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colAdress.setCellValueFactory(new PropertyValueFactory("domicilio"));
        this.colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        this.colIdCard.setCellValueFactory(new PropertyValueFactory("nif"));
        this.colType.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.colFee.setCellValueFactory(new PropertyValueFactory("tasaCliente"));
        this.colDiscount.setCellValueFactory(new PropertyValueFactory("descuentoCliente"));

        filterCustomers = FXCollections.observableArrayList();
    }

    @FXML
    private void select(MouseEvent event) {
    }

    @FXML
    public void addCustomer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/ClienteDialogoVistaFX.fxml"));
            Parent root = loader.load();

            ClienteDialogoControladorFX controller = loader.getController();
            controller.inicializeVariables(customers);

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Cliente cliente = controller.getCustomer();
            if (cliente != null) {
                try {
                    datos.customerAdd(cliente);
                } catch (Exception e) {

                }
                this.customers.add(cliente);

                //Para que aparezca en el filtro
                if (cliente.getTipo() == ClienteTipo.ESTANDARD && btnRegular.isSelected()
                        || cliente.getTipo() == ClienteTipo.PREMIUM && btnPremium.isSelected()) {
                    this.filterCustomers.add(cliente);
                }

                this.tablaClientes.refresh();
            }

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
        Cliente cliente = this.tablaClientes.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ningun Customer");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/ClienteDialogoVistaFX.fxml"));
                Parent root = loader.load();

                ClienteDialogoControladorFX controller = loader.getController();
                controller.inicializeVariables(customers, cliente);

                Scene scene = new Scene(root);

                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();


                Cliente clienteSelecionado = controller.getCustomer();
                if (clienteSelecionado != null) {
                    //Actualizamos cliente de la DB
                    try {
                        datos.customerUpdate(cliente);
                    } catch (Exception e) {

                    }
                    //Filtro
                    if (clienteSelecionado.getTipo() != ClienteTipo.ESTANDARD && this.btnRegular.isSelected()
                            || clienteSelecionado.getTipo() != ClienteTipo.PREMIUM && this.btnPremium.isSelected()) {
                        this.filterCustomers.remove(clienteSelecionado);
                    }

                    this.tablaClientes.refresh();
                }

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        Cliente cliente = this.tablaClientes.getSelectionModel().getSelectedItem();

        if (cliente == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ningun Customer");
            alert.showAndWait();
        } else {

            //Eliminamos cliente de la DB
            try {
                datos.deleteCustomer(cliente);
            } catch (Exception e) {
                System.err.println("No se ha podido eliminar de la Base de datos." + e.getMessage());
            }

            this.customers.remove(cliente);
            this.tablaClientes.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se ha Eliminado correctamente");
            alert.showAndWait();
        }
    }

    @FXML
    private void filter(ActionEvent event) {
        if (this.btnTodos.isSelected()) {
            this.tablaClientes.setItems(customers);
        } else {
            this.filterCustomers.clear();

            if (this.btnRegular.isSelected()) {
                for (Cliente cliente : this.customers) {
                    if (cliente.getTipo() == ClienteTipo.ESTANDARD) {
                        this.filterCustomers.add(cliente);
                    }
                }
            } else if (this.btnPremium.isSelected()) {
                for (Cliente cliente : this.customers) {
                    if (cliente.getTipo() == ClienteTipo.PREMIUM) {
                        this.filterCustomers.add(cliente);
                    }
                }
            }
            this.tablaClientes.setItems(filterCustomers);
        }
    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/PantallaPrincipal.fxml"));
            Parent root = loader.load();

            ControladorFX controller = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnAdd.getScene().getWindow();
            myStage.close();
        } catch (Exception e) {
        }
    }

}


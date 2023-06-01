package Databases_controlador.FX;

import Databases_modelo.Articulo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Databases_modelo.Datos;
import javafx.beans.property.SimpleIntegerProperty;
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

public class ArticuloControladorFX implements Initializable {
    @FXML
    private TableView<Articulo> tablaArticulos;
    @FXML
    private TableColumn colCode;
    @FXML
    private TableColumn colDescription;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colShipping;
    @FXML
    private TableColumn colPrepTime;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    ToggleGroup tg;
    ObservableList<Articulo> articles;
    Datos datos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.datos = new Datos();
        } catch (Exception e) {
            System.err.println("Error creating Data. " + e);
        }

        ArrayList<Articulo> dataArticles = datos.getArticles();
        if (dataArticles != null) {
            articles = FXCollections.observableArrayList(dataArticles);
        }


        this.tablaArticulos.setItems(articles);
        this.colCode.setCellValueFactory(new PropertyValueFactory("codigo_articulo"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory("descripcion"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory("precio_venta"));
        this.colShipping.setCellValueFactory(new PropertyValueFactory("gastos_envio"));
        this.colPrepTime.setCellValueFactory(new PropertyValueFactory("tiempo_preparacion"));
    }

    @FXML
    private void select(MouseEvent event) {
    }

    @FXML
    public void addArticle(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/ArticuloDialogoVistaFX.fxml"));
            Parent root = loader.load();

            ArticuloDialogoControladorFX controller = loader.getController();
            controller.inicializeVariables(articles);

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Articulo articulo = controller.getArticle();
            if (articulo != null) {
                try {
                    datos.articleAdd(articulo);
                } catch (Exception e) {

                }
                this.articles.add(articulo);

                this.tablaArticulos.refresh();
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
    private void updateArticle(ActionEvent event) {
        Articulo articulo = this.tablaArticulos.getSelectionModel().getSelectedItem();

        if (articulo == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ningun artículo");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Databases_vista/FX/ArticuloDialogoVistaFX.fxml"));
                Parent root = loader.load();

                ArticuloDialogoControladorFX controller = loader.getController();
                controller.inicializeVariables(articles, articulo);

                Scene scene = new Scene(root);

                Stage stage = new Stage();

                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();


                Articulo articuloSelecionado = controller.getArticle();
                if (articuloSelecionado != null) {

                    try {
                        datos.articleUpdate(articulo);
                    } catch (Exception e) {

                    }

                    this.tablaArticulos.refresh();
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
    private void deleteArticle(ActionEvent event) {
        Articulo articulo = this.tablaArticulos.getSelectionModel().getSelectedItem();

        if (articulo == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No se ha seleccionado ningun artículo");
            alert.showAndWait();
        } else {

            try {
                datos.deleteArticle(articulo);
            } catch (Exception e) {
                System.err.println("No se ha podido eliminar de la Base de datos." + e.getMessage());
            }

            this.articles.remove(articulo);
            this.tablaArticulos.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se ha Eliminado correctamente");
            alert.showAndWait();
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

